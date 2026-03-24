package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.Maintenance;
import virgilistrate.CapstoneProject.entities.Vehicle;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.payloads.MaintenancePatchDTO;
import virgilistrate.CapstoneProject.repositories.MaintenanceRepository;
import virgilistrate.CapstoneProject.repositories.VehicleRepository;

import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;

    public MaintenanceService(
            MaintenanceRepository maintenanceRepository,
            VehicleRepository vehicleRepository
    ) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Maintenance createMaintenance(
            Maintenance maintenance,
            Long vehicleId
    ) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        maintenance.setVehicle(vehicle);

        return maintenanceRepository.save(maintenance);
    }

    public Maintenance patchMaintenance(Long id, MaintenancePatchDTO dto) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Maintenance not found"));

        if (dto.type() != null) maintenance.setType(dto.type());
        if (dto.date() != null) maintenance.setDate(dto.date());
        if (dto.km() != null) maintenance.setKm(dto.km());

        if (dto.vehicleId() != null) {
            Vehicle vehicle = vehicleRepository.findById(dto.vehicleId())
                    .orElseThrow(() -> new NotFoundException("Vehicle not found"));
            maintenance.setVehicle(vehicle);
        }

        return maintenanceRepository.save(maintenance);
    }

    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    public Maintenance getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Maintenance not found"));
    }

    public void deleteMaintenance(Long id) {
        Maintenance maintenance = getMaintenanceById(id);
        maintenanceRepository.delete(maintenance);
    }
}