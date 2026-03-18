package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.CarImage;
import virgilistrate.CapstoneProject.entities.Vehicle;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.CarImageRepository;
import virgilistrate.CapstoneProject.repositories.VehicleRepository;

import java.util.List;

@Service
public class CarImageService {

    private final CarImageRepository carImageRepository;
    private final VehicleRepository vehicleRepository;

    public CarImageService(CarImageRepository carImageRepository, VehicleRepository vehicleRepository) {
        this.carImageRepository = carImageRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public CarImage createImage(String imageUrl, Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        CarImage carImage = new CarImage();
        carImage.setImageUrl(imageUrl);
        carImage.setVehicle(vehicle);

        return carImageRepository.save(carImage);
    }

    public List<CarImage> getAllImages() {
        return carImageRepository.findAll();
    }

    public CarImage getImageById(Long id) {
        return carImageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image not found"));
    }

    public List<CarImage> getImagesByVehicleId(Long vehicleId) {
        return carImageRepository.findByVehicleId(vehicleId);
    }

    public void deleteImage(Long id) {
        CarImage carImage = getImageById(id);
        carImageRepository.delete(carImage);
    }
}