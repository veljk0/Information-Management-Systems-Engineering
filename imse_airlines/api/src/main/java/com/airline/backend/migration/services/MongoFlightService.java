package com.airline.backend.migration.services;

import com.airline.backend.entities.Flight;
import com.airline.backend.entities.Ticket;
import com.airline.backend.migration.collections.MongoFlight;
import com.airline.backend.migration.collections.MongoTicket;
import com.airline.backend.migration.repositories.MongoFlightRepository;
import com.airline.backend.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MongoFlightService {

    @Autowired
    private MongoFlightRepository flightRepository;

    private boolean flag = false;

    public List<MongoFlight> findFlights(String city){
        List<MongoFlight> flights = flightRepository.findAll();
        List<MongoFlight> result = flights.stream().filter(f -> f.getDepartureCity().equals(city) || f.getArrivalCity().equals(city)).collect(Collectors.toList());
        return result;
    }

    public List<MongoTicket> getSoldtickets(String companyId){
        List<MongoFlight> flights = new LinkedList<>();
        for(MongoFlight mongoFlight: getAll()) {
            if(mongoFlight.getCompany().getCompanyId().equals(companyId)){
                flights.add(mongoFlight);
            }
        }
        List<MongoTicket> tickets = new ArrayList<>();
        for(MongoFlight f : flights)
            for(MongoTicket t : f.getTickets())
                if(t.getSold()) tickets.add(t);
        return tickets;
    }



    public int getCompanyWin(String companyId){
        int result = 0;
        List<MongoTicket> tickets = getSoldtickets(companyId);
        for(MongoTicket t : tickets)
            result += t.getPrice();

        return result;
    }

    public MongoFlight getFlightPerID(String id){
        List<MongoFlight> flights = flightRepository.findAll();
        MongoFlight result = flights.stream().filter(f -> f.getFlightID().equals(id)).findFirst().orElse(null);
        return result;
    }

    public MongoFlight getFlightPerCity(String city){
        List<MongoFlight> flights = flightRepository.findAll();
        MongoFlight result = flights.stream().filter(f -> f.getArrivalCity().equals(city)).findFirst().orElse(null);
        return result;
    }


    public String createFlight(MongoFlight flight) {
        flightRepository.save(flight);
        return "Flight: " + flight.getFlightID() + " created";
    }

    public List<MongoFlight> getAll() {
        return flightRepository.findAll();
    }

    public String deleteAll() {
        flightRepository.deleteAll();
        return "All flights deleted";
    }

    public boolean availability(String id){
        List<MongoFlight> flights = flightRepository.findAll();
        MongoFlight check = flights.stream().filter(x -> x.getFlightID().equals(id)).findFirst().orElse(null);
        if(check.getPlaceLeft() <= 0) return false;
        else return true;
    }


    public String ticketBoughtForFlight(String id) {
        List<MongoFlight> flights = flightRepository.findAll();
        MongoFlight update = flights.stream().filter(x -> x.getFlightID().equals(id)).findFirst().orElse(null);
        if (update != null) {
            if (update.ticketBought()) {
                flightRepository.save(update);
                return "flight updated";
            } else
                return "no place left";

        } else
            return "flight not found!";
    }

    public List<MongoFlight> getCompanyFlights(String companyId) {
        List<MongoFlight> flights = flightRepository.findAll();
        List<MongoFlight> result = flights.stream().filter(f -> f.getCompany().getCompanyId().equals(companyId)).collect(Collectors.toList());
        return result;
    }


    public List<MongoFlight> getFlightsPerDate(String companyId, String from, String to) throws ParseException {
        Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(from);
        Date date2= new SimpleDateFormat("yyyy-MM-dd").parse(to);
        List<MongoFlight> flights = getCompanyFlights(companyId);
        List<MongoFlight> result = flights.stream().filter(f -> f.getDepartureDate().after(date1) && f.getArrivalDate().before(date2)).collect(Collectors.toList());
        return result;
    }



    public MongoFlight ticketFlight(String ticketID){
        List<MongoFlight> flights = flightRepository.findAll();
        for(MongoFlight f:flights){
            for(MongoTicket t : f.getTickets())
                if(t.getTicketID().equals(ticketID)) return f;
        }

        return null;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

    public void updateFlight(MongoFlight mongoFlight) {
        flightRepository.save(mongoFlight);
    }
}
