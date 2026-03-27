package virgilistrate.CapstoneProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUserId(Long userId);
}