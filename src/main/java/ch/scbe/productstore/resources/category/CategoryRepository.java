    package ch.scbe.productstore.resources.category;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import org.springframework.data.rest.core.annotation.RepositoryRestResource;

    @Repository
    @RepositoryRestResource(exported = false)
    public interface CategoryRepository extends JpaRepository<Category, Long> {
        Category findByName(String name);
    }