package virgilistrate.CapstoneProject.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.Vehicle;

import java.util.Optional;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByBrand(String brand);
    Optional<Vehicle> findByModel(String model);


}
