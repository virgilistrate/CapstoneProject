package virgilistrate.CapstoneProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}