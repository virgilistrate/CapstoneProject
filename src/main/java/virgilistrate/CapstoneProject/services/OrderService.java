package virgilistrate.CapstoneProject.services;
import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.*;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

private final OrderRepository orderRepository;
private final ClientRepository clientRepository;
private final VehicleRepository vehicleRepository;
private final ConsulenteVenditaRepository consulenteVenditaRepository;

public OrderService(
        OrderRepository orderRepository,
        ClientRepository clientRepository,
        VehicleRepository vehicleRepository,
        ConsulenteVenditaRepository consulenteVenditaRepository

){

    this.orderRepository = orderRepository;
    this.clientRepository = clientRepository;
    this.vehicleRepository = vehicleRepository;
    this.consulenteVenditaRepository = consulenteVenditaRepository;

}

// CREATE ORDER --- SELLING CAR

    public  Order createOrder(Long clinetId, Long vehicleId, Long consulenteId) {

    Client client = clientRepository.findById(clinetId)
            .orElseThrow(() -> new NotFoundException("Client not found"));

    Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new NotFoundException("Vehicle not found"));

    ConsulenteVendita consulenteVendita = consulenteVenditaRepository.findById(consulenteId)
            .orElseThrow(() -> new NotFoundException("Consulente not found"));

    Order order = new Order();
    order.setDate(LocalDate.now());
    order.setClient(client);
    order.setVehicle(vehicle);
    order.setConsulente(consulenteVendita);

    return orderRepository.save(order);


    }

    // GET ALL ORDERS

    public List<Order> getAllOrders(){

    return orderRepository.findAll();
    }

    // GET ORDER BY ID

    public Order getOrderById(Long id){

    return orderRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Order not found"));

    }

    // GET ORDERS BY CLIENT ID

    public List<Order> getOrdersByClient(Long clientId) {
    Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new NotFoundException("Client not found"));

    return orderRepository.findByClient(client);


    }

    // GET ORDERS BY CONSULENTE ID

    public List<Order> getOrdersByConsulente(Long consulenteId){

        ConsulenteVendita consulente = consulenteVenditaRepository.findById(consulenteId)
                .orElseThrow(() -> new NotFoundException("Consulente not found"));

        return orderRepository.findByConsulente(consulente);
    }

    // DELETE ORDER

    public void deleteOrder(Long id){

    Order order = getOrderById(id);
    orderRepository.delete(order);
    }

}
