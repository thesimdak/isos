package cz.svetsplhu.isos.service.mapper;

import cz.svetsplhu.isos.repository.model.TimeEntity;
import cz.svetsplhu.isos.service.model.Time;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(
        componentModel = "spring"
)
public interface TimeMapper {

    Time map(
            TimeEntity timeEntity
    );

    TimeEntity map(
            Time time
    );
}