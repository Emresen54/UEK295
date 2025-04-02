package ch.scbe.productstore.resources.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Login request DTO having the username and password")
public class LoginRequestDto {

    @NotBlank
    @Schema(description = "Username of the user", example = "admin")
    private String username;

    @NotBlank
    @Schema(description = "Password of the user", example = "admin123")
    private String password;

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
}
