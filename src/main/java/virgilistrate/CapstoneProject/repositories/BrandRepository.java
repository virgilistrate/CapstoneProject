package virgilistrate.CapstoneProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}