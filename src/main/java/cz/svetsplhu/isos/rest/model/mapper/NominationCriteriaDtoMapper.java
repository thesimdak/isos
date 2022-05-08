package cz.svetsplhu.isos.rest.model.mapper;

import cz.svetsplhu.isos.rest.model.NominationCriteriaDto;
import cz.svetsplhu.isos.rest.model.NominationDto;
import cz.svetsplhu.isos.service.model.Nomination;
import cz.svetsplhu.isos.service.model.NominationCriteria;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface NominationCriteriaDtoMapper {

    NominationCriteria map(
            NominationCriteriaDto nominationCriteriaDto
    );

    NominationCriteriaDto map(
            NominationCriteria nominationCriteria
    );
}