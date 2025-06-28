/** Clasa pentru serviciul dedicat Users; rezolva conexiunea cu repository-ul Users
 * @author Ghirda-Ratoi Melania-Maria
 * @version 12 Ianuarie 2024
 */

package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    // Connection to the Repository Layer.
    private final UsersRepository usersRepository;

    // Constructor
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Calls the repository to pass the user to the database for saving.
    public void addUser(Users user) {
        usersRepository.save(user);
    }

    // Calls the repository to find the user by email and password.
    public Users findByEmailAndPassword(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password); // chemam repo sa caute userul in tabel
    }

    // Calls the repository to find the user by email.
    public boolean findByEmail(String email) {
        List<Users> users = usersRepository.findByEmail(email);
        return !users.isEmpty(); // returns 1 if the user list with the same e-mail is not empty
    }

    // Calls the repository to get the user from the database, by email
    public Users getUserByEmail(String email) {
        List<Users> users = usersRepository.findByEmail(email);
        return users.isEmpty() ? null : users.get(0);
    }

    // Calls repository to retrieve all users from the database
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }


}
