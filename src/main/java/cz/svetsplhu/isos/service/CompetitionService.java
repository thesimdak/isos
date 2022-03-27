package cz.svetsplhu.isos.service;

import cz.svetsplhu.isos.repository.CategoryRepository;
import cz.svetsplhu.isos.repository.CompetitionRepository;
import cz.svetsplhu.isos.repository.SeasonsRepository;
import cz.svetsplhu.isos.repository.model.CompetitionEntity;
import cz.svetsplhu.isos.service.mapper.CategoryMapper;
import cz.svetsplhu.isos.service.mapper.CompetitionMapper;
import cz.svetsplhu.isos.service.model.Category;
import cz.svetsplhu.isos.service.model.Competition;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by M on 22.07.2017.
 */
@Service
public class CompetitionService {

    private final SeasonsRepository seasonsRepository;
    private final CompetitionRepository competitionRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CompetitionMapper competitionMapper;

    @Inject
    public CompetitionService(SeasonsRepository seasonsRepository,
                              CompetitionRepository competitionRepository,
                              CategoryRepository categoryRepository,
                              CategoryMapper categoryMapper,
                              CompetitionMapper competitionMapper) {
        this.seasonsRepository = seasonsRepository;
        this.competitionRepository = competitionRepository;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.competitionMapper = competitionMapper;
    }

    public List<Category> getCategories(Long competitionId) {
        return categoryRepository.findByCompetitionId(competitionId)
                .stream()
                .map(categoryMapper::map)
                .collect(Collectors.toList());
    }

    public List<Competition> getCompetitions(Integer year) {
        return competitionRepository.findByYear(year)
                .stream()
                .map(competitionMapper::map)
                .collect(Collectors.toList());
    }

    public List<Competition> getCompetitions() {
        return competitionRepository.findAllByOrderByDateDesc()
                .stream()
                .map(competitionMapper::map)
                .collect(Collectors.toList());
    }

    public List<Integer> getSeasons() {
        return seasonsRepository.getSeasons();
    }

    public List<Category> getCategories() {
        return categoryRepository.findAllNotEmpty()
                .stream()
                .map(categoryMapper::map)
                .collect(Collectors.toList());
    }

    public Competition getCompetition(Long id) {
        Optional<CompetitionEntity> competitionEntityOptional = competitionRepository.findById(id);
        return competitionEntityOptional.map(competitionMapper::map).orElse(null);
    }
}
