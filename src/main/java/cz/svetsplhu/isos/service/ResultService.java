package cz.svetsplhu.isos.service;

import cz.svetsplhu.isos.repository.*;
import cz.svetsplhu.isos.repository.model.*;
import cz.svetsplhu.isos.repository.model.comparator.ParticipationComparator;
import cz.svetsplhu.isos.service.mapper.CompetitionMapper;
import cz.svetsplhu.isos.service.mapper.NominationMapper;
import cz.svetsplhu.isos.service.mapper.ParticipationMapper;
import cz.svetsplhu.isos.service.mapper.TimeMapper;
import cz.svetsplhu.isos.service.model.Competition;
import cz.svetsplhu.isos.service.model.CompetitionTime;
import cz.svetsplhu.isos.service.model.Nomination;
import cz.svetsplhu.isos.service.model.Participation;
import cz.svetsplhu.isos.service.uploader.ResultLoader;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by M on 22.07.2017.
 */
@Service
public class ResultService {

    private final ResultLoader resultLoader;
    private final RopeClimberRepository ropeClimberRepository;
    private final ParticipationRepository participationRepository;
    private final CompetitionRepository competitionRepository;
    private final TimeRepository timeRepository;
    private final NominationRepository nominationRepository;
    private final NominationMapper nominationMapper;
    private final CompetitionMapper competitionMapper;
    private final TimeMapper timeMapper;
    private final ParticipationMapper participationMapper;

    @Inject
    public ResultService(ResultLoader resultLoader,
                         RopeClimberRepository ropeClimberRepository,
                         ParticipationRepository participationRepository,
                         CompetitionRepository competitionRepository,
                         TimeRepository timeRepository,
                         NominationRepository nominationRepository,
                         NominationMapper nominationMapper,
                         CompetitionMapper competitionMapper,
                         TimeMapper timeMapper,
                         ParticipationMapper participationMapper) {
        this.resultLoader = resultLoader;
        this.ropeClimberRepository = ropeClimberRepository;
        this.participationRepository = participationRepository;
        this.competitionRepository = competitionRepository;
        this.timeRepository = timeRepository;
        this.nominationRepository = nominationRepository;
        this.nominationMapper = nominationMapper;
        this.competitionMapper = competitionMapper;
        this.timeMapper = timeMapper;
        this.participationMapper = participationMapper;
    }

    @Transactional(rollbackOn = Exception.class)
    public void load(InputStream file) throws Exception {
        try {
            CompetitionEntity competition = resultLoader.load(file);
            CompetitionEntity persistedCompetition = competitionRepository.findByNameAndCompetitionNameAndDate(competition.getName(), competition.getCompetitionName(), competition.getDate());
            if (persistedCompetition != null) {
                persistedCompetition.setCompetitionName(competition.getCompetitionName());
                persistedCompetition.setName(competition.getName());
                persistedCompetition.setJudge(competition.getJudge());
                persistedCompetition.setPlace(competition.getPlace());
                persistedCompetition.setDate(competition.getDate());
                persistedCompetition.setType(competition.getType());
                persistedCompetition.setStarter(competition.getStarter());
                persistedCompetition.setSensorInstallation(competition.getSensorInstallation());
                persistedCompetition.getParticipationList().forEach(participationEntity -> {
                    timeRepository.deleteAll(participationEntity.getTimeList());
                    if (participationEntity.getRopeClimber().getParticipationList().size() <= 1) {
                        ropeClimberRepository.deleteById(participationEntity.getRopeClimber().getId());
                    }
                });
                participationRepository.deleteAll(persistedCompetition.getParticipationList());
                persistedCompetition.setParticipationList(competition.getParticipationList());
                persistedCompetition.getParticipationList().forEach(participation -> participation.setCompetition(persistedCompetition));
                competition = persistedCompetition;

            }
            saveCompetition(competition);
        } catch (Exception e) {
            e.printStackTrace();

            throw new Exception(e);
        }

    }

    @Transactional
    public void saveCompetition(CompetitionEntity competition) {
        List<ParticipationEntity> participationList = new ArrayList<>(competition.getParticipationList());
        competitionRepository.save(competition);
        for (ParticipationEntity participation : participationList) {
            saveParticipation(participation);
        }
    }

    @Transactional
    public void saveParticipation(ParticipationEntity participation) {
        RopeClimberEntity ropeClimber = participation.getRopeClimber();
        RopeClimberEntity persistedRopeClimber = ropeClimberRepository.findByFirstNameAndLastNameAndYearOfBirth(ropeClimber.getFirstName(), ropeClimber.getLastName(), ropeClimber.getYearOfBirth());
        if (persistedRopeClimber == null) {
            participation.setRopeClimber(ropeClimberRepository.save(ropeClimber));
        } else {
            participation.setRopeClimber(persistedRopeClimber);
        }
        List<TimeEntity> timeList = participation.getTimeList();
        participationRepository.save(participation);
        timeRepository.saveAll(timeList);
    }

