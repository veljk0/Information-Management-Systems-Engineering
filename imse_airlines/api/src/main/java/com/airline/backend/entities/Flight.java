package com.airline.backend.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int flightID;
    private String departureCity;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date departureDate;
    private String arrivalCity;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date arrivalDate;
    private int placeLeft = 10;
    @ManyToOne
	@JoinColumn(name ="companyid_fk", referencedColumnName = "companyId")
	private Company company;
    
    @OneToMany(targetEntity = Ticket.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "flightID", referencedColumnName = "flightID")
    private List<Ticket> tickets;

    public Flight() {}
    
    public Flight(String departureCity, Date departureDate, String arrivalCity, Date arrivalDate, Company company) {
    	this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
        this.company = company;
    
    }

    public Flight(int flightID, String departureCity, Date departureDate, String arrivalCity, Date arrivalDate, Company company) {
        this.flightID = flightID;
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
        this.company = company;
    
    }


    
    public boolean ticketBought(){
        if(placeLeft <= 0) return false;
        else{
            --placeLeft;
           return true;
        }
    }

    public String getDepartureCity() {
        return departureCity;
    }

    

    public void setDepartureCity(String string) {
        this.departureCity = string;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @JsonManagedReference
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int id){
        this.flightID = id;
    }

    @JsonBackReference
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Flight [arrivalCity=" + arrivalCity + ", arrivalDate=" + arrivalDate + ", company=" + company
                + ", departureCity=" + departureCity + ", departureDate=" + departureDate + ", flightID=" + flightID
                + ", tickets=" + tickets + "]";
    }

    public int getPlaceLeft() {
        return placeLeft;
    }

    public void setPlaceLeft(int placeLeft) {
        this.placeLeft = placeLeft;
    }
}
