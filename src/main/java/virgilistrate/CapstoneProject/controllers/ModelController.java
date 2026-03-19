package virgilistrate.CapstoneProject.controllers;

import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Model;
import virgilistrate.CapstoneProject.services.ModelService;

import java.util.List;

@RestController
@RequestMapping("/models")
@CrossOrigin(origins = "http://localhost:5173")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public List<Model> getAll(@RequestParam(required = false) Long brandId) {
        if (brandId != null) {
            return modelService.getByBrandId(brandId);
        }
        return modelService.getAll();
    }

    @GetMapping("/{id}")
    public Model getById(@PathVariable Long id) {
        return modelService.getById(id);
    }
}