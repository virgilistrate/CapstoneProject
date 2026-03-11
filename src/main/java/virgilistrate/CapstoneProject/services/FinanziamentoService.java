package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.Finanziamento;
import virgilistrate.CapstoneProject.entities.Order;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.FinanziamentoRepository;
import virgilistrate.CapstoneProject.repositories.OrderRepository;

import java.util.List;

@Service
public class FinanziamentoService {

    private final FinanziamentoRepository finanziamentoRepository;
    private final OrderRepository orderRepository;

    public FinanziamentoService(
            FinanziamentoRepository finanziamentoRepository,
            OrderRepository orderRepository
    ) {
        this.finanziamentoRepository = finanziamentoRepository;
        this.orderRepository = orderRepository;
    }

    // CREATE
    public Finanziamento createFinanziamento(Long orderId, Double amount){

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        Finanziamento f = new Finanziamento();
        f.setOrder(order);
        f.setAmount(amount);

        return finanziamentoRepository.save(f);
    }

    // GET ALL
    public List<Finanziamento> getAll(){
        return finanziamentoRepository.findAll();
    }

    // GET BY ID
    public Finanziamento getById(Long id){
        return finanziamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Finanziamento not found"));
    }

    // DELETE
    public void delete(Long id){
        finanziamentoRepository.deleteById(id);
    }
}