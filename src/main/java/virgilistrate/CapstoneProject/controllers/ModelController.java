package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Model;
import virgilistrate.CapstoneProject.payloads.ModelDTO;
import virgilistrate.CapstoneProject.services.ModelService;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public Model createModel(@RequestBody @Valid ModelDTO dto) {
        return modelService.createModel(
                dto.brandId(),
                dto.name()
        );
    }

    @GetMapping
    public List<Model> getAll() {
        return modelService.getAll();
    }

    @GetMapping("/{id}")
    public Model getById(@PathVariable Long id) {
        return modelService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        modelService.delete(id);
    }
}