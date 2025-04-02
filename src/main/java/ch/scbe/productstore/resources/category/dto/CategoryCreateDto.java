package ch.scbe.productstore.resources.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO used to create a new category")
public class CategoryCreateDto {

    @Schema(description = "Name of the category", example = "Phones")
    private String name;

    @Schema(description = "Show if the category is active", example = "true")
    private Boolean active;

    @Schema(description = "ID of the parent category if there is a subcategory", example = "1")
    private Long parentCategoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
