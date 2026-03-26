package virgilistrate.CapstoneProject.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.User;
import virgilistrate.CapstoneProject.enums.Role;
import virgilistrate.CapstoneProject.exceptions.UnauthorizedException;
import virgilistrate.CapstoneProject.payloads.LoginDTO;
import virgilistrate.CapstoneProject.payloads.UserDTO;
import virgilistrate.CapstoneProject.repositories.UserRepository;
import virgilistrate.CapstoneProject.security.JWTSecret;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JWTSecret jwtSecret;
    private final PasswordEncoder bcrypt;

    public AuthService(UserRepository userRepository, JWTSecret jwtSecret, PasswordEncoder bcrypt) {
        this.userRepository = userRepository;
        this.jwtSecret = jwtSecret;
        this.bcrypt = bcrypt;
    }

    public User register(UserDTO body) {

        if (userRepository.existsByEmail(body.email())) {
            throw new UnauthorizedException("Email già registrata!");
        }

        User user = new User();
        user.setName(body.name());
        user.setSurname(body.surname());
        user.setEmail(body.email());
        user.setPassword(bcrypt.encode(body.password()));
        user.setPhoneNumber(body.phoneNumber());
        user.setRole(Role.CLIENT);

        return userRepository.save(user);
    }

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        User found = userRepository.findByEmail(body.email())
                .orElseThrow(() -> new UnauthorizedException("Credenziali errate!"));

        if (bcrypt.matches(body.password(), found.getPassword())) {
            return jwtSecret.generateToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}