package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.Sede;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.SedeRepository;

import java.util.List;

@Service
public class SedeService {

    private final SedeRepository sedeRepository;

    public SedeService(
            SedeRepository sedeRepository
    ) {
        this.sedeRepository = sedeRepository;
    }

    // CREATE
    public Sede createSede(Sede sede){
        return sedeRepository.save(sede);
    }

    // GET ALL
    public List<Sede> getAllSedi(){
        return sedeRepository.findAll();
    }

    // GET BY ID
    public Sede getSedeById(Long id){
        return sedeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sede not found"));
    }

    // DELETE
    public void deleteSede(Long id){
        sedeRepository.deleteById(id);
    }
}