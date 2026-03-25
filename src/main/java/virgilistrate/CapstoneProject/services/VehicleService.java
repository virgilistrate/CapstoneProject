package virgilistrate.CapstoneProject.services;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.*;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.payloads.VehiclePatchDTO;
import virgilistrate.CapstoneProject.repositories.*;
import virgilistrate.CapstoneProject.specifications.VehicleSpecification;

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
    private final CarImageRepository carImageRepository;

    public VehicleService(
            VehicleRepository vehicleRepository,
            BrandRepository brandRepository,
            ModelRepository modelRepository,
            BodyTypeRepository bodyTypeRepository,
            OptionalRepository optionalRepository,
            SedeRepository sedeRepository,
            CarImageRepository carImageRepository
    ) {
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.bodyTypeRepository = bodyTypeRepository;
        this.optionalRepository = optionalRepository;
        this.sedeRepository = sedeRepository;
        this.carImageRepository = carImageRepository;
    }

    public Vehicle createVehicle(
            Vehicle vehicle,
            Long brandId,
            Long modelId,
            Long bodyTypeId,
            Long sedeId,
            Set<Long> optionalIds,
            Set<Long> imageIds,
            Set<String> imageUrls
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

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        if (imageIds != null && !imageIds.isEmpty()) {
            List<CarImage> existingImages = carImageRepository.findAllById(imageIds);
            for (CarImage image : existingImages) {
                image.setVehicle(savedVehicle);
            }
            carImageRepository.saveAll(existingImages);
            savedVehicle.setImages(existingImages);
        }

        if (imageUrls != null && !imageUrls.isEmpty()) {
            List<CarImage> newImages = imageUrls.stream()
                    .filter(url -> url != null && !url.isBlank())
                    .map(url -> {
                        CarImage image = new CarImage();
                        image.setImageUrl(url.trim());
                        image.setVehicle(savedVehicle);
                        return image;
                    })
                    .toList();

            if (!newImages.isEmpty()) {
                List<CarImage> savedImages = carImageRepository.saveAll(newImages);
                savedVehicle.setImages(savedImages);
            }
        }

        return vehicleRepository.save(savedVehicle);
    }

    public Vehicle patchVehicle(Long id, VehiclePatchDTO dto) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        if (dto.plateNumber() != null) vehicle.setPlateNumber(dto.plateNumber());
        if (dto.sold() != null) {
            vehicle.setSold(dto.sold());
        }
        if (dto.price() != null) vehicle.setPrice(dto.price());
        if (dto.yearOfConstruction() != null) vehicle.setYearOfConstruction(dto.yearOfConstruction());
        if (dto.kilometers() != null) vehicle.setKilometers(dto.kilometers());
        if (dto.color() != null) vehicle.setColor(dto.color());
        if (dto.fuelType() != null) vehicle.setFuelType(dto.fuelType());
        if (dto.seats() != null) vehicle.setSeats(dto.seats());
        if (dto.doorsNumber() != null) vehicle.setDoorsNumber(dto.doorsNumber());
        if (dto.engineCapacity() != null) vehicle.setEngineCapacity(dto.engineCapacity());
        if (dto.enginePower() != null) vehicle.setEnginePower(dto.enginePower());
        if (dto.engineConsumption() != null) vehicle.setEngineConsumption(dto.engineConsumption());
        if (dto.tractiontype() != null) vehicle.setTractiontype(dto.tractiontype());
        if (dto.vehicleLength() != null) vehicle.setVehicleLength(dto.vehicleLength());
        if (dto.vehicleWidth() != null) vehicle.setVehicleWidth(dto.vehicleWidth());
        if (dto.vehicleHeight() != null) vehicle.setVehicleHeight(dto.vehicleHeight());
        if (dto.trunkSize() != null) vehicle.setTrunkSize(dto.trunkSize());
        if (dto.emissionsClass() != null) vehicle.setEmissionsClass(dto.emissionsClass());
        if (dto.co2Emissions() != null) vehicle.setCo2Emissions(dto.co2Emissions());

        if (dto.brandId() != null) {
            Brand brand = brandRepository.findById(dto.brandId())
                    .orElseThrow(() -> new NotFoundException("Brand not found"));
            vehicle.setBrand(brand);
        }

        if (dto.modelId() != null) {
            Model model = modelRepository.findById(dto.modelId())
                    .orElseThrow(() -> new NotFoundException("Model not found"));
            vehicle.setModel(model);
        }

        if (dto.bodyTypeId() != null) {
            BodyType bodyType = bodyTypeRepository.findById(dto.bodyTypeId())
                    .orElseThrow(() -> new NotFoundException("Body Type not found"));
            vehicle.setBodyType(bodyType);
        }

        if (dto.sedeId() != null) {
            Sede sede = sedeRepository.findById(dto.sedeId())
                    .orElseThrow(() -> new NotFoundException("Sede non trovata"));
            vehicle.setSede(sede);
        }

        if (dto.optionalIds() != null) {
            Set<Optional> optionals = new HashSet<>(optionalRepository.findAllById(dto.optionalIds()));
            vehicle.setOptionals(optionals);
        }

        if (dto.imageIds() != null) {
            List<CarImage> currentImages = carImageRepository.findByVehicleId(vehicle.getId());

            List<CarImage> imagesToDelete = currentImages.stream()
                    .filter(img -> !dto.imageIds().contains(img.getId()))
                    .toList();

            if (!imagesToDelete.isEmpty()) {
                carImageRepository.deleteAll(imagesToDelete);
            }

            List<CarImage> selectedImages = carImageRepository.findAllById(dto.imageIds());
            for (CarImage image : selectedImages) {
                image.setVehicle(vehicle);
            }
            carImageRepository.saveAll(selectedImages);
            vehicle.setImages(selectedImages);
        }

        if (dto.imageUrls() != null) {
            List<CarImage> currentImages = carImageRepository.findByVehicleId(vehicle.getId());
            if (!currentImages.isEmpty()) {
                carImageRepository.deleteAll(currentImages);
            }

            List<CarImage> newImages = dto.imageUrls().stream()
                    .filter(url -> url != null && !url.isBlank())
                    .map(url -> {
                        CarImage image = new CarImage();
                        image.setImageUrl(url.trim());
                        image.setVehicle(vehicle);
                        return image;
                    })
                    .toList();

            List<CarImage> savedImages = carImageRepository.saveAll(newImages);
            vehicle.setImages(savedImages);
        }

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> filterVehicles(
            String search,
            Long brandId,
            Long modelId,
            Long bodyTypeId,
            String color,
            String fuelType,
            Double minPrice,
            Double maxPrice,
            Integer minYear,
            Integer maxYear,
            Integer maxKm
    ) {
        Specification<Vehicle> spec = Specification
                .where(VehicleSpecification.isNotSold())
                .and(VehicleSpecification.matchesSearch(search))
                .and(VehicleSpecification.hasBrandId(brandId))
                .and(VehicleSpecification.hasModelId(modelId))
                .and(VehicleSpecification.hasBodyTypeId(bodyTypeId))
                .and(VehicleSpecification.hasColor(color))
                .and(VehicleSpecification.hasFuelType(fuelType))
                .and(VehicleSpecification.priceGreaterThanOrEqual(minPrice))
                .and(VehicleSpecification.priceLessThanOrEqual(maxPrice))
                .and(VehicleSpecification.yearGreaterThanOrEqual(minYear))
                .and(VehicleSpecification.yearLessThanOrEqual(maxYear))
                .and(VehicleSpecification.kilometersLessThanOrEqual(maxKm));

        return vehicleRepository.findAll(spec);
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