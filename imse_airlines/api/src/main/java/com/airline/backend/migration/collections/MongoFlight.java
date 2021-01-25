package com.airline.backend.migration.collections;


import com.airline.backend.entities.Company;
import com.airline.backend.entities.Ticket;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Document(collection = "flights")
public class MongoFlight {
    @Id
    private String id;
    private String departureCity;
    private Date departureDate;
    private String arrivalCity;
    private Date arrivalDate;
    private int placeLeft = 10;

    private MongoCompany mongoCompany;
    private List<MongoTicket> tickets;

    public MongoFlight() {}

    public MongoFlight(String id, String departureCity, Date departureDate, String arrivalCity, Date arrivalDate, MongoCompany company) {
        this.id = id;
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
        this.mongoCompany = company;
    }

    public MongoFlight(String id, String departureCity, Date departureDate, String arrivalCity, Date arrivalDate) {
        this.id = id;
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
    }

    public MongoFlight(String departureCity, Date departureDate, String arrivalCity, Date arrivalDate) {
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
    }

    public MongoFlight(String departureCity, Date departureDate, String arrivalCity, Date arrivalDate, MongoCompany mongoCompany) {
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
        this.mongoCompany = mongoCompany;
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

    public List<MongoTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<MongoTicket> tickets) {
        this.tickets = tickets;
    }

    public String getFlightID() {
        return id;
    }

    public void setFlightID(String id){
        this.id = id;
    }




    @Override
    public String toString() {
        return "Flight [arrivalCity=" + arrivalCity + ", arrivalDate=" + arrivalDate
                + ", departureCity=" + departureCity + ", departureDate=" + departureDate + ", flightID=" + id
                + ", tickets=" + tickets + "]";
    }

    public int getPlaceLeft() {
        return placeLeft;
    }

    public void setPlaceLeft(int placeLeft) {
        this.placeLeft = placeLeft;
    }

    public MongoCompany getCompany() {
        return  this.mongoCompany;
    }
}
