package ch.scbe.productstore.resources.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ch.scbe.productstore.resources.category.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Category Controller", description = "Controller for manage the categories")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Operation(
            summary = "Get all categories",
            description = "Returns a list with all the categories"
    )
    @ApiResponse(responseCode = "200", description = "All categories found!")
    @GetMapping
    public List<CategoryShowDto> getAllCategories() {
        return categoryService.getAll().stream().map(categoryMapper::toShowDto).collect(Collectors.toList());
    }

    @Operation(
            summary = "Get category by its ID",
            description = "Returns the category with the specified ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category found!"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "400", description = "Invalid category ID")
    })
    @GetMapping("/{id}")
    public CategoryShowDto getCategoryById(
            @Parameter(description = "ID of the category to give back", example = "1")
            @PathVariable Long id
    ) {
        return categoryMapper.toShowDto(categoryService.getById(id));
    }

    @Operation(
            summary = "Create new category",
            description = "Create a new category"
    )
    @ApiResponse(responseCode = "201", description = "Category created!")
    @PostMapping
    public CategoryShowDto createCategory(@RequestBody CategoryCreateDto dto) {
        Category category = categoryMapper.toEntity(dto);

        if (dto.getParentCategoryId() != null) {
            Category parent = categoryService.getById(dto.getParentCategoryId());
            category.setParentCategory(parent);
        }

        return categoryMapper.toShowDto(categoryService.create(category));
    }

    @Operation(
            summary = "Update a category",
            description = "Update an existing category by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category updated!"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "400", description = "Invalid category data")
    })
    @PutMapping("{id}")
    public void updateCategory(
            @Parameter(description = "ID of the category you want to update", example = "1")
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

    @Operation(
            summary = "Delete a category",
            description = "Delete a existing category by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category deleted!"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "400", description = "Invalid category ID")
    })
    @DeleteMapping("{id}")
    public void deleteCategory(
            @Parameter(description = "ID of the category you want to delete", example = "1")
            @PathVariable Long id
    ) {
        categoryService.delete(id);
    }
}
