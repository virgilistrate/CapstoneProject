package virgilistrate.CapstoneProject.controllers;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import virgilistrate.CapstoneProject.entities.Client;
import virgilistrate.CapstoneProject.payloads.ClientDTO;
import virgilistrate.CapstoneProject.services.ClientService;

import java.util.List;


@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService){

        this.clientService = clientService;
    }
    @PostMapping
    public  Client createClient(@RequestBody @Valid ClientDTO dto) {

        return clientService.createClient(dto.userId());

    }

    @GetMapping
    public List<Client> getAll(){

        return clientService.getAllClients();

    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id){
        return clientService.getClientById(id);

    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
         clientService.deleteClient(id);

    }

}
