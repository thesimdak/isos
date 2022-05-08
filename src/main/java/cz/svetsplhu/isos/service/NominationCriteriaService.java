package cz.svetsplhu.isos.service;

import cz.svetsplhu.isos.repository.NominationCriteriaRepository;
import cz.svetsplhu.isos.repository.model.NominationCriteriaEntity;
import cz.svetsplhu.isos.service.mapper.NominationCriteriaMapper;
import cz.svetsplhu.isos.service.model.NominationCriteria;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NominationCriteriaService {

    private final NominationCriteriaRepository nominationCriteriaRepository;
    private final NominationCriteriaMapper nominationCriteriaMapper;

    public NominationCriteriaService(NominationCriteriaRepository nominationCriteriaRepository,
                                     NominationCriteriaMapper nominationCriteriaMapper) {
        this.nominationCriteriaRepository = nominationCriteriaRepository;
        this.nominationCriteriaMapper = nominationCriteriaMapper;
    }

    public List<NominationCriteria> getNominationCriteria(Integer year) {
        List<NominationCriteriaEntity> nominationCriteria = nominationCriteriaRepository.findByYear(year);
        return nominationCriteria.stream().map(nominationCriteriaMapper::map).collect(Collectors.toList());
    }

    public NominationCriteria saveNominationCriteria(NominationCriteria nominationCriteria) {
        NominationCriteriaEntity nominationCriteriaEntity = nominationCriteriaMapper.map(nominationCriteria);
        nominationCriteriaEntity = nominationCriteriaRepository.save(nominationCriteriaEntity);
        return nominationCriteriaMapper.map(nominationCriteriaEntity);
    }


    public List<Integer> getNominationCriteriaSeasons() {
        return nominationCriteriaRepository.findAll()
                .stream().map(NominationCriteriaEntity::getYear).distinct().collect(Collectors.toList());

    }
}
