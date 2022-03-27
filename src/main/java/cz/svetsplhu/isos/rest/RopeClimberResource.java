package cz.svetsplhu.isos.rest;

import cz.svetsplhu.isos.rest.model.RopeClimberDto;
import cz.svetsplhu.isos.rest.model.mapper.RopeClimberDtoMapper;
import cz.svetsplhu.isos.service.RopeClimberService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Resource for rope climber.
 */
@Produces(MediaType.APPLICATION_JSON)
@Component
@Path("/rope-climbers")
public class RopeClimberResource {

    private final RopeClimberService ropeClimberService;
    private final RopeClimberDtoMapper ropeClimberDtoMapper;

    @Inject
    public RopeClimberResource(RopeClimberService ropeClimberService, RopeClimberDtoMapper ropeClimberDtoMapper) {
        this.ropeClimberService = ropeClimberService;
        this.ropeClimberDtoMapper = ropeClimberDtoMapper;
    }

    @GET
    public List<RopeClimberDto> getAll() {
        return ropeClimberService.getAll().stream()
                .map(ropeClimberDtoMapper::map)
                .collect(Collectors.toList());
    }
}
