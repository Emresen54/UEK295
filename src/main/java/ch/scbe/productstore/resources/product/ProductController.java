package ch.scbe.productstore.resources.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ch.scbe.productstore.resources.product.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Tag(name = "Product Controller", description = "Controller for manage the products")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(
            summary = "Get all products",
            description = "Returns a list with all the products"
    )
    @ApiResponse(responseCode = "200", description = "Products found!")
    @GetMapping
    public List<ProductShowDto> getAllProducts() {
        return productService.getAll();
    }

    @Operation(
            summary = "Get a product by its ID",
            description = "Returns a product by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found!"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid product ID")
    })
    @GetMapping("/{id}")
    public ProductDetailDto getProductById(
            @Parameter(description = "ID of the product to give back", example = "1")
            @PathVariable Long id
    ) {
        return productService.getById(id);
    }

    @Operation(
            summary = "Create new product",
            description = "Create a new product"
    )
    @ApiResponse(responseCode = "201", description = "Product created!")
    @PostMapping
    public ProductShowDto createProduct(@RequestBody ProductCreateDto dto) {
        return productService.create(dto);
    }

    @Operation(
            summary = "Update a product",
            description = "Update an existing product"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product updated!"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid product data")
    })
    @PutMapping("/{id}")
    public void updateProduct(
            @Parameter(description = "ID of the product you want to update", example = "1")
            @PathVariable Long id,
            @RequestBody ProductUpdateDto dto
    ) {
        productService.update(id, dto);
    }

    @Operation(
            summary = "Delete a product",
            description = "Delete a product by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product deleted!"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid product ID")
    })
    @DeleteMapping("/{id}")
    public void deleteProduct(
            @Parameter(description = "ID of the product you want to delete", example = "1")
            @PathVariable Long id
    ) {
        productService.delete(id);
    }
}
