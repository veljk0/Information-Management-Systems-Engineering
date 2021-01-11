package com.airline.backend.controllers;

import java.text.ParseException;
import java.util.List;

import com.airline.backend.entities.Flight;
import com.airline.backend.entities.Ticket;
import com.airline.backend.services.FlightService;

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
@RequestMapping(value = "/flights")
@CrossOrigin
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/getFlight")
    @ResponseBody
    public Flight getFlightByID(@RequestParam int flightID) { return flightService.getFlightPerID(flightID); }

    @GetMapping("/available")
    @ResponseBody
    public Boolean available(@RequestParam int flightID) { return flightService.availability(flightID); }

    @PostMapping(value = "/addFlight")
    public String createFlight(@RequestBody Flight flight) { return flightService.createFlight(flight); }

    @GetMapping(value = "/deleteAll")
    public String deleteAll() { return flightService.deleteAll(); }

    @GetMapping(value = "/getAll")
    public List<Flight> getAllFlights() { return flightService.getAll(); }

    @GetMapping("/updateSeat")
    @ResponseBody
    public String updateFlight(@RequestParam int flightID) { return flightService.ticketBoughtForFlight(flightID); }

    @GetMapping("/getCompanyFlights")
    @ResponseBody
    public List<Flight> getCompanyFlights(@RequestParam int companyId) { return flightService.getCompanyFlights(companyId); }
    
    @GetMapping("/getCompanySoldTickets")
    @ResponseBody
    public List<Ticket> getSoldtickets(@RequestParam int companyId) { return flightService.getSoldtickets(companyId);}
    
    
    @GetMapping("/getCompanyWin")
    @ResponseBody
    public int getCompanyWin(@RequestParam int companyId) { return flightService.getCompanyWin(companyId); }
    
    @GetMapping("/getFlightsDate")
    @ResponseBody
    public List<Flight> getFlightsPerDate(@RequestParam int companyId, @RequestParam String from, @RequestParam String to) throws ParseException { return flightService.getFlightsPerDate(companyId, from, to); }
    
    @GetMapping("/findFlights")
    @ResponseBody
    public List<Flight> findFlights(@RequestParam String city) { return flightService.findFlights(city); }

    @GetMapping("/ticketFlight")
    @ResponseBody
    public Flight ticketFlight(@RequestParam int ticketID) { return flightService.ticketFlight(ticketID); }


}
