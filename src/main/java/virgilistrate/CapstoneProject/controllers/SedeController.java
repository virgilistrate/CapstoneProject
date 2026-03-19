package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Sede;
import virgilistrate.CapstoneProject.payloads.SedeDTO;
import virgilistrate.CapstoneProject.services.SedeService;

import java.util.List;

@RestController
@RequestMapping("/sedi")
@CrossOrigin(origins = "http://localhost:5173")
public class SedeController {

    private final SedeService sedeService;

    public SedeController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @PostMapping
    public Sede createSede(@RequestBody @Valid SedeDTO dto) {
        Sede sede = new Sede();
        sede.setName(dto.name());
        sede.setCity(dto.city());
        sede.setAdress(dto.adress());
        sede.setPostalCode(dto.postalCode());
        sede.setPhone(dto.phone());
        sede.setEmail(dto.email());

        return sedeService.createSede(sede);
    }

    @GetMapping
    public List<Sede> getAll() {
        return sedeService.getAllSedi();
    }

    @GetMapping("/{id}")
    public Sede getById(@PathVariable Long id) {
        return sedeService.getSedeById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sedeService.deleteSede(id);
    }
}