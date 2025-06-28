/** Clasa pentru repository-ul entitatii Vacation (vacante)
 * @author Ghirda-Ratoi Melania-Maria
 * @version 12 Ianuarie 2024
 */

package com.example.demo.repository;

import com.example.demo.model.Users;
import com.example.demo.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer> {

    // Finds vacations if a user by his ID
    List<Vacation> findByUserId(int userId);

    // Returns all vacations of all users that have been completed (visited)
    List<Vacation> findByVisited(boolean visited);

    // Returns the vacations after one user's ID that have been completed (visited)
    List<Vacation> findByUserIdAndVisited(int userId, boolean visited);
}