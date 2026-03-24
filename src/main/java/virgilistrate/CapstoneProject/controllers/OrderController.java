package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Order;
import virgilistrate.CapstoneProject.payloads.OrderDTO;
import virgilistrate.CapstoneProject.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody @Valid OrderDTO dto){
        return orderService.createOrder(
                dto.clientId(),
                dto.vehicleId(),
                dto.consulenteId()
        );
    }

    @GetMapping
    public List<Order> getAll(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
}