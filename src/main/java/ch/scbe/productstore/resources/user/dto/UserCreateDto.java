package ch.scbe.productstore.resources.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO used to create a new user")
public class UserCreateDto {

    @Schema(description = "Username for the user", example = "Emre")
    private String username;

    @Schema(description = "Password for the user", example = "123")
    private String password;

    @Schema(description = "Roles for the user", example = "[USER]")
    private List<String> roles;

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
