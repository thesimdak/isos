package cz.svetsplhu.isos.service.mapper;

import cz.svetsplhu.isos.repository.model.RopeClimberEntity;
import cz.svetsplhu.isos.service.model.RopeClimber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring"
)
public interface RopeClimberMapper {

    @Mappings({
            @Mapping(target = "participationList", ignore = true)
    })
    RopeClimber map(
            RopeClimberEntity ropeClimberEntity
    );

    @Mappings({
            @Mapping(target = "participationList", ignore = true)
    })
    RopeClimberEntity map(
            RopeClimber ropeClimber
    );
}