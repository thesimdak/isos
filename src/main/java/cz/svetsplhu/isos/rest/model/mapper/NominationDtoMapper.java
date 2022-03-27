package cz.svetsplhu.isos.rest.model.mapper;

import cz.svetsplhu.isos.rest.model.NominationDto;
import cz.svetsplhu.isos.service.model.Nomination;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface NominationDtoMapper {

    Nomination map(
            NominationDto nominationDto
    );

    NominationDto map(
            Nomination nomination
    );
}