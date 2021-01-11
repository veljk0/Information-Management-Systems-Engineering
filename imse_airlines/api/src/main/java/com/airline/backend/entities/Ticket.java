package com.airline.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int ticketID;
    private int price;
    private Boolean sold = false;
    
    @ManyToOne
	@JoinColumn(name ="flightID", referencedColumnName = "flightID")
    private Flight flight;
   
    @ManyToOne
	@JoinColumn(name ="userID", referencedColumnName = "id")
    private User user;
    
    public Ticket() {}

    public Ticket(int ticketID, int price, Boolean sold, Flight flight, User user) {
        this.ticketID = ticketID;
        this.price = price;
        this.sold = sold;
        this.flight = flight;
        this.user = user;
    }

    public Ticket(int i, Flight flight1) {
        this.price = i;
        this.flight = flight1;
	}

	public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
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

    @JsonBackReference
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    @JsonBackReference(value="user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
