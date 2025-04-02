package ch.scbe.productstore.resources.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO used to create a new product")
public class ProductCreateDto {

    @NotBlank
    @Schema(description = "Name of the product", example = "iPhone 15")
    private String name;

    @Schema(description = "Description of the product", example = "New Iphone 15 with 128GB and A12 Chip")
    private String description;

    @Schema(description = "Image URL of the product", example = "https://example.com/image.png")
    private String image;

    @NotNull
    @Schema(description = "Price of the product", example = "599.99")
    private float price;

    @NotNull
    @Schema(description = "Stock of the product", example = "100")
    private int stock;

    @Schema(description = "Product SKU", example = "KS249SK")
    private String sku;

    @NotNull
    @Schema(description = "ID of the category this product belongs to", example = "1")
    private Long categoryId;

    @NotNull
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
