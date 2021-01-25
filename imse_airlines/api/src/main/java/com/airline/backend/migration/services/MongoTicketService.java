package com.airline.backend.migration.services;

import com.airline.backend.migration.collections.MongoTicket;
import com.airline.backend.migration.repositories.MongoTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MongoTicketService {

    @Autowired
    private MongoTicketRepository ticketRepository;

    private boolean flag;

    public MongoTicketService(){
        this.flag = false;
    }
    public void updateTicket(MongoTicket ticket) {
       // MongoTicket t = getTicketPerID(ticket.getTicketID());
        System.out.println("BRACALAAAAAA: " + ticket.getUser().getFirstName());
       // t.setUser(ticket.getUser());
       // t.setSold(true);
        ticketRepository.save(ticket);
    }


    public String addTicket(MongoTicket ticket){
        ticketRepository.save(ticket);
        System.out.println("MAJMUNEEE: " + ticket);
        return "add ticket " + ticket.getTicketID() + " to database!";
    }

    public List<MongoTicket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public String deleteAllTickets(){
        ticketRepository.deleteAll();
        return "All tickets deleted!";
    }

    public List<MongoTicket> getFlightTickets(String flightId) {
        List<MongoTicket> tickets = ticketRepository.findAll();
        List<MongoTicket> result = tickets.stream().filter(t -> t.getFlight().getFlightID().equals(flightId)).collect(Collectors.toList());
        return result;
    }


    public List<MongoTicket> getUserTickets(String userID) {
        List<MongoTicket> tickets = ticketRepository.findAll();
        List<MongoTicket> result = tickets.stream().filter(t -> t.getUser().getId().equals(userID)).collect(Collectors.toList());
        return result;
    }


    public List<MongoTicket> getFlightAvailableTickets(String flightId){
        List<MongoTicket> tickets = ticketRepository.findAll();
        List<MongoTicket> flilter1 = tickets.stream().filter(t -> t.getFlight().getFlightID().equals(flightId)).collect(Collectors.toList());
        List<MongoTicket> flilter2 = flilter1.stream().filter(f -> !f.getSold()).collect(Collectors.toList());
        return flilter2;
    }


    public MongoTicket getTicketPerID(String ticketID) {
        List<MongoTicket> tickets = ticketRepository.findAll();
        MongoTicket result = tickets.stream().filter(f -> f.getTicketID().equals(ticketID)).findFirst().orElse(null);
        return result;
    }

    public void setFlag(boolean b) {
        this.flag = b;
    }

    public boolean getFlag() {
        return this.flag;
    }
}
