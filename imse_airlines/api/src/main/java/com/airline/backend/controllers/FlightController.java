package com.airline.backend.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.airline.backend.entities.Company;
import com.airline.backend.entities.Flight;
import com.airline.backend.entities.Ticket;
import com.airline.backend.migration.collections.MongoCompany;
import com.airline.backend.migration.collections.MongoFlight;
import com.airline.backend.migration.collections.MongoTicket;
import com.airline.backend.migration.services.MongoCompanyService;
import com.airline.backend.migration.services.MongoFlightService;
import com.airline.backend.migration.services.MongoTicketService;
import com.airline.backend.services.CompanyService;
import com.airline.backend.services.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/flights")
@CrossOrigin
public class FlightController {
    @Autowired
    private FlightService flightService;
    


    @Autowired
    private MongoFlightService mongoFlightService;
    
    @Autowired
    private MongoTicketService mongoTicketService;

    @Autowired
    private MongoCompanyService mongoCompanyService;
    
    @Autowired
    private CompanyService companyService;

    @GetMapping("/getFlight")
    @ResponseBody
    public Object getFlightByID(@RequestParam String flightID) {
        if (mongoFlightService.getFlag()) {
            return mongoFlightService.getFlightPerID(flightID);
        }
        return flightService.getFlightPerID(Integer.parseInt(flightID));
    }

    @GetMapping("/available")
    @ResponseBody
    public Boolean available(@RequestParam String flightID) {
        if (mongoFlightService.getFlag()) {
            return mongoFlightService.availability(flightID);
        }
        return flightService.availability(Integer.parseInt(flightID));
    }

    @GetMapping("/addNewFlight/{comanyName}/{departureCity}/{departureDate}/{arrivalCity}/{arrivalDate}")
    @ResponseBody
    public String flightCreation(@PathVariable("comanyName") String companyName,
                          @PathVariable("departureCity") String departureCity,
                          @PathVariable("departureDate") String departureDate,
                          @PathVariable("arrivalCity") String arrivalCity,
                          @PathVariable("arrivalDate") String arrivalDate
    )
    {
    	
    	
    	if (mongoFlightService.getFlag()) {
    		Date date1 = null;
	    	Date date2 = null;
	    	try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDate); 
			} catch (ParseException e) {e.printStackTrace();} 
    	
	    	MongoCompany mongocompany = mongoCompanyService.getCompanyPerName(companyName);
	    	MongoFlight mongoflight = new MongoFlight(departureCity, date1, arrivalCity, date2, mongocompany);
	    	mongoFlightService.createFlight(mongoflight);
	    	
	    	
	    	
