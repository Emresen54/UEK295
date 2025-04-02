package ch.scbe.productstore.resources.category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    Category getById(Long id);
    List<Category> getAll();
    Category update(Long id, Category category);
    void delete(Long id);
}
