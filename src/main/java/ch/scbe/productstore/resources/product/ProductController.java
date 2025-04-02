package ch.scbe.productstore.resources.product;

import ch.scbe.productstore.resources.product.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Controller", description = "Manage all product operations")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // Konstruktor-Injektion (statt @Autowired)
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 🔹 GET all products
    @Operation(summary = "Get all products", description = "Returns a list of all products")
    @ApiResponse(responseCode = "200", description = "Products found")
    @GetMapping
    public List<ProductShowDto> getAllProducts() {
        return productService.getAll();
    }

    // 🔹 GET product by ID
    @Operation(summary = "Get product by ID", description = "Returns a product matching the ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid product ID")
    })
    @GetMapping("/{id}")
    public ProductDetailDto getProductById(
            @Parameter(description = "ID of the product", example = "1")
            @PathVariable Long id
    ) {
        return productService.getById(id);
    }

    // 🔹 POST create new product
    @Operation(summary = "Create product", description = "Creates a new product")
    @ApiResponse(responseCode = "201", description = "Product created")
    @PostMapping
    public ProductShowDto createProduct(@RequestBody ProductCreateDto dto) {
        return productService.create(dto);
    }

    // 🔹 PUT update product
    @Operation(summary = "Update product", description = "Updates an existing product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product updated"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid product data")
    })
    @PutMapping("/{id}")
    public void updateProduct(
            @Parameter(description = "ID of the product to update", example = "1")
            @PathVariable Long id,
            @RequestBody ProductUpdateDto dto
    ) {
        productService.update(id, dto);
    }

    // 🔹 DELETE product
    @Operation(summary = "Delete product", description = "Deletes a product by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid product ID")
    })
    @DeleteMapping("/{id}")
    public void deleteProduct(
            @Parameter(description = "ID of the product to delete", example = "1")
            @PathVariable Long id
    ) {
        productService.delete(id);
    }
}
