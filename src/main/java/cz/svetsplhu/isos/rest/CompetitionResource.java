package cz.svetsplhu.isos.rest;

import cz.svetsplhu.isos.constants.Constants;
import cz.svetsplhu.isos.rest.model.CategoryDto;
import cz.svetsplhu.isos.rest.model.CompetitionDto;
import cz.svetsplhu.isos.rest.model.mapper.CategoryDtoMapper;
import cz.svetsplhu.isos.rest.model.mapper.CompetitionDtoMapper;
import cz.svetsplhu.isos.service.CompetitionService;
import cz.svetsplhu.isos.service.model.Category;
import cz.svetsplhu.isos.service.model.Competition;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for competitions.
 */
@Path("/competitions")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class CompetitionResource {

    private final CompetitionService competitionService;
    private final CompetitionDtoMapper competitionDtoMapper;
    private final CategoryDtoMapper categoryDtoMapper;

    @Inject
    public CompetitionResource(CompetitionService competitionService,
                               CompetitionDtoMapper competitionDtoMapper,
                               CategoryDtoMapper categoryDtoMapper) {
        this.competitionService = competitionService;
        this.competitionDtoMapper = competitionDtoMapper;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @GET
    @Path("/seasons")
    public List<Integer> getSeasons() {
        return competitionService.getSeasons();
    }

    @GET
    @Path("/seasons/{year}")
    public List<CompetitionDto> getCompetitions(@PathParam("year") Integer year) {
        return competitionService.getCompetitions(year)
                .stream()
                .map(competitionDtoMapper::map)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{competitionId}")
    public CompetitionDto getCompetition(@PathParam("competitionId") Long id) {
        return competitionDtoMapper.map(competitionService.getCompetition(id));
    }

    @GET
    public List<CompetitionDto> getCompetitions() {
        return competitionService.getCompetitions()
                .stream()
                .map(competitionDtoMapper::map)
                .collect(Collectors.toList());
    }


    @GET
    @Path("/categories")
    public List<Category> getCategories() {
        return competitionService.getCategories();
    }

    @GET
    @Path("/{competitionId}/categories")
    public List<CategoryDto> getCategories(@PathParam("competitionId") Long competitionId) {
        Competition competition = competitionService.getCompetition(competitionId);
        List<Category> categories = competitionService.getCategories(competitionId);
        if (competition.getDate().getYear() >= 2018) {
            renameSeniorsToMasters(categories);
        }
        return categories
                .stream()
                .map(categoryDtoMapper::map)
                .collect(Collectors.toList());
    }

    private void renameSeniorsToMasters(List<Category> categories) {
        for (Category category : categories) {
            if (category.getCategoryKey().equals(Constants.KAT_IV)) {
                category.setLabel(Constants.KAT_IV_NEW_NAME);
            }
        }
    }
}
