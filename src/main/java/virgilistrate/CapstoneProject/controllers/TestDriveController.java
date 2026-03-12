package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.TestDrive;
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
    public TestDrive createTestDrive(@RequestBody @Valid TestDriveDTO dto) {
        return testDriveService.createTestDrive(
                dto.clientId(),
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