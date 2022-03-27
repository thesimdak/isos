package cz.svetsplhu.isos.rest.model.mapper;

import cz.svetsplhu.isos.repository.model.CompetitionEntity;
import cz.svetsplhu.isos.rest.model.CompetitionDto;
import cz.svetsplhu.isos.service.model.Competition;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface CompetitionDtoMapper {

    Competition map(
            CompetitionDto competitionDto
    );

    CompetitionDto map(
            Competition competition
    );
}