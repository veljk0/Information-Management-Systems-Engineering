package com.airline.backend.entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Passenger {
    @Id
    @Column(name = "ticket_id")
    private int id;
    private String firstName;
    private String secondName;
    private Date birthDate;
    private String passNr;
    @OneToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticketId")
    private Ticket ticket;


    public Passenger() {}

    public Passenger(String firstName, String secondName, Date birthDate, String passNr, Ticket ticket) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.passNr = passNr;
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassNr() {
        return passNr;
    }

    public void setPassNr(String passNr) {
        this.passNr = passNr;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Passenger [birthDate=" + birthDate + ", firstName=" + firstName + ", id=" + id + ", passNr=" + passNr
                + ", secondName=" + secondName + ", ticket=" + ticket + "]";
    }

    

    
    

}