	    	return "mongodb flight created";
    	}
    	else {
	    	System.out.println("COMANY NAME " + companyName);
	    	System.out.println("DEPARTURE CITY " + departureCity);
	    	System.out.println("DEPARUTRE DATE " + departureDate);
	    	System.out.println("ARRIVAL CITY " + arrivalCity);
	    	System.out.println("ARRIVAL DATE " + arrivalDate);
	    	Date date1 = null;
	    	Date date2 = null;
	    	try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDate); 
			} catch (ParseException e) {e.printStackTrace();} 
    	
	    	Company company = companyService.getCompanyPerName(companyName);
	    	Flight flight = new Flight(departureCity, date1, arrivalCity, date2, company);
	    	flightService.createFlight(flight);
	    	return "mysql flight created";
    	}
    }

    @PostMapping(value = "/addFlight")
    public String createFlight(@RequestBody Flight flight) {
        if (mongoFlightService.getFlag()) {
            MongoCompany mongoCompany = mongoCompanyService.getCompanyPerName(flight.getCompany().getCompanyName());
            MongoFlight mongoFlight = new MongoFlight(flight.getDepartureCity(), flight.getDepartureDate(), flight.getArrivalCity(), flight.getArrivalDate(), mongoCompany);
            mongoFlightService.createFlight(mongoFlight);
            List<MongoFlight> flights = mongoCompany.getFlights();
            flights.add(mongoFlight);
            MongoCompany mongoCompany1 = new MongoCompany(
                    mongoCompany.getCompanyId(),
                    mongoCompany.getCompanyName(),
                    flights
            );
            mongoCompanyService.addCompany(mongoCompany1);
            return "mongo flight created";
        }
        flightService.createFlight(flight);
        return "mysql flight created";
    }

    @GetMapping(value = "/deleteAll")
    public String deleteAll() {
        if (mongoFlightService.getFlag()) {
            return mongoFlightService.deleteAll();
        }
        return flightService.deleteAll();
    }

    @GetMapping(value = "/getAll")
    public List<?> getAllFlights() {
        if (mongoFlightService.getFlag()) {
            return mongoFlightService.getAll();
        }
        return flightService.getAll();
    }

    @GetMapping("/updateSeat")
    @ResponseBody
    public String updateFlight(@RequestParam String flightID) {
        if (mongoFlightService.getFlag()) {
            return mongoFlightService.ticketBoughtForFlight(flightID);
        }
        return flightService.ticketBoughtForFlight(Integer.parseInt(flightID));
    }

    @GetMapping("/getCompanyFlights")
    @ResponseBody
    public List<Flight> getCompanyFlights(@RequestParam String companyId) {
        if (mongoFlightService.getFlag()) {
            mongoFlightService.getCompanyFlights(companyId);
        }
        return flightService.getCompanyFlights(Integer.parseInt(companyId));
    }

    @GetMapping("/getCompanySoldTickets")
    @ResponseBody
    public List<?> getSoldtickets(@RequestParam String companyId) {
        if (mongoFlightService.getFlag()) {
            return mongoFlightService.getSoldtickets(companyId);
        }
        return flightService.getSoldtickets(Integer.parseInt(companyId));
    }


    @GetMapping("/getCompanyWin")
    @ResponseBody
    public int getCompanyWin(@RequestParam String companyId) {
        if (mongoFlightService.getFlag()) {
            return mongoFlightService.getCompanyWin(companyId);
        }
        return flightService.getCompanyWin(Integer.parseInt(companyId));
    }

    @GetMapping("/getFlightsDate")
    @ResponseBody
    public List<?> getFlightsPerDate(@RequestParam String companyId, @RequestParam String from, @RequestParam String to) throws ParseException {
        if (mongoFlightService.getFlag()) {
            return mongoFlightService.getFlightsPerDate(companyId, from, to);
        }
        return flightService.getFlightsPerDate(Integer.parseInt(companyId), from, to);
    }

    @GetMapping("/findFlights")
    @ResponseBody
    public List<?> findFlights(@RequestParam String city) {
        if (mongoFlightService.getFlag()) {
            mongoFlightService.findFlights(city);
        }
        return flightService.findFlights(city);
    }

    @GetMapping("/ticketFlight")
    @ResponseBody
    public Object ticketFlight(@RequestParam String ticketID) {
        if(mongoFlightService.getFlag()) {
            return mongoFlightService.ticketFlight(ticketID);
        }
        return flightService.ticketFlight(Integer.parseInt(ticketID));
    }
    
    @GetMapping("/mongoSold/{companyName}")
    @ResponseBody
    public List<?> soldTickets(@PathVariable("companyName") String companyName){
    	 if(mongoFlightService.getFlag()) {
    		 List<MongoFlight> flights = mongoFlightService.getAll();
    		 List<MongoFlight> filter1 = flights.stream().filter(f -> f.getCompany().getCompanyName().equals(companyName)).collect(Collectors.toList());
    		 List<MongoTicket> tickets = new ArrayList<>();
    		 for(MongoFlight fliga : filter1) 
    			 for(MongoTicket t : mongoTicketService.getAllTickets()) 
    				 if(t.getFlight().getFlightID().equals(fliga.getFlightID()) && t.getSold()) tickets.add(t);
    		 return tickets;
    		 
    	 }
    	 else {
    		 List<Flight> flights = flightService.getAll();
    		 List<Flight> filter1 = flights.stream().filter(f -> f.getCompany().getCompanyName().equals(companyName)).collect(Collectors.toList());
    		 List<Ticket> tickets = new ArrayList<>();
    		 for(Flight fliga : filter1) 
    			 for(Ticket t : fliga.getTickets()) 
    				 if(t.getSold()) tickets.add(t);
    		 return tickets;
    	 }
    }
    
    @GetMapping("/mongoProfit/{companyName}")
    @ResponseBody
    public int profit(@PathVariable("companyName") String companyName){
    	 if(mongoFlightService.getFlag()) {
    		 List<MongoFlight> flights = mongoFlightService.getAll();
    		 List<MongoFlight> filter1 = flights.stream().filter(f -> f.getCompany().getCompanyName().equals(companyName)).collect(Collectors.toList());
    		 List<MongoTicket> tickets = new ArrayList<>();
    		 for(MongoFlight fliga : filter1) 
    			 for(MongoTicket t : mongoTicketService.getAllTickets()) 
    				 if(t.getFlight().getFlightID().equals(fliga.getFlightID()) && t.getSold()) tickets.add(t);
	    	int sum = 0;
	    	for(MongoTicket t : tickets) {
	    		sum+=t.getPrice();
	    	}
	    	
	    	return sum;
    	 }
    	 
    	 else {
    		 List<Flight> flights = flightService.getAll();
    		 List<Flight> filter1 = flights.stream().filter(f -> f.getCompany().getCompanyName().equals(companyName)).collect(Collectors.toList());
    		 List<Ticket> tickets = new ArrayList<>();
	    	for(Flight fliga : filter1) 
	    		for(Ticket t : fliga.getTickets()) 
	    			if(t.getSold()) tickets.add(t);
	    	int sum = 0;
	    	for(Ticket t : tickets) {
	    		sum+=t.getPrice();
	    	}
	    	
	    	return sum;
    	 }
   }
}


