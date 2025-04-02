package ch.scbe.productstore.resources.user;

import ch.scbe.productstore.resources.user.dto.UserCreateDto;
import ch.scbe.productstore.resources.user.dto.UserShowDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Users Controller", description = "Controller for manage the users")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UsersService usersService;

    @Autowired
    UserMapper userMapper;

    // Alle Benutzer anzeigen
    @Operation(summary = "Get all users", description = "Returns a list with all users")
    @ApiResponse(responseCode = "200", description = "Users found!")
    @GetMapping
    public List<UserShowDto> getAllUsers() {
        List<Users> allUsers = usersService.getAll();
        List<UserShowDto> dtos = allUsers.stream().map(user -> userMapper.toShowDto(user)).collect(Collectors.toList());
        return dtos;
    }

    // Benutzer nach ID anzeigen
    @Operation(summary = "Get the user by ID", description = "Returns a user by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found!"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid user ID")
    })
    @GetMapping("/{id}")
    public UserShowDto getUserById(@PathVariable Long id) {
        Users user = usersService.getById(id);
        return userMapper.toShowDto(user);
    }

    // Neuen Benutzer erstellen
    @Operation(summary = "Create new user", description = "Create a new user")
    @ApiResponse(responseCode = "201", description = "User created!")
    @PostMapping
    public UserShowDto createUser(@RequestBody UserCreateDto dto) {
        Users user = userMapper.toEntity(dto);
        Users createdUser = usersService.create(user);
        return userMapper.toShowDto(createdUser);
    }

    // Benutzer aktualisieren
    @Operation(summary = "Update user", description = "Update an existing user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User updated!"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid user data")
    })
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserCreateDto dto) {
        Users user = usersService.getById(id);
        userMapper.update(dto, user);
        usersService.update(id, user);
    }

    // Benutzer löschen
    @Operation(summary = "Delete user", description = "Delete a user by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User deleted!"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid user ID")
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersService.delete(id);
    }

    // Benutzer zu Admin befördern
    @Operation(summary = "Promote user to ADMIN", description = "Adds the ADMIN role to a user")
    @ApiResponse(responseCode = "200", description = "User promoted successfully")
    @PutMapping("/{id}/promote")
    @PreAuthorize("hasRole('ADMIN')")
    public void promoteToAdmin(@PathVariable Long id) {
        Users user = usersService.getById(id);
        List<String> roles = user.getRoles();

        if (!roles.contains("ADMIN")) {
            roles.add("ADMIN");
            user.setRoles(roles);
            usersService.update(id, user);
        }
    }
}
