package com.airline.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import com.airline.backend.entities.Ticket;
import com.airline.backend.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;
    
    public void updateTicket(Ticket ticket) {
    	Ticket t = getTicketPerID(ticket.getTicketID());
    	t.setUser(ticket.getUser());
    	t.setSold(true);
    	ticketRepository.save(t);
    }
    

   public String addTicket(Ticket ticket){
        ticketRepository.save(ticket);
                System.out.println("MAJMUNEEE: " + ticket);
        return "add ticket " + ticket.getTicketID() + " to database!";
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public String deleteAllTickets(){
        ticketRepository.deleteAll();
        return "All tickets deleted!";
    }

    public List<Ticket> getFlightTickets(int flightId) {
    	List<Ticket> tickets = ticketRepository.findAll();
    	List<Ticket> result = tickets.stream().filter(t -> t.getFlight().getFlightID() == flightId).collect(Collectors.toList());
    	return result;
    }

    
    public List<Ticket> getUserTickets(int userID) {
    	List<Ticket> tickets = ticketRepository.findAll();
        List<Ticket> result = tickets.stream().filter(t -> t.getUser().getId() == userID).collect(Collectors.toList());
    	return result;
    }
    
    
    public List<Ticket> getFlightAvailableTickets(@RequestParam int flightId){
    	List<Ticket> tickets = ticketRepository.findAll();
    	List<Ticket> flilter1 = tickets.stream().filter(t -> t.getFlight().getFlightID() == flightId).collect(Collectors.toList());
    	List<Ticket> flilter2 = flilter1.stream().filter(f -> f.getSold() == false).collect(Collectors.toList());
    	return flilter2;
    }

	
    public Ticket getTicketPerID(int ticketID) {
		 List<Ticket> tickets = ticketRepository.findAll();
	     Ticket result = tickets.stream().filter(f -> f.getTicketID() == ticketID).findFirst().orElse(null);
	     return result;
    }




	
    
}
