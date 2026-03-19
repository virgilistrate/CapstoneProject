package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Optional;
import virgilistrate.CapstoneProject.payloads.OptionalDTO;
import virgilistrate.CapstoneProject.services.OptionalService;

import java.util.List;

@RestController
@RequestMapping("/optionals")
@CrossOrigin(origins = "http://localhost:5173")
public class OptionalController {

    private final OptionalService optionalService;

    public OptionalController(OptionalService optionalService) {
        this.optionalService = optionalService;
    }

    @PostMapping
    public Optional createOptional(@RequestBody @Valid OptionalDTO dto) {
        Optional optional = new Optional();
        optional.setName(dto.name());

        return optionalService.createOptional(optional);
    }

    @GetMapping
    public List<Optional> getAll() {
        return optionalService.getAllOptionals();
    }

    @GetMapping("/{id}")
    public Optional getById(@PathVariable Long id) {
        return optionalService.getOptionalById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        optionalService.deleteOptional(id);
    }
}