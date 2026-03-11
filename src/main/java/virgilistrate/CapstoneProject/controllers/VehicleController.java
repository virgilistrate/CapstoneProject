package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Vehicle;
import virgilistrate.CapstoneProject.payloads.VehicleDTO;
import virgilistrate.CapstoneProject.services.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody @Valid VehicleDTO dto){

        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber(dto.plateNumber());
        vehicle.setPrice(dto.price());
        vehicle.setYearOfConstruction(dto.yearOfConstruction());
        vehicle.setKilometers(dto.kilometers());
        vehicle.setColor(dto.color());
        vehicle.setFuelType(dto.fuelType());

        return vehicleService.createVehicle(
                vehicle,
                dto.brandId(),
                dto.modelId(),
                dto.bodyTypeId(),
                dto.optionalIds()
        );
    }

    @GetMapping
    public List<Vehicle> getAll(){
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable Long id){
        return vehicleService.getVehicleById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
    }
}