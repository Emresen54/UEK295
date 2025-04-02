package ch.scbe.productstore.resources.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Repository
@RepositoryRestResource(exported = false)
public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}