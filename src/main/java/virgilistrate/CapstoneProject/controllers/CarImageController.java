package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.CarImage;
import virgilistrate.CapstoneProject.payloads.CarImageDTO;
import virgilistrate.CapstoneProject.services.CarImageService;

import java.util.List;

@RestController
@RequestMapping("/car-images")
@CrossOrigin(origins = "http://localhost:5173")
public class CarImageController {

    private final CarImageService carImageService;

    public CarImageController(CarImageService carImageService) {
        this.carImageService = carImageService;
    }

    @PostMapping
    public CarImage createImage(@RequestBody @Valid CarImageDTO dto) {
        return carImageService.createImage(dto.imageUrl(), dto.vehicleId());
    }

    @GetMapping
    public List<CarImage> getAllImages() {
        return carImageService.getAllImages();
    }

    @GetMapping("/{id}")
    public CarImage getImageById(@PathVariable Long id) {
        return carImageService.getImageById(id);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<CarImage> getImagesByVehicleId(@PathVariable Long vehicleId) {
        return carImageService.getImagesByVehicleId(vehicleId);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        carImageService.deleteImage(id);
    }
}