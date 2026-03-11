package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.Brand;
import virgilistrate.CapstoneProject.entities.Model;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.BrandRepository;
import virgilistrate.CapstoneProject.repositories.ModelRepository;

import java.util.List;

@Service
public class ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public ModelService(
            ModelRepository modelRepository,
            BrandRepository brandRepository
    ) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    public Model createModel(Long brandId, String name){

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("Brand not found"));

        Model m = new Model();
        m.setName(name);
        m.setBrand(brand);

        return modelRepository.save(m);
    }

    public List<Model> getAll(){
        return modelRepository.findAll();
    }

    public Model getById(Long id){
        return modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model not found"));
    }

    public void delete(Long id){
        modelRepository.deleteById(id);
    }
}