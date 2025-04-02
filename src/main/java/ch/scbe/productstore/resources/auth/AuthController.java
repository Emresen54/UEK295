package ch.scbe.productstore.resources.auth;

import ch.scbe.productstore.resources.auth.dto.LoginRequestDto;
import ch.scbe.productstore.resources.auth.dto.TokenWrapper;
import ch.scbe.productstore.resources.user.Users;
import ch.scbe.productstore.resources.user.UsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import ch.scbe.productstore.resources.auth.security.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public TokenWrapper login(@RequestBody LoginRequestDto dto) {
        Users user = usersService.getUserWithCredentials(dto.getUsername(), dto.getPassword());
        String token = tokenService.generateToken(user);
        return new TokenWrapper(token);
    }
}
