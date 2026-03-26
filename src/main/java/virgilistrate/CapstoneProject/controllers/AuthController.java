package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.User;
import virgilistrate.CapstoneProject.payloads.LoginDTO;
import virgilistrate.CapstoneProject.payloads.ResponseDTO;
import virgilistrate.CapstoneProject.payloads.UserDTO;
import virgilistrate.CapstoneProject.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody @Valid LoginDTO body) {
        return new ResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Valid UserDTO body) {
        return this.authService.register(body);
    }
}