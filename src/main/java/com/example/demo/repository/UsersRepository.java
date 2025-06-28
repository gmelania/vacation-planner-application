/** Clasa pentru repository-ul entitatilor Users (utilizator)
 * @author Ghirda-Ratoi Melania-Maria
 * @version 12 Ianuarie 2024
 */

package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    // Finds user by email and password
    Users findByEmailAndPassword(String email, String password);

    // Finds all users with the same email.
    // This helps to verify if the email in the registration form is already used
    // generating an exception.
    List<Users> findByEmail(String email);
}
