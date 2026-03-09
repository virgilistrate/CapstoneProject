package virgilistrate.CapstoneProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.CarImage;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
}