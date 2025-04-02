package ch.scbe.productstore.resources.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO used to update an existing product")
public class ProductUpdateDto {

    @Schema(description = "Name of the product", example = "iPhone 15")
    private String name;

    @Schema(description = "Description of the product", example = "Updated product with another name")
    private String description;

    @Schema(description = "Image URL of the product", example = "https://example.com/image.png")
    private String image;

    @Schema(description = "Price of the product", example = "599.99")
    private float price;

    @Schema(description = "Stock available", example = "100")
    private int stock;

    @Schema(description = "Product SKU", example = "KS249SK")
    private String sku;

    @Schema(description = "ID of the category this product belongs to", example = "1")
    private Long categoryId;

    @Schema(description = "Show if the product is active", example = "true")
    private Boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
