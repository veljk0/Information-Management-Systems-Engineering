package com.airline.backend.controllers;

import java.util.List;

import com.airline.backend.entities.Flight;
import com.airline.backend.entities.Ticket;
import com.airline.backend.entities.User;
import com.airline.backend.migration.collections.MongoFlight;
import com.airline.backend.migration.collections.MongoTicket;
import com.airline.backend.migration.collections.MongoUser;
import com.airline.backend.migration.services.MongoFlightService;
import com.airline.backend.migration.services.MongoTicketService;
import com.airline.backend.migration.services.MongoUserService;
import com.airline.backend.services.FlightService;
import com.airline.backend.services.TicketService;

import com.airline.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tickets")
@CrossOrigin
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private MongoTicketService mongoTicketService;

    @Autowired
    private MongoUserService mongoUserService;

    @Autowired
    private MongoFlightService mongoFlightService;

    @GetMapping("/getTicket")
    @ResponseBody
    public Object getTicketByID(@RequestParam String ticketID) {
        if (mongoTicketService.getFlag()) {
            System.out.println("usloooooooooooooooo");
            return mongoTicketService.getTicketPerID(ticketID);
        }
        return ticketService.getTicketPerID(Integer.parseInt(ticketID));
    }

    @GetMapping(value = "/getAll")
    public List<?> getAllTickets() {
        if (mongoTicketService.getFlag()) {
            return mongoTicketService.getAllTickets();
        }
        return ticketService.getAllTickets();
    }

    @GetMapping("/details/{ticketId}")
    @ResponseBody
    public Object details(@PathVariable("ticketId") String ticketID){
        if(mongoTicketService.getFlag()){
            MongoTicket mongoTicket = mongoTicketService.getTicketPerID(ticketID);
            return mongoTicket.getFlight();
        }
        return flightService.ticketFlight(Integer.parseInt(ticketID));
    }

    @GetMapping("/addNewTicket/{flightId}/{price}")
    @ResponseBody
    public String createNewTicket(@PathVariable("flightId") String flightId, @PathVariable("price") int price){
        if(mongoTicketService.getFlag()){
            MongoFlight mongoFlight = mongoFlightService.getFlightPerID(flightId);
            MongoTicket mongoTicket = new MongoTicket(price, mongoFlight);
            mongoTicketService.addTicket(mongoTicket);
            return "MONGO TICKET CREATED!";
        }
        else {
            Ticket ticket = new Ticket(price, flightService.getFlightPerID(Integer.parseInt(flightId)));
            ticketService.addTicket(ticket);
            return "MYSQL TICKET CREATED";
        }
    }

    /*
    @PostMapping(value = "/addTicket/{flightID}")
    @ResponseBody
    public String createTicket(@RequestBody Ticket ticket, @PathVariable("flightID") String flightID) {
        if (mongoTicketService.getFlag()) {
            MongoFlight mongoFlight = mongoFlightService.getFlightPerID(flightID);//new MongoFlight(Integer.toString(flight.getFlightID()), flight.getDepartureCity(), flight.getDepartureDate(), flight.getArrivalCity(), flight.getArrivalDate());
            MongoTicket mongoTicket = new MongoTicket(ticket.getPrice(), mongoFlight);
            System.out.println("flight id: " + mongoFlight.getFlightID());
            mongoTicketService.addTicket(mongoTicket);
            mongoFlight.getTickets().add(mongoTicket);
            mongoFlightService.createFlight(mongoFlight);
            return "ticket created!";
        }
        return ticketService.addTicket(ticket);
    }*/

    @GetMapping(value = "/deleteAll")
    public String deleteAllTickets() {
        if (mongoTicketService.getFlag()) {
            return mongoTicketService.deleteAllTickets();
        }
        return ticketService.deleteAllTickets();
    }

    @GetMapping("/getFlightTickets")
    @ResponseBody
    public List<?> getFlightTickets(@RequestParam String flightId) {
        if (mongoTicketService.getFlag()) {
            return mongoTicketService.getFlightTickets(flightId);
        }
        return ticketService.getFlightTickets(Integer.parseInt(flightId));
    }

    @GetMapping("/getUserTickets")
    @ResponseBody
    public List<?> getUserTickets(@RequestParam int userID) {
        if (mongoTicketService.getFlag()) {
            return mongoTicketService.getUserTickets(Integer.toString(userID));
        }
        return ticketService.getUserTickets(userID);
    }


    @GetMapping("/getFlightAvailableTickets")
    @ResponseBody
    public List<?> getFlightAvailableTickets(@RequestParam String flightId) {
        if (mongoTicketService.getFlag()) {
            return mongoTicketService.getFlightAvailableTickets(flightId);
        }
        return ticketService.getFlightAvailableTickets(Integer.parseInt(flightId));
    }


    @GetMapping("/buyTicket/{id}/{flightID}/{email}")
    @ResponseBody
    public String buyTicket(@PathVariable("id") String ticketID,
                            @PathVariable("flightID") String flightID,
                            @PathVariable("email") String email
    ) {
        if (mongoTicketService.getFlag()) {
            MongoTicket ticket = mongoTicketService.getTicketPerID(ticketID);
            MongoUser user = mongoUserService.getUserPerEmail(email);
            MongoFlight flight = mongoFlightService.getFlightPerID(flightID);
            System.out.println("TICKET_____" + ticket.getTicketID());
            System.out.println("USER" + user.getId());
            System.out.println("FLIGHT_____" + flight.getFlightID());
            ticket.setUser(user);
            ticket.setSold(true);
            mongoTicketService.updateTicket(ticket);
            user.getTickets().add(mongoTicketService.getTicketPerID(ticketID));
            mongoUserService.updateUser(user);
            mongoFlightService.ticketBoughtForFlight(flightID);



            return ticketID;
        } else {
            System.out.println(ticketID);
            System.out.println(flightID);
            System.out.println(email);

            Ticket ticket = ticketService.getTicketPerID(Integer.parseInt(ticketID));
            User user = userService.getUserPerEmail(email);
            Flight flight = flightService.getFlightPerID(Integer.parseInt(flightID));
            System.out.println("TICKET_____" + ticket.getTicketID());
            System.out.println("USER" + user.getId());
            System.out.println("FLIGHT_____" + flight.getFlightID());
            ticket.setUser(user);
            ticket.setSold(true);
            flightService.ticketBoughtForFlight(Integer.parseInt(flightID));
            flightService.createFlight(flight);
            ticketService.addTicket(ticket);

            return "ID: " + ticketID;
        }


    }
}





    /*
    @PostMapping(value = "/updateTicket")
    public void updateTicket(@RequestBody Ticket ticket) {
        if(mongoTicketService.getFlag()) {
            Flight flight = ticket.getFlight();
            System.out.println("update flight id" + flight.getFlightID()  );
            MongoFlight mongoFlight = mongoFlightService.getFlightPerID(Integer.toString(flight.getFlightID()));//new MongoFlight(Integer.toString(flight.getFlightID()), flight.getDepartureCity(), flight.getDepartureDate(), flight.getArrivalCity(), flight.getArrivalDate());
            System.out.println("update flight id" + mongoFlight.getFlightID()  );
            MongoTicket mongoTicket = new MongoTicket(Integer.toString(ticket.getTicketID()), ticket.getPrice(), ticket.getSold(), mongoFlight);
            User user = ticket.getUser();
            //MongoUser mongoUser = new MongoUser(Integer.toString(user.getId()), user.getFirstName(), user.getSecondName(), user.getEmail(), user.getPassword());
            mongoTicket.setUser(mongoUserService.getUserPerEmail(user.getEmail()));
            MongoUser mogli = mongoUserService.getUserPerEmail(user.getEmail());
            mogli.getTickets().add(mongoTicket);
            mongoUserService.updateUser(mogli);
            mongoTicketService.updateTicket(mongoTicket);
        } else {
            ticketService.updateTicket(ticket);
        }
       }*/

    
    

