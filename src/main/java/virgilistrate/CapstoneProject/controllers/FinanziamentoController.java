package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Finanziamento;
import virgilistrate.CapstoneProject.payloads.FinanziamentoDTO;
import virgilistrate.CapstoneProject.services.FinanziamentoService;

import java.util.List;

@RestController
@RequestMapping("/finanziamenti")
public class FinanziamentoController {

    private final FinanziamentoService finanziamentoService;

    public FinanziamentoController(FinanziamentoService finanziamentoService){

        this.finanziamentoService = finanziamentoService;

    }
    @PostMapping
    public Finanziamento createFinanziamento(@RequestBody @Valid FinanziamentoDTO dto) {

        return finanziamentoService.createFinanziamento(
                dto.orderId(), dto.amount()
        );

    }

    @GetMapping
    public List<Finanziamento> getAll(){
        return finanziamentoService.getAll();

    }

    @GetMapping("/{id}")
    public Finanziamento getById(@PathVariable Long id){

        return finanziamentoService.getById(id);

    }
    @DeleteMapping("/{id}")

    public void delete(@PathVariable Long id){

        finanziamentoService.delete(id);
    }
}
