package cz.svetsplhu.isos.rest;

import cz.svetsplhu.isos.rest.model.NominationDto;
import cz.svetsplhu.isos.rest.model.mapper.NominationDtoMapper;
import cz.svetsplhu.isos.service.ResultService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
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
    private final NominationDtoMapper nominationDtoMapper;

    @Inject
    public NominationResource(ResultService resultService, NominationDtoMapper nominationDtoMapper) {
        this.resultService = resultService;
        this.nominationDtoMapper = nominationDtoMapper;
    }

    @GET
    public List<NominationDto> get(@QueryParam("year") Integer year,
                                   @QueryParam("categoryId") Long categoryId,
                                   @QueryParam("time") Double time,
                                   @QueryParam("participationCount") Integer participationCount) {
        return resultService.getNominations(year, categoryId, time, participationCount)
                .stream()
                .map(nominationDtoMapper::map)
                .collect(Collectors.toList());
    }


}
