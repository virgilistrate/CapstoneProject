package virgilistrate.CapstoneProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.Client;
import virgilistrate.CapstoneProject.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClient(Client client);
    boolean existsByVehicleId(Long vehicleId);
}