package virgilistrate.CapstoneProject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulente")
public class ConsulenteController {

    @GetMapping("/dashboard")
    public String consulenteDashboard() {
        return "Benvenuto nell'area consulente";
    }
}