package com.airline.backend.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class User {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    @OneToMany(targetEntity = Ticket.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "id")
    private List<Ticket> tickets;


    public User() {}

    public User(String firstName, String secondName, String email, String password) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
    
   
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "|ID: "+ id + "|" + firstName + "|" + this.secondName + "|" + this.email + "|" + "\n";
    }

}

    


