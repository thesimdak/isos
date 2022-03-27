package cz.svetsplhu.isos.rest;

import cz.svetsplhu.isos.rest.model.ParticipationDto;
import cz.svetsplhu.isos.rest.model.mapper.ParticipationDtoMapper;
import cz.svetsplhu.isos.service.ResultService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by M on 14.05.2017.
 */

@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Component
@Path("/result")
public class ResultResource {
    public static final Logger LOGGER = LoggerFactory.getLogger(ResultResource.class);

    private final ResultService resultService;
    private final ParticipationDtoMapper participationDtoMapper;

    @Inject
    public ResultResource(ResultService resultService, ParticipationDtoMapper participationDtoMapper) {
        this.resultService = resultService;
        this.participationDtoMapper = participationDtoMapper;
    }

    @POST
    @Consumes(value = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response upload(@FormDataParam("myfile") InputStream fileInputStream,
                           @FormDataParam("myfile") FormDataContentDisposition fileDisposition) {
        try {
            resultService.load(fileInputStream);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();

    }


    @Path("/{competitionId}")
    @DELETE
    public Response delete(@PathParam("competitionId") Long competitionId) {
        try {
            resultService.delete(competitionId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();

    }

    @GET
    @Path("/top/{id}")
    public List<ParticipationDto> getTop(@PathParam("id") Long id) {
        return resultService.getTopForCategory(id)
                .stream()
                .map(participationDtoMapper::map)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{competitionId}/{categoryId}")
    public List<ParticipationDto> getResults(@PathParam("competitionId") Long competitionId,
                                      @PathParam("categoryId") Long categoryId) {
        return resultService.getParticipations(competitionId, categoryId)
                .stream()
                .map(participationDtoMapper::map)
                .collect(Collectors.toList());

    }


}
