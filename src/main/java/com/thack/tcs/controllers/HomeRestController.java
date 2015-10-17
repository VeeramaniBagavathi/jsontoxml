package com.thack.tcs.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.http.client.ClientProtocolException;
import org.iata.ndc.ClientException;
import org.iata.ndc.NdcClient;
import org.iata.ndc.schema.AirShoppingRQ;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thack.tcs.domain.Flight;
import com.thack.tcs.domain.FlightConnection;
import com.thack.tcs.responsemappers.AirShoppingResonseMapper;

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
    
    @RequestMapping(value = "/flight/itinerary", method = RequestMethod.GET)
    public List<FlightConnection> getFlightDetails() throws JAXBException, ClientProtocolException, ClientException, IOException {
 	   File file = new File("C:\\AirShopping.xml");
 		JAXBContext jaxbContext = JAXBContext.newInstance(AirShoppingRQ.class);
 		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
 		AirShoppingRQ request = (AirShoppingRQ) jaxbUnmarshaller.unmarshal(file);
 		NdcClient client = new NdcClient("http://iata.api.mashery.com/athena/api", "f6g8wh9pymvvmerp25t4ehzv");
 		return new AirShoppingResonseMapper().mapAirShoppingResponse(client.airShopping(request));
    }
}
