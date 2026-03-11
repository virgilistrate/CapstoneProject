package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.Optional;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.OptionalRepository;

import java.util.List;

@Service
public class OptionalService {

    private final OptionalRepository optionalRepository;

    public OptionalService(
            OptionalRepository optionalRepository
    ) {
        this.optionalRepository = optionalRepository;
    }

    // CREATE
    public Optional createOptional(Optional optional){
        return optionalRepository.save(optional);
    }

    // GET ALL
    public List<Optional> getAllOptionals(){
        return optionalRepository.findAll();
    }

    // GET BY ID
    public Optional getOptionalById(Long id){
        return optionalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Optional not found"));
    }

    // DELETE
    public void deleteOptional(Long id){
        optionalRepository.deleteById(id);
    }
}