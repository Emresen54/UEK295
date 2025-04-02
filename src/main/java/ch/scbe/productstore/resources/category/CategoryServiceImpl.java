package ch.scbe.productstore.resources.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(Category category) {
        return this.categoryRepository.save(category);
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category update(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
        existingCategory.setName(category.getName());
        existingCategory.setActive(category.getActive());
        existingCategory.setParentCategory(category.getParentCategory());

        return this.categoryRepository.save(existingCategory);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
