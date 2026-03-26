package virgilistrate.CapstoneProject.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.User;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("/me")
    public User getMyProfile(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}