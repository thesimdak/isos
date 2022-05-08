package cz.svetsplhu.isos.rest;

import cz.svetsplhu.isos.rest.model.NominationCriteriaDto;
import cz.svetsplhu.isos.rest.model.mapper.NominationCriteriaDtoMapper;
import cz.svetsplhu.isos.service.NominationCriteriaService;
import cz.svetsplhu.isos.service.model.NominationCriteria;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by M on 14.05.2017.
 */

@Path("/nomination-criterias")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class NominationCriteriaResource {

    private final NominationCriteriaService nominationCriteriaService;
    private final NominationCriteriaDtoMapper nominationCriteriaDtoMapper;

    public NominationCriteriaResource(NominationCriteriaService nominationCriteriaService,
                                      NominationCriteriaDtoMapper nominationCriteriaDtoMapper) {
        this.nominationCriteriaService = nominationCriteriaService;
        this.nominationCriteriaDtoMapper = nominationCriteriaDtoMapper;
    }

    @GET
    @Path("seasons")
    public List<Integer> getNominationCriteriaSeasons() {
        return nominationCriteriaService.getNominationCriteriaSeasons();
    }

    @GET
    @Path("/")
    public List<NominationCriteriaDto> getNominationCriteriasBySeasons(@QueryParam("season") Integer season) {
        List<NominationCriteria> nominationCriteria = nominationCriteriaService.getNominationCriteria(season);
        return nominationCriteria.stream().map(nominationCriteriaDtoMapper::map).collect(Collectors.toList());
    }

    @POST
    @Path("/")
    public NominationCriteriaDto createNominationCriterias(NominationCriteriaDto nominationCriteriaDto) {
        NominationCriteria nominationCriteria = nominationCriteriaDtoMapper.map(nominationCriteriaDto);
        return nominationCriteriaDtoMapper.map(nominationCriteriaService.saveNominationCriteria(nominationCriteria));
    }

    @PUT
    @Path("/{nominationCriteriaId}")
    public NominationCriteriaDto updateNominationCriterias(@PathParam("nominationCriteriaId") Long nominationCriteriaId,
                                                           NominationCriteriaDto nominationCriteriaDto) {
        nominationCriteriaDto.setId(nominationCriteriaId);

        NominationCriteria nominationCriteria = nominationCriteriaDtoMapper.map(nominationCriteriaDto);
        return nominationCriteriaDtoMapper.map(nominationCriteriaService.saveNominationCriteria(nominationCriteria));
    }


}
