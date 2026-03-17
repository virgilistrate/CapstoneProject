package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.*;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final BodyTypeRepository bodyTypeRepository;
    private final OptionalRepository optionalRepository;
    private final SedeRepository sedeRepository;

    public VehicleService(
            VehicleRepository vehicleRepository,
            BrandRepository brandRepository,
            ModelRepository modelRepository,
            BodyTypeRepository bodyTypeRepository,
            OptionalRepository optionalRepository,
            SedeRepository sedeRepository
    ) {
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.bodyTypeRepository = bodyTypeRepository;
        this.optionalRepository = optionalRepository;
        this.sedeRepository = sedeRepository;
    }

    public Vehicle createVehicle(
            Vehicle vehicle,
            Long brandId,
            Long modelId,
            Long bodyTypeId,
            Long sedeId,
            Set<Long> optionalIds
    ) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("Brand not found"));

        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new NotFoundException("Model not found"));

        BodyType bodyType = bodyTypeRepository.findById(bodyTypeId)
                .orElseThrow(() -> new NotFoundException("Body Type not found"));

        Sede sede = sedeRepository.findById(sedeId)
                .orElseThrow(() -> new NotFoundException("Sede non trovata"));

        Set<Optional> optionals = new HashSet<>();
        if (optionalIds != null && !optionalIds.isEmpty()) {
            optionals = new HashSet<>(optionalRepository.findAllById(optionalIds));
        }

        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setBodyType(bodyType);
        vehicle.setSede(sede);
        vehicle.setOptionals(optionals);

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }
}