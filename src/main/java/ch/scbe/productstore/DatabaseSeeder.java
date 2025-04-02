package ch.scbe.productstore;

import ch.scbe.productstore.resources.user.Users;
import ch.scbe.productstore.resources.user.UsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seedDatabase() {
        if (usersRepository.count() == 0) {
            Users admin = new Users(
                    "admin",
                    passwordEncoder.encode("admin123"),
                    List.of("ADMIN")
            );

            Users user = new Users(
                    "user",
                    passwordEncoder.encode("123"),
                    List.of("USER")
            );

            usersRepository.saveAll(List.of(admin, user));
            System.out.println("Default users inserted with live hashed passwords.");
        } else {
            System.out.println("Users already exist. Skipping seeding.");
        }
    }
}