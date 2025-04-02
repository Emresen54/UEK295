package ch.scbe.productstore.resources.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO used to show the preview of the product")
public class ProductShowDto {

    @Schema(description = "ID of the product", example = "1")
    private Long id;

    @Schema(description = "Name of the product", example = "iPhone 15")
    private String name;

    @Schema(description = "Price of the product", example = "599.99")
    private float price;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
