package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import virgilistrate.CapstoneProject.entities.Client;
import virgilistrate.CapstoneProject.entities.Order;
import virgilistrate.CapstoneProject.entities.User;
import virgilistrate.CapstoneProject.entities.Vehicle;
import virgilistrate.CapstoneProject.enums.Role;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.exceptions.UnauthorizedException;
import virgilistrate.CapstoneProject.repositories.ClientRepository;
import virgilistrate.CapstoneProject.repositories.OrderRepository;
import virgilistrate.CapstoneProject.repositories.VehicleRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;

    public OrderService(
            OrderRepository orderRepository,
            ClientRepository clientRepository,
            VehicleRepository vehicleRepository
    ) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public Order createOnlineOrder(Long vehicleId, User userLogged) {
        if (userLogged == null) {
            throw new UnauthorizedException("Utente non autenticato");
        }

        if (userLogged.getRole() != Role.CLIENT) {
            throw new UnauthorizedException("Solo i clienti possono acquistare online");
        }

        Client client = clientRepository.findByUserId(userLogged.getId())
                .orElseThrow(() -> new NotFoundException("Client non trovato per questo utente"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        if (Boolean.TRUE.equals(vehicle.getSold())) {
            throw new IllegalStateException("Questo veicolo è già stato venduto");
        }

        if (orderRepository.existsByVehicleId(vehicleId)) {
            throw new IllegalStateException("Esiste già un ordine per questo veicolo");
        }

        vehicle.setSold(true);
        vehicleRepository.save(vehicle);

        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setClient(client);
        order.setVehicle(vehicle);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }

    public List<Order> getOrdersByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        return orderRepository.findByClient(client);
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
}