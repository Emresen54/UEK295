package ch.scbe.productstore.resources.category;

import ch.scbe.productstore.resources.category.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "active", target = "active")
    Category toEntity(CategoryCreateDto categoryCreateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    CategoryShowDto toShowDto(Category category);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "active", target = "active")
    void update(CategoryCreateDto categoryCreateDto, @MappingTarget Category categoryToUpdate);
}
