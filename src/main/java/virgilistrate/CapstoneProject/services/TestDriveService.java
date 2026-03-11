package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.Client;
import virgilistrate.CapstoneProject.entities.TestDrive;
import virgilistrate.CapstoneProject.entities.Vehicle;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.ClientRepository;
import virgilistrate.CapstoneProject.repositories.TestDriveRepository;
import virgilistrate.CapstoneProject.repositories.VehicleRepository;

import java.util.List;

@Service
public class TestDriveService {

    private final TestDriveRepository testDriveRepository;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;

    public TestDriveService(
            TestDriveRepository testDriveRepository,
            ClientRepository clientRepository,
            VehicleRepository vehicleRepository
    ) {
        this.testDriveRepository = testDriveRepository;
        this.clientRepository = clientRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // CREATE
    public TestDrive createTestDrive(Long clientId, Long vehicleId){

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        TestDrive td = new TestDrive();
        td.setClient(client);
        td.setVehicle(vehicle);

        return testDriveRepository.save(td);
    }

    // GET ALL
    public List<TestDrive> getAll(){
        return testDriveRepository.findAll();
    }

    // GET BY ID
    public TestDrive getById(Long id){
        return testDriveRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TestDrive not found"));
    }

    // DELETE
    public void delete(Long id){
        testDriveRepository.deleteById(id);
    }
}