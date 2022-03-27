package cz.svetsplhu.isos.rest.model.mapper;

import cz.svetsplhu.isos.rest.model.RopeClimberDto;
import cz.svetsplhu.isos.service.model.RopeClimber;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface RopeClimberDtoMapper {

    RopeClimber map(
            RopeClimberDto ropeClimberDto
    );

    RopeClimberDto map(
            RopeClimber ropeClimber
    );
}