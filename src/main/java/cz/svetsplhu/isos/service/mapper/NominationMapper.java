package cz.svetsplhu.isos.service.mapper;

import cz.svetsplhu.isos.repository.model.NominationEntity;
import cz.svetsplhu.isos.service.model.Nomination;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface NominationMapper {

    Nomination map(
            NominationEntity nominationEntity
    );

    NominationEntity map(
            Nomination nomination
    );
}