package ch.scbe.productstore.resources.user;

import java.util.List;

public interface UsersService {
    Users create(Users user);
    Users getById(Long id);
    List<Users> getAll();
    Users update(Long id, Users user);
    void delete(Long id);

    Users getUserWithCredentials(String username, String password);
    Users findUserByUsername(String username);
}
