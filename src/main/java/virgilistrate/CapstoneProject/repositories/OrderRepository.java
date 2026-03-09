package virgilistrate.CapstoneProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.CapstoneProject.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}