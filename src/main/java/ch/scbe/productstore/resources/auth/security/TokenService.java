package ch.scbe.productstore.resources.auth.security;

import ch.scbe.productstore.resources.user.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class TokenService {

    private SecretKey SECRET_KEY;

    private final String RAW_SECRET = "MySuperSecretKey12345678901234567890";

    @PostConstruct
    public void init() {
        byte[] keyBytes = RAW_SECRET.getBytes(StandardCharsets.UTF_8);
        this.SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Users user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date()) // Date creation the token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10h for expiration the token
                .signWith(SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<String> extractRoles(String token) {
        return (List<String>) Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles");
    }
}
