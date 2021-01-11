package com.airline.backend.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.airline.backend.entities.Flight;
import com.airline.backend.entities.Ticket;
import com.airline.backend.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    
	@Autowired
    private FlightRepository flightRepository;


    public List<Flight> findFlights(String city){
        List<Flight> flights = flightRepository.findAll();
        List<Flight> result = flights.stream().filter(f -> f.getDepartureCity().equals(city) || f.getArrivalCity().equals(city)).collect(Collectors.toList());
        return result;
    }
    
    public List<Ticket> getSoldtickets(int companyId){
    	List<Flight> flights = getCompanyFlights(companyId);
    	List<Ticket> tickets = new ArrayList<>();
    		for(Flight f : flights)
    			for(Ticket t : f.getTickets())
    				if(t.getSold()) tickets.add(t);
    	return tickets;
    }
    
    public int getCompanyWin(int companyId){
    	int result = 0;
    	List<Ticket> tickets = getSoldtickets(companyId);
    	for(Ticket t : tickets)
    		result += t.getPrice();
    	
    	return result;
    }
    
    public Flight getFlightPerID(int id){
        List<Flight> flights = flightRepository.findAll();
        Flight result = flights.stream().filter(f -> f.getFlightID() == id).findFirst().orElse(null);
        return result;
    }

    public Flight getFlightPerCity(String city){
        List<Flight> flights = flightRepository.findAll();
        Flight result = flights.stream().filter(f -> f.getArrivalCity().equals(city)).findFirst().orElse(null);
        return result;
    }


    public String createFlight(Flight flight) {
        flightRepository.save(flight);
        return "Flight: " + flight.getFlightID() + " created";
    }

   public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    public String deleteAll() {
        flightRepository.deleteAll();
        return "All flights deleted";
    }

    public boolean availability(int id){
        List<Flight> flights = flightRepository.findAll();
        Flight check = flights.stream().filter(x -> x.getFlightID() == id).findFirst().orElse(null);
        if(check.getPlaceLeft() <= 0) return false;
        else return true;
    }


    public String ticketBoughtForFlight(int id) {
        List<Flight> flights = flightRepository.findAll();
        Flight update = flights.stream().filter(x -> x.getFlightID() == id).findFirst().orElse(null);
        if (update != null) {
            if (update.ticketBought()) {
                flightRepository.save(update);
                return "flight updated";
            } else
                return "no place left";

        } else
            return "flight not found!";
    }

    public List<Flight> getCompanyFlights(int companyId) {
        List<Flight> flights = flightRepository.findAll();
        List<Flight> result = flights.stream().filter(f -> f.getCompany().getCompanyId() == companyId).collect(Collectors.toList());
        return result;
    }
    
    
    public List<Flight> getFlightsPerDate(int companyId, String from, String to) throws ParseException{
    	Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(from);
    	Date date2= new SimpleDateFormat("yyyy-MM-dd").parse(to);  
    	List<Flight> flights = getCompanyFlights(companyId);
    	List<Flight> result = flights.stream().filter(f -> f.getDepartureDate().after(date1) && f.getArrivalDate().before(date2)).collect(Collectors.toList());
    	return result;
    }

    public Flight ticketFlight(int ticketID){
        List<Flight> flights = flightRepository.findAll();
        for(Flight f:flights){
            for(Ticket t : f.getTickets())
                if(t.getTicketID() == ticketID) return f;
        }
            
        return null;
    }

    
    
    
}
