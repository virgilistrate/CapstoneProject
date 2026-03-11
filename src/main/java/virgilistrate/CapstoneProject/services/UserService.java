package virgilistrate.CapstoneProject.services;

import org.springframework.stereotype.Service;
import virgilistrate.CapstoneProject.entities.*;
import virgilistrate.CapstoneProject.exceptions.NotFoundException;
import virgilistrate.CapstoneProject.repositories.*;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository

    ) {

        this.userRepository = userRepository;

    }

    // CREATE USER
    public User createUser(

            User user
    ) {


        return userRepository.save(user);
    }

    // GET ALL USERS

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET USER BY ID

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));


    }

    // GET USER BY EMAIL

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }


    // UPDATE USER --- DA FARE

    // DELETE USER

    public void deleteUser(Long id) {

        User user = getUserById(id);
        userRepository.delete(user);
    }


}
