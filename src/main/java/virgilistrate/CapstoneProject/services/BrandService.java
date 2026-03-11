package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.Brand;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.BrandRepository;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(
            BrandRepository brandRepository
    ) {
        this.brandRepository = brandRepository;
    }

    public Brand createBrand(Brand brand){
        return brandRepository.save(brand);
    }

    public List<Brand> getAll(){
        return brandRepository.findAll();
    }

    public Brand getById(Long id){
        return brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found"));
    }

    public void delete(Long id){
        brandRepository.deleteById(id);
    }
}