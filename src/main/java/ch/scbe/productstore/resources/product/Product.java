package ch.scbe.productstore.resources.product;

import ch.scbe.productstore.resources.category.Category;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String sku;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean active;

    @Column(columnDefinition = "VARCHAR(500)")
    private String name;

    @Column(columnDefinition = "VARCHAR(1000)")
    private String image;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    @Column(columnDefinition = "FLOAT")
    private float price;

    @Column(columnDefinition = "INTEGER")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {}

    // Constructor
    public Product(String sku, Boolean active, String name, String image, String description, float price, int stock, Category category) {
        this.sku = sku;
        this.active = active;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
