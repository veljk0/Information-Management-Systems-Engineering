package com.airline.backend.migration.collections;

import com.airline.backend.entities.Flight;
import com.airline.backend.entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document(collection = "tickets")
public class MongoTicket {

    @Id
    private String id;
    private int price;
    private Boolean sold = false;


    private MongoFlight flight;

    private MongoUser mongoUser;

    public MongoTicket() {}


    public MongoTicket(String ticketID, int price, Boolean sold, MongoFlight flight) {
        this.id = ticketID;
        this.price = price;
        this.sold = sold;
        this.flight = flight;
    }

    public MongoTicket(int i, MongoFlight flight1) {
        this.price = i;
        this.flight = flight1;
        this.sold = false;
    }

    public MongoTicket(String toString, int price, Boolean sold) {
        this.id = toString;
        this.price = price;
        this.sold = sold;
    }

    public String getTicketID() {
        return id;
    }

    public void setTicketID(String ticketID) {
        this.id = ticketID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }


    public MongoFlight getFlight() {
        return flight;
    }

    public void setFlight(MongoFlight flight) {
        this.flight = flight;
    }

    public MongoUser getUser() {
        return mongoUser;
    }

    public void setUser(MongoUser mongoUser) {
        this.mongoUser = mongoUser;
    }
}
