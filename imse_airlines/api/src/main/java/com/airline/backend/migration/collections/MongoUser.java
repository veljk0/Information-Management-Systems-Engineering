package com.airline.backend.migration.collections;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class MongoUser {

    @Id
    private String id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;

    private List<MongoTicket> tickets;


    public MongoUser() {}

    public MongoUser(String firstName, String secondName, String email, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.tickets = new ArrayList<>();
    }

    public MongoUser(String id, String firstName, String secondName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.tickets = new ArrayList<>();
    }

    public String getId() {
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

    public void setId(String id) {
        this.id = id;
    }

    public List<MongoTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<MongoTicket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "|ID: "+ id + "|" + firstName + "|" + this.secondName + "|" + this.email + "|" + "\n";
    }
}
