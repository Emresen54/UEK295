package ch.scbe.productstore.resources.product;

import ch.scbe.productstore.resources.category.Category;
import ch.scbe.productstore.resources.category.CategoryRepository;
import ch.scbe.productstore.resources.product.dto.ProductShowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ch.scbe.productstore.resources.product.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductShowDto create(ProductCreateDto dto) {
        Product product = productMapper.toEntity(dto);
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        return productMapper.toShowDto(productRepository.save(product));
    }

    @Override
    public ProductDetailDto getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.toDetailDto(product);
    }

    @Override
    public List<ProductShowDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toShowDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, ProductUpdateDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productMapper.update(dto, product);

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);

        productRepository.save(product);
    }


    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
