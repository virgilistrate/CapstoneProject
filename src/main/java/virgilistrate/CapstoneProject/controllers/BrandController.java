package virgilistrate.CapstoneProject.controllers;

import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Brand;
import virgilistrate.CapstoneProject.services.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brands")
@CrossOrigin(origins = "http://localhost:5173")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<Brand> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public Brand getById(@PathVariable Long id) {
        return brandService.getById(id);
    }
}