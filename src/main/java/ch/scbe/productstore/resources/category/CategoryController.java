package ch.scbe.productstore.resources.category;

import ch.scbe.productstore.resources.category.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Category Controller", description = "Manage all category operations")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    // Konstruktor-Injektion statt @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    // 🔹 GET all categories
    @Operation(summary = "Get all categories", description = "Returns a list of all categories")
    @ApiResponse(responseCode = "200", description = "Categories found")
    @GetMapping
    public List<CategoryShowDto> getAllCategories() {
        return categoryService.getAll()
                .stream()
                .map(categoryMapper::toShowDto)
                .collect(Collectors.toList());
    }

    // 🔹 GET category by ID
    @Operation(summary = "Get category by ID", description = "Returns a specific category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "400", description = "Invalid category ID")
    })
    @GetMapping("/{id}")
    public CategoryShowDto getCategoryById(
            @Parameter(description = "ID of the category", example = "1")
            @PathVariable Long id
    ) {
        return categoryMapper.toShowDto(categoryService.getById(id));
    }

    // 🔹 POST create new category
    @Operation(summary = "Create category", description = "Creates a new category")
    @ApiResponse(responseCode = "201", description = "Category created")
    @PostMapping
    public CategoryShowDto createCategory(@RequestBody CategoryCreateDto dto) {
        Category category = categoryMapper.toEntity(dto);

        if (dto.getParentCategoryId() != null) {
            Category parent = categoryService.getById(dto.getParentCategoryId());
            category.setParentCategory(parent);
        }

        return categoryMapper.toShowDto(categoryService.create(category));
    }

    // 🔹 PUT update category
    @Operation(summary = "Update category", description = "Updates an existing category")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category updated"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "400", description = "Invalid category data")
    })
    @PutMapping("/{id}")
    public void updateCategory(
            @Parameter(description = "ID of the category to update", example = "1")
            @PathVariable Long id,
            @RequestBody CategoryCreateDto dto
    ) {
        Category category = categoryService.getById(id);
        categoryMapper.update(dto, category);

        if (dto.getParentCategoryId() != null) {
            Category parent = categoryService.getById(dto.getParentCategoryId());
            category.setParentCategory(parent);
        }

        categoryService.update(id, category);
    }

    // 🔹 DELETE category
    @Operation(summary = "Delete category", description = "Deletes a category by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "400", description = "Invalid category ID")
    })
    @DeleteMapping("/{id}")
    public void deleteCategory(
            @Parameter(description = "ID of the category to delete", example = "1")
            @PathVariable Long id
    ) {
        categoryService.delete(id);
    }
}
