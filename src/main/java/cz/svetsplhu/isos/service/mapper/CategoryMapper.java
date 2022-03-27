package cz.svetsplhu.isos.service.mapper;

import cz.svetsplhu.isos.repository.model.CategoryEntity;
import cz.svetsplhu.isos.service.model.Category;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface CategoryMapper {

    Category map(
            CategoryEntity categoryEntity
    );
    CategoryEntity map(
            Category category
    );
}