package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Order;
import virgilistrate.CapstoneProject.entities.User;
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

    @PostMapping("/purchase")
    public Order createOnlineOrder(
            @RequestBody @Valid OrderDTO dto,
            @AuthenticationPrincipal User userLogged
    ) {
        return orderService.createOnlineOrder(dto.vehicleId(), userLogged);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}