    @Transactional
    public void delete(Long competitionId) {
        Optional<CompetitionEntity> competitionEntityOptional = competitionRepository.findById(competitionId);
        competitionEntityOptional.ifPresent(competitionEntity -> {
            competitionEntity.getParticipationList().forEach(participationEntity -> {
                timeRepository.deleteAll(participationEntity.getTimeList());
                if (participationEntity.getRopeClimber().getParticipationList().size() <= 1) {
                    ropeClimberRepository.deleteById(participationEntity.getRopeClimber().getId());
                }
            });
            participationRepository.deleteAll(competitionEntity.getParticipationList());
        });
        competitionRepository.deleteById(competitionId);
    }

    public List<Nomination> getNominations(Integer year,
                                           Long categoryId) {
        List<NominationEntity> nominationEntities = nominationRepository.getNominations(year, categoryId);
        Map<String, Nomination> nominations = new HashMap<>();
        for (NominationEntity nominationEntity : nominationEntities) {
            String nominationKey = nominationEntity.getFirstName() + ":" + nominationEntity.getLastName() + ":" + nominationEntity.getYearOfBirth();
            Nomination nomination = nominations.get(nominationKey);
            if (nomination == null) {
                nomination = new Nomination();
                nomination.setFirstName(nominationEntity.getFirstName());
                nomination.setLastName(nominationEntity.getLastName());
                nomination.setYearOfBirth(nominationEntity.getYearOfBirth());
                nomination.setTime(nominationEntity.getTime());
                nominations.put(nominationKey, nomination);
            }
            CompetitionTime competitionTime = new CompetitionTime();
            competitionTime.setTime(nominationEntity.getTime());
            competitionTime.setCompetitionName(nominationEntity.getCompetitionName());
            nomination.getCompetitionTimes().add(competitionTime);
            if (nomination.getTime().compareTo(nominationEntity.getTime()) < 0) {
                nomination.setTime(nominationEntity.getTime());
            }
        }
        return new ArrayList<>(nominations.values());
    }

    @Transactional
    public List<Participation> getParticipations(Long competitionId,
                                                 Long categoryId) {
        List<ParticipationEntity> resultList = participationRepository.findByCompetitionIdAndCategoryId(competitionId, categoryId);
        resultList = new LinkedList<>(resultList);
        resultList.sort(new ParticipationComparator());
        setResultRank(resultList);
        return resultList
                .stream()
                .map(participationMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Participation> getTopForCategory(Long id) {
        List<ParticipationEntity> resultList = participationRepository.findByCategoryId(id);

        Map<Long, ParticipationEntity> ropeClimberMap = new HashMap<>();
        resultList = new LinkedList<>(resultList);
        resultList.sort(new ParticipationComparator());
        for (ParticipationEntity participation : resultList) {
            ropeClimberMap.putIfAbsent(participation.getRopeClimber().getId(), participation);

        }
        resultList = new LinkedList<>(ropeClimberMap.values());
        resultList.sort(new ParticipationComparator());
        setTopResultRank(resultList);
        return resultList
                .stream()
                .map(participationMapper::map)
                .collect(Collectors.toList());
    }


    public void setResultRank(List<ParticipationEntity> resultRank) {
        Iterator<ParticipationEntity> it = resultRank.iterator();
        int rank = 1;
        ParticipationEntity lastPart = null;
        while (it.hasNext()) {
            ParticipationEntity p = it.next();
            if (lastPart == null || !isEqual(p, lastPart)) {
                p.setResultRank(rank);
                lastPart = p;
            } else {
                p.setResultRank(lastPart.getResultRank());
                lastPart = p;
            }
            rank++;
        }
    }

    private boolean isEqual(ParticipationEntity p1, ParticipationEntity p2) {
        int index = p1.getTimeDoubleList().size();
        if (index > p2.getTimeDoubleList().size()) {
            index = p2.getTimeDoubleList().size();
        }
        for (int i = 0; i < index; i++) {
            if (!p1.getTimeDoubleList().get(i).equals(p2.getTimeDoubleList().get(i))) {
                return false;
            }
        }
        return p1.getTimeDoubleList().size() == p2.getTimeDoubleList().size();
    }

    public void setTopResultRank(List<ParticipationEntity> resultRank) {
        Iterator<ParticipationEntity> it = resultRank.iterator();
        int rank = 1;
        ParticipationEntity lastPart = null;
        while (it.hasNext()) {
            ParticipationEntity p = it.next();
            if (lastPart == null || !p.getTopTime().equals(lastPart.getTopTime())) {
                p.setResultRank(rank);
                lastPart = p;
            } else {
                p.setResultRank(lastPart.getResultRank());
                lastPart = p;
            }
            rank++;
        }
    }


}
