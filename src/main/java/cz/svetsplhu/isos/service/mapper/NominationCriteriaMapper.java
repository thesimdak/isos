package cz.svetsplhu.isos.service.mapper;

import cz.svetsplhu.isos.repository.model.NominationCriteriaEntity;
import cz.svetsplhu.isos.service.model.NominationCriteria;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface NominationCriteriaMapper {

    NominationCriteria map(
            NominationCriteriaEntity nominationCriteriaEntity
    );

    NominationCriteriaEntity map(
            NominationCriteria nominationCriteria
    );
}