package virgilistrate.CapstoneProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
}