package virgilistrate.CapstoneProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.CarImage;

import java.util.List;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
    List<CarImage> findByVehicleId(Long vehicleId);
}