package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.BodyType;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.BodyTypeRepository;

import java.util.List;

@Service
public class BodyTypeService {

    private final BodyTypeRepository bodyTypeRepository;

    public BodyTypeService(
            BodyTypeRepository bodyTypeRepository
    ) {
        this.bodyTypeRepository = bodyTypeRepository;
    }

    // CREATE
    public BodyType createBodyType(BodyType bodyType){
        return bodyTypeRepository.save(bodyType);
    }

    // GET ALL
    public List<BodyType> getAllBodyTypes(){
        return bodyTypeRepository.findAll();
    }

    // GET BY ID
    public BodyType getBodyTypeById(Long id){
        return bodyTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BodyType not found"));
    }

    // DELETE
    public void deleteBodyType(Long id){
        bodyTypeRepository.deleteById(id);
    }
}