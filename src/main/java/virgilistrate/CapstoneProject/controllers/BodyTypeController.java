package virgilistrate.CapstoneProject.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.BodyType;
import virgilistrate.CapstoneProject.payloads.BodyTypeDTO;
import virgilistrate.CapstoneProject.services.BodyTypeService;

import java.util.List;

@RestController
@RequestMapping("/body-types")
@CrossOrigin(origins = "http://localhost:5173")
public class BodyTypeController {

    private final BodyTypeService bodyTypeService;

    public BodyTypeController(BodyTypeService bodyTypeService) {
        this.bodyTypeService = bodyTypeService;
    }

    @PostMapping
    public BodyType createBodyType(@RequestBody @Valid BodyTypeDTO dto) {
        BodyType bodyType = new BodyType();
        bodyType.setName(dto.name());

        return bodyTypeService.createBodyType(bodyType);
    }

    @GetMapping
    public List<BodyType> getAll() {
        return bodyTypeService.getAllBodyTypes();
    }

    @GetMapping("/{id}")
    public BodyType getById(@PathVariable Long id) {
        return bodyTypeService.getBodyTypeById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bodyTypeService.deleteBodyType(id);
    }
}