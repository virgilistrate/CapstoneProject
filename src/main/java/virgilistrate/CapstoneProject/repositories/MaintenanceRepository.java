package virgilistrate.CapstoneProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}