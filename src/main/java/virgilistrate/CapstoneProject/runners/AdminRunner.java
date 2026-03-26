package virgilistrate.CapstoneProject.runners;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import virgilistrate.CapstoneProject.entities.User;
import virgilistrate.CapstoneProject.enums.Role;
import virgilistrate.CapstoneProject.repositories.UserRepository;

@Component
public class AdminRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.surname}")
    private String adminSurname;

    @Value("${admin.phone}")
    private String adminPhone;

    public AdminRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = new User();
            admin.setName(adminName);
            admin.setSurname(adminSurname);
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setPhoneNumber(adminPhone);
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
        }
    }
}