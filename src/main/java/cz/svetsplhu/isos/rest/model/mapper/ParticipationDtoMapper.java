package cz.svetsplhu.isos.rest.model.mapper;

import cz.svetsplhu.isos.rest.model.ParticipationDto;
import cz.svetsplhu.isos.service.model.Participation;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface ParticipationDtoMapper {

    Participation map(
            ParticipationDto participationDto
    );

    ParticipationDto map(
            Participation participation
    );
}