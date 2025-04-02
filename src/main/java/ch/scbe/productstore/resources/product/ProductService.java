package ch.scbe.productstore.resources.product;

import ch.scbe.productstore.resources.product.dto.*;
import java.util.List;

public interface ProductService {
    ProductShowDto create(ProductCreateDto dto);
    ProductDetailDto getById(Long id);
    List<ProductShowDto> getAll();
    void update(Long id, ProductUpdateDto dto);
    void delete(Long id);
}
