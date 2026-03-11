package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.Client;
import virgilistrate.CapstoneProject.entities.User;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.ClientRepository;
import virgilistrate.CapstoneProject.repositories.UserRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientService(
            ClientRepository clientRepository,
            UserRepository userRepository
    ) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    // CREATE CLIENT
    public Client createClient(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Client client = new Client();
        client.setUser(user);

        return clientRepository.save(client);
    }

    // GET ALL
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    // GET BY ID
    public Client getClientById(Long id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found"));
    }

    // DELETE
    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }
}