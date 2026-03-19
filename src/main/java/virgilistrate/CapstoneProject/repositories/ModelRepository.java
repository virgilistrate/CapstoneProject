package virgilistrate.CapstoneProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.Model;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByBrandId(Long brandId);
}