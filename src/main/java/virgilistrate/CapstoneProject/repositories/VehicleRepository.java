package virgilistrate.CapstoneProject.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.Vehicle;

import java.util.List;
import java.util.Optional;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByBrand(String brand);
    Optional<Vehicle> findByModel(String model);


    List<Vehicle> findByBrandId(Long brandId);

    List<Vehicle> findByModelId(Long modelId);

    List<Vehicle> findByPriceBetween(Double min, Double max);
}
