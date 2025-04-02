package ch.scbe.productstore.resources.auth.dto;

public class TokenWrapper {

    private String token;

    // Constructor
    public TokenWrapper(String token) {
        this.token = token;
    }

    // Getters & Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
