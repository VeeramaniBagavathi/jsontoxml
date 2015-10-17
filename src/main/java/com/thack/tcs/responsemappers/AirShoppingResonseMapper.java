/**
 * 
 */
package com.thack.tcs.responsemappers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.iata.ndc.schema.AirShoppingRS;
import org.iata.ndc.schema.AirShoppingRS.DataLists;
import org.iata.ndc.schema.ListOfFlightSegmentType;

import com.thack.tcs.domain.FlightConnection;


/**
 * @author x078862
 *
 */
public class AirShoppingResonseMapper {
	
	public List<FlightConnection> mapAirShoppingResponse(AirShoppingRS response) {
		DataLists dataLists = response.getDataLists();
		List<FlightConnection> flightConnections = new ArrayList<FlightConnection>();
		FlightConnection connection = null;
		int count = 0;
		for (ListOfFlightSegmentType flightSegmentType : dataLists.getFlightSegmentList()) {
			connection = new FlightConnection();
			connection.setFlightNumber(flightSegmentType.getOperatingCarrier().getFlightNumber().getOperationalSuffix() + " " + flightSegmentType.getOperatingCarrier().getFlightNumber().getValue());
			connection.setDepartureStation(dataLists.getOriginDestinationList().get(0).getDepartureCode().getValue());
			connection.setArrivalStation((dataLists.getOriginDestinationList().get(0).getArrivalCode().getValue()));
			connection.setDepartureDateTime(toDate(flightSegmentType.getDeparture().getDate()) + " " + flightSegmentType.getDeparture().getTime());
			connection.setArrivalDateTime((toDate(flightSegmentType.getArrival().getDate()) + " " + flightSegmentType.getArrival().getTime()));
			connection.setPrice(response.getOffersGroup().getAirlineOffers().get(count).getAirlineOffer().get(0).getPricedOffer().getOfferPrice().get(0).getRequestedDate().getPriceDetail().getBaseAmount().getValue().toString());
			connection.setCurrency(response.getOffersGroup().getAirlineOffers().get(count).getAirlineOffer().get(0).getTotalPrice().getDetailCurrencyPrice().getTotal().getCode());
			flightConnections.add(connection);
		}
		return flightConnections;
		
	}

	

	public static String toDate(XMLGregorianCalendar calendar){
        if(calendar == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(calendar.toGregorianCalendar().getTime());
    }


}
