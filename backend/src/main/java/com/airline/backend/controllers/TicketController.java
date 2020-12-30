package com.airline.backend.controllers;

import java.util.List;

import com.airline.backend.entities.Ticket;
import com.airline.backend.entities.User;
import com.airline.backend.services.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tickets")
@CrossOrigin
public class TicketController {
    
    @Autowired
    private TicketService ticketService;

    @GetMapping(value = "/getAll")
    public List<Ticket> getAllTickets(){ return ticketService.getAllTickets(); }

    @PostMapping(value = "/addTicket")
    public String addUser(@RequestBody Ticket ticket) { return ticketService.addTicket(ticket); }

    @GetMapping(value = "/deleteAll")
    public String deleteAllTickets(){ return ticketService.deleteAllTickets(); }
    
    @GetMapping("/getFlightTickets")
    @ResponseBody
    public List<Ticket> getFlightTickets(@RequestParam int flightId) { return ticketService.getFlightTickets(flightId); }
    
    
    @GetMapping("/getFlightAvailableTickets")
    @ResponseBody
    public List<Ticket> getFlightAvailableTickets(@RequestParam int flightId) { return ticketService.getFlightAvailableTickets(flightId); }
    
    @GetMapping("/buy")
    @ResponseBody
    public boolean buyTicket(@RequestParam int ticketID, @RequestParam User user) { return ticketService.buyTicket(ticketID, user); }
    
    
   
    
    
}
