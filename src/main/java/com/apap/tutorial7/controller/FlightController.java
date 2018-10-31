package com.apap.tutorial7.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.apap.tutorial7.rest.PilotDetail;
import com.apap.tutorial7.rest.Setting;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/flight")
public class FlightController {

	// private final static Logger LOGGER =
	// Logger.getLogger(RestController.class.getName());

	@Autowired
	private FlightService flightService;

	@Autowired
	private PilotService pilotService;

	@PostMapping("/add")
	public FlightModel addFlight(@RequestBody FlightModel flight) {
		return flightService.addFlight(flight);
	}

    @PutMapping("/update/{flightID}")
    public String updateFlight(@PathVariable("flightID") long flightID,
                               @RequestParam(value = "destination", required = false) String destination,
                               @RequestParam(value = "origin", required = false) String origin) {
        FlightModel flight = flightService.getFlightById(flightID);
        
        if(flight.equals(null)) {
    		return "Couldn't find your flight";
    	}
    	
        flight.setDestination(destination);
        flight.setOrigin(origin);
        flightService.updateFlight(flightID, flight);
        return "flight update success";
    	
    }

	@GetMapping("/view/{flightNumber}")
	public Optional<FlightModel> getFlight(@PathVariable("flightNumber") String flightNumber) {
		return flightService.getFlightDetailByFlightNumber(flightNumber);
	}

    @GetMapping("/all")
    public List<FlightModel> getListFlight() {
        return flightService.getAllFlight();
    }

    @DeleteMapping(value = "/")
    public String deleteFlight(@RequestParam("flightID") long flightID) {
    	flightService.deleteFlightById(flightID);
    	return "flight has been deleted";
    }
}