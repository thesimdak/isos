package cz.svetsplhu.isos.rest.model.mapper;

import cz.svetsplhu.isos.rest.model.CategoryDto;
import cz.svetsplhu.isos.service.model.Category;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface CategoryDtoMapper {

    Category map(
            CategoryDto categoryDto
    );

    CategoryDto map(
            Category category
    );
}