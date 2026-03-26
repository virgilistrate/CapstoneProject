package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.TestDrive;
import virgilistrate.CapstoneProject.entities.User;
import virgilistrate.CapstoneProject.payloads.TestDriveDTO;
import virgilistrate.CapstoneProject.services.TestDriveService;

import java.util.List;

@RestController
@RequestMapping("/testdrives")
public class TestDriveController {

    private final TestDriveService testDriveService;

    public TestDriveController(TestDriveService testDriveService) {
        this.testDriveService = testDriveService;
    }

    @PostMapping
    public TestDrive createTestDrive(@RequestBody @Valid TestDriveDTO dto, Authentication authentication) {

        User userLogged = (User) authentication.getPrincipal();

        return testDriveService.createTestDrive(
                userLogged.getId(),
                dto.vehicleId()
        );
    }

    @GetMapping
    public List<TestDrive> getAll() {
        return testDriveService.getAll();
    }

    @GetMapping("/{id}")
    public TestDrive getById(@PathVariable Long id) {
        return testDriveService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        testDriveService.delete(id);
    }
}