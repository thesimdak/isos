package cz.svetsplhu.isos.service.mapper;

import cz.svetsplhu.isos.repository.model.CompetitionEntity;
import cz.svetsplhu.isos.service.model.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring"
)
public interface CompetitionMapper {

    @Mappings({
            @Mapping(target="participationList", ignore = true)
    })
    Competition map(
            CompetitionEntity competitionEntity
    );

    @Mappings({
            @Mapping(target="participationList", ignore = true)
    })
    CompetitionEntity map(
            Competition competition
    );
}