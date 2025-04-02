package ch.scbe.productstore.resources.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Repository
@RepositoryRestResource(exported = false)
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrue();
}