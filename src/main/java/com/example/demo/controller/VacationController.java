/** Clasa pentru Controller Vacation
 * @author Ghirda-Ratoi Melania-Maria
 * @version 12 Ianuarie 2024
 */

package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.model.Vacation;
import com.example.demo.service.UserService;
import com.example.demo.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class VacationController {

    // Connection to both service layers: UserService, VacationService
    private final UserService usersService;
    private final VacationService vacationService;


    // Constructor
    @Autowired
    public VacationController(UserService usersService, VacationService vacationService) {
        this.usersService = usersService;
        this.vacationService = vacationService;
    }

    // Returns current user's vacations list after being authenticated successfully.
    @GetMapping("/vacations")
    public String getUserVacationsList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Users currentUser = usersService.getUserByEmail(email);

        model.addAttribute("userVacationLists", vacationService.getVacationsByUserId(currentUser.getId()));

        return "vacations";
    }

    @PostMapping("/vacations")
    public RedirectView addVacation(
            @RequestParam String destination,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(required = false) String notes,
            @RequestParam(required = false) boolean visited) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Users currentUser = usersService.getUserByEmail(email);

        Vacation newVacation = new Vacation();
        newVacation.setDestination(destination);
        newVacation.setStartDate(startDate);
        newVacation.setEndDate(endDate);
        newVacation.setNotes(notes);
        newVacation.setVisited(visited);
        newVacation.setUser(currentUser);

        vacationService.addVacation(newVacation);

        return new RedirectView("/vacations");
    }

    @PatchMapping("/vacations")
    public ResponseEntity<?> editVacation(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String notes,
            @RequestParam(required = false) boolean visited) {

        Optional<Vacation> vacationOpt = vacationService.getVacationById(id);

        if (vacationOpt.isPresent()) {
            Vacation vacation = vacationOpt.get();

            if (destination != null) {
                vacation.setDestination(destination);
            }
            if (startDate != null) {
                vacation.setStartDate(startDate);
            }
            if (endDate != null) {
                vacation.setEndDate(endDate);
            }
            if (notes != null) {
                vacation.setNotes(notes);
            }
            vacation.setVisited(visited);

            vacationService.updateVacation(id, vacation);

            return ResponseEntity.ok(vacation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacation not found");
        }
    }

    @GetMapping("/vacations/delete/{id}")
    public String deleteVacation(@PathVariable("id") Integer id) {
        vacationService.deleteVacationById(id);
        return "redirect:/vacations";
    }

    @GetMapping("/vacations/edit/{id}")
    @ResponseBody
    public Vacation getVacationById(@PathVariable("id") Integer id) {
        Optional<Vacation> vacation = vacationService.getVacationById(id);
        return vacation.orElse(null);
    }
}
