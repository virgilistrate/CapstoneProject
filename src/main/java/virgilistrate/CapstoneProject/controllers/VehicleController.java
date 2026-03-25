package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Vehicle;
import virgilistrate.CapstoneProject.payloads.VehicleDTO;
import virgilistrate.CapstoneProject.payloads.VehiclePatchDTO;
import virgilistrate.CapstoneProject.services.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = "http://localhost:5173")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody @Valid VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber(dto.plateNumber());
        vehicle.setPrice(dto.price());
        vehicle.setYearOfConstruction(dto.yearOfConstruction());
        vehicle.setKilometers(dto.kilometers());
        vehicle.setColor(dto.color());
        vehicle.setFuelType(dto.fuelType());
        vehicle.setSeats(dto.seats());
        vehicle.setDoorsNumber(dto.doorsNumber());
        vehicle.setEngineCapacity(dto.engineCapacity());
        vehicle.setEnginePower(dto.enginePower());
        vehicle.setEngineConsumption(dto.engineConsumption());
        vehicle.setTractiontype(dto.tractiontype());
        vehicle.setVehicleLength(dto.vehicleLength());
        vehicle.setVehicleWidth(dto.vehicleWidth());
        vehicle.setVehicleHeight(dto.vehicleHeight());
        vehicle.setTrunkSize(dto.trunkSize());
        vehicle.setEmissionsClass(dto.emissionsClass());
        vehicle.setCo2Emissions(dto.co2Emissions());

        return vehicleService.createVehicle(
                vehicle,
                dto.brandId(),
                dto.modelId(),
                dto.bodyTypeId(),
                dto.sedeId(),
                dto.optionalIds(),
                dto.imageIds(),
                dto.imageUrls()
        );
    }

    @PatchMapping("/{id}")
    public Vehicle patchVehicle(@PathVariable Long id, @RequestBody VehiclePatchDTO dto) {
        return vehicleService.patchVehicle(id, dto);
    }

    @GetMapping
    public List<Vehicle> getAll() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/filter")
    public List<Vehicle> filterVehicles(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long modelId,
            @RequestParam(required = false) Long bodyTypeId,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String fuelType,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) Integer maxKm
    ) {
        return vehicleService.filterVehicles(
                search,
                brandId,
                modelId,
                bodyTypeId,
                color,
                fuelType,
                minPrice,
                maxPrice,
                minYear,
                maxYear,
                maxKm
        );
    }

    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}