/** Clasa pentru Controller User
 * @author Ghirda-Ratoi Melania-Maria
 * @version 12 Ianuarie 2024
 */

package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class UserController {

    // Connection to the UserService service layer
    private final UserService userService;

    // Constructor
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Returns all users
    @GetMapping("users/all")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }
}
