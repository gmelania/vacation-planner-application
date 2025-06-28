/** Clasa pentru conexiunea cu repository-ul dedicat Users si clasa pentru spring-security 'SecurityConfig'
 * @author Ghirda-Ratoi Melania-Maria
 * @version 12 Ianuarie 2024
 */

package com.example.demo.service;

import com.example.demo.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Connection to the Repository Layer.
    private final UsersRepository usersRepository;

    // Constructor
    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Returns the email and password if the user is found in the database.
    // If user not found, throws exception.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var users = usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        var user = users.get(0);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
