/** Clasa pentru Controller Homepage
 * @author Ghirda-Ratoi Melania-Maria
 * @version 12 Ianuarie 2024
 */

package com.example.demo.controller;

import com.example.demo.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Redirect homepage
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    // Get homepage
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
