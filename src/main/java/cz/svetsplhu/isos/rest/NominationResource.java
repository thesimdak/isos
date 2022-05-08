package cz.svetsplhu.isos.rest;

import cz.svetsplhu.isos.rest.model.CompetitionDto;
import cz.svetsplhu.isos.rest.model.NominationContainerDto;
import cz.svetsplhu.isos.rest.model.NominationDto;
import cz.svetsplhu.isos.rest.model.mapper.CompetitionDtoMapper;
import cz.svetsplhu.isos.rest.model.mapper.NominationDtoMapper;
import cz.svetsplhu.isos.service.CompetitionService;
import cz.svetsplhu.isos.service.ResultService;
import cz.svetsplhu.isos.service.model.Competition;
import cz.svetsplhu.isos.service.model.Nomination;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by M on 14.05.2017.
 */

@Path("/nominations")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class NominationResource {

    private final ResultService resultService;
    private final CompetitionService competitionService;
    private final NominationDtoMapper nominationDtoMapper;
    private final CompetitionDtoMapper competitionDtoMapper;

    @Inject
    public NominationResource(ResultService resultService,
                              CompetitionService competitionService,
                              NominationDtoMapper nominationDtoMapper,
                              CompetitionDtoMapper competitionDtoMapper) {
        this.resultService = resultService;
        this.competitionService = competitionService;
        this.nominationDtoMapper = nominationDtoMapper;
        this.competitionDtoMapper = competitionDtoMapper;
    }

    @GET
    public NominationContainerDto getNominations(@QueryParam("year") @NotNull Integer year,
                                      @QueryParam("categoryId") Long categoryId,
                                      @QueryParam("time") Double time,
                                      @QueryParam("participationCount") Integer participationCount) {
        List<NominationDto> nominationList = resultService.getNominations(year, categoryId)
                .stream()
                .map(nominationDtoMapper::map)
                .collect(Collectors.toList());
        List<CompetitionDto> competitionList = competitionService.getCompetitions(year)
                .stream()
                .map(competitionDtoMapper::map)
                .collect(Collectors.toList());
        return new NominationContainerDto(competitionList, nominationList);
    }


}
