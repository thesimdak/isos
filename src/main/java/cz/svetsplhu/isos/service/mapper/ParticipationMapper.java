package cz.svetsplhu.isos.service.mapper;

import cz.svetsplhu.isos.repository.model.ParticipationEntity;
import cz.svetsplhu.isos.service.model.Participation;
import cz.svetsplhu.isos.service.model.Time;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ParticipationMapper {

    @Mappings({
            @Mapping(target = "ropeClimber.participationList", ignore = true),
            @Mapping(target = "competition.participationList", ignore = true)
    })
    Participation map(
            ParticipationEntity participationEntity
    );

    @Mappings({
            @Mapping(target = "ropeClimber.participationList", ignore = true),
            @Mapping(target = "competition.participationList", ignore = true)
    })
    ParticipationEntity map(
            Participation participation
    );

}