package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.*;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.*;

import java.util.List;
import java.util.Set;


@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final BodyTypeRepository bodyTypeRepository;
    private final OptionalRepository optionalRepository;

    public VehicleService (

            VehicleRepository vehicleRepository,
            BrandRepository brandRepository,
            ModelRepository modelRepository,
            BodyTypeRepository bodyTypeRepository,
            OptionalRepository optionalRepository

    ){

        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.bodyTypeRepository = bodyTypeRepository;
        this.optionalRepository = optionalRepository;

    }

    // CREATE VEHICLE

    public  Vehicle createVehicle(
            Vehicle vehicle,
            Long brandId,
            Long modelId,
            Long bodyTypeId,
            Set<Long> optionalIds


    ){
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("Brand not found"));

        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new NotFoundException("Model not found"));

        BodyType bodyType = bodyTypeRepository.findById(bodyTypeId)
                .orElseThrow(() -> new NotFoundException("Body Type not found"));
        Set<Optional> optionals = Set.copyOf(optionalRepository.findAllById(optionalIds));

        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setBodyType(bodyType);
        vehicle.setOptionals(optionals);

return vehicleRepository.save(vehicle);



    }
// GET ALL VEHICLES

  public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();


  }

// GET VEHICLE BY ID

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

    }

    // UPDATE VEHICLE  --- DA FARE


    // DELETE VEHICLE

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);

    }

    // FILTER BY BRAND
    public List<Vehicle> getVehiclesByBrand(Long brandId) {
        return vehicleRepository.findByBrandId(brandId);
    }
    // FILTER BY MODEL
    public List<Vehicle> getVehiclesByModel(Long modelId) {
        return vehicleRepository.findByModelId(modelId);
    }
    // FILTER BY PRICE RANGE
    public List<Vehicle> getVehiclesByPriceRange(Double min, Double max) {
        return vehicleRepository.findByPriceBetween(min, max);
    }

}
