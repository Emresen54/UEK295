package ch.scbe.productstore.resources.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO used to show a category")
public class CategoryShowDto {

    @Schema(description = "ID of the category", example = "1")
    private Long id;

    @Schema(description = "Name of the category", example = "Phones")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
