package com.thack.tcs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thack.tcs.domain.Flight;

@RestController
public class HomeRestController {

    @RequestMapping(value="rest/test")
    public Flight testRestService() {
        Flight flight = new Flight();
        flight.setFlightNumber("KL0069");
        flight.setFlightDepartureDate("18-Oct-2015 12:30");
        flight.setArrStation("HAM");
        flight.setDepStation("AMS");
        return flight;
    }
}
