package ch.scbe.productstore.resources.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Users create(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public Users getById(Long id) {
        return this.usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with the id " + id + " not found"));
    }

    public List<Users> getAll() {
        return (List<Users>) this.usersRepository.findAll();
    }

    @Override
    public Users update(Long id, Users user) {
        Users existing = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existing.setUsername(user.getUsername());
        existing.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(existing);
    }

    public void delete(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Users getUserWithCredentials(String username, String password) {
        Users user = this.usersRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }

    @Override
    public Users findUserByUsername(String username) {
        Users user = this.usersRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }
}
