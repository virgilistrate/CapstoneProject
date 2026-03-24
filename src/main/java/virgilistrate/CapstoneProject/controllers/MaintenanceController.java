package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Maintenance;
import virgilistrate.CapstoneProject.payloads.MaintenanceDTO;
import virgilistrate.CapstoneProject.payloads.MaintenancePatchDTO;
import virgilistrate.CapstoneProject.services.MaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@CrossOrigin(origins = "http://localhost:5173")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    public Maintenance createMaintenance(@RequestBody @Valid MaintenanceDTO dto) {
        Maintenance maintenance = new Maintenance();
        maintenance.setType(dto.type());
        maintenance.setDate(dto.date());
        maintenance.setKm(dto.km());

        return maintenanceService.createMaintenance(
                maintenance,
                dto.vehicleId()
        );
    }

    @PatchMapping("/{id}")
    public Maintenance patchMaintenance(@PathVariable Long id, @RequestBody MaintenancePatchDTO dto) {
        return maintenanceService.patchMaintenance(id, dto);
    }

    @GetMapping
    public List<Maintenance> getAll() {
        return maintenanceService.getAllMaintenances();
    }

    @GetMapping("/{id}")
    public Maintenance getById(@PathVariable Long id) {
        return maintenanceService.getMaintenanceById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
    }
}