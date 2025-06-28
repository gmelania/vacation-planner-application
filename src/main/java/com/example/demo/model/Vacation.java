/** Clasa pentru entitatea Vacation
 * @author Ghirda-Ratoi Melania-Maria
 * @version 12 Ianuarie 2024
 */

package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Vacation")
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "start_Date")
    private String startDate;

    @Column(name = "end_Date")
    private String endDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "visited")
    private boolean visited;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    // Constructor
    public Vacation() {
    }

    public Vacation(String destination, String startDate, String endDate, String notes, boolean visited, Users user) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.visited = visited;
        this.user = user;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
