/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.thack.tcs.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbeds.distribution.hotel_api_model.auto.common.SimpleTypes.RateType;
import com.hotelbeds.distribution.hotel_api_model.auto.messages.AvailabilityRS;
import com.hotelbeds.distribution.hotel_api_model.auto.messages.BookingRS;
import com.hotelbeds.distribution.hotel_api_model.auto.messages.StatusRS;
import com.hotelbeds.distribution.hotel_api_model.auto.model.Hotel;
import com.hotelbeds.distribution.hotel_api_model.auto.model.Rate;
import com.hotelbeds.distribution.hotel_api_model.auto.model.Room;
import com.hotelbeds.distribution.hotel_api_sdk.HotelApiClient;
import com.hotelbeds.distribution.hotel_api_sdk.helpers.AvailRoom;
import com.hotelbeds.distribution.hotel_api_sdk.helpers.AvailRoom.AvailRoomBuilder;
import com.hotelbeds.distribution.hotel_api_sdk.helpers.Availability;
import com.hotelbeds.distribution.hotel_api_sdk.helpers.Booking;
import com.hotelbeds.distribution.hotel_api_sdk.helpers.ConfirmRoom;
import com.hotelbeds.distribution.hotel_api_sdk.helpers.ConfirmRoom.ConfirmRoomBuilder;
import com.hotelbeds.distribution.hotel_api_sdk.helpers.RoomDetail.GuestType;
import com.hotelbeds.distribution.hotel_api_sdk.types.HotelSDKException;
import com.thack.tcs.domain.CustomerDetails;
import com.thack.tcs.domain.HotelBookings;
import com.thack.tcs.domain.Hotels;
import com.thack.tcs.domain.TimeDetails;

/**
 *
 *
 * @author TCS (x076459)
 * @since Oct 17, 2015
 * @version 1.0
 */
@Service
public class AvailabilityService {

    public HotelBookings getAvailabilities(String numberOfAdults, String numberOfChildren, String lat, String longi, String priceRange, String checkinDate, String checkoutDate) throws HotelSDKException {
        HotelBookings hotelBookings = new HotelBookings();

        HotelApiClient apiClient = new HotelApiClient("5pwp98rfeqfwktrv66yn8nf5", "UsYDN4KQDc");
        apiClient.setReadTimeout(40000);
        apiClient.init();
        StatusRS statusRS = apiClient.status();
        System.out.println(statusRS);

        AvailRoomBuilder availRoom = AvailRoom.builder().adults(Integer.parseInt(numberOfAdults)).children(numberOfChildren != null ? Integer.parseInt(numberOfChildren) : null);

        LocalDate checkIn = null;
        LocalDate checkOut = null;
        if (checkinDate != null && checkoutDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            checkIn = LocalDate.parse(checkinDate, formatter);
            checkOut = LocalDate.parse(checkoutDate, formatter);
        }
        else {
            checkIn = LocalDate.now().plusDays(100);
            checkOut = LocalDate.now().plusDays(100 + 1);
        }

        // Availability availability = Availability.builder().checkIn(checkIn).checkOut(checkOut)./* destination("DEU"). */addRoom(availRoom).limitHotelsTo(2).ratesHigherThan(new BigDecimal(50))
        // .ratesLowerThan(new BigDecimal(350)).withinThis(new Availability.Circle("2.646633999999949", "39.57119", 20)).build();

        Availability availability = Availability.builder().checkIn(checkIn).checkOut(checkOut)./* destination("DEU"). */addRoom(availRoom).limitHotelsTo(5).ratesHigherThan(new BigDecimal(priceRange.split("-")[0]))
                        .ratesLowerThan(new BigDecimal(priceRange.split("-")[1])).withinThis(new Availability.Circle(longi, lat, 20)).build();

        // https://maps.googleapis.com/maps/api/geocode/json?address=Paris&key=AIzaSyBQfNJjW3pfQj-ypeM8OEKs6Rf-vYGrq94

        AvailabilityRS availabilityRS = apiClient.availability(availability);

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(availability));
            System.out.println(mapper.writeValueAsString(availabilityRS));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        List<Hotel> bookableHotels = new ArrayList<>();
        boolean hotelBookable = true;
        for (Hotel hotel : availabilityRS.getHotels().getHotels()) {
            for (Room room : hotel.getRooms()) {
                for (Rate rate : room.getRates()) {
                    if (!RateType.BOOKABLE.toString().equalsIgnoreCase(rate.getRateType().toString())) {
                        hotelBookable = false;
                    }
                }
            }
            if (hotelBookable) {
                bookableHotels.add(hotel);
            }
        }

        List<Hotels> hotels = new ArrayList<>();
        for (Hotel bookableHotel : bookableHotels) {
            Hotels hotel = new Hotels();
            hotel.setId(bookableHotel.getCode().toString());
            hotel.setAmount(bookableHotel.getRooms().get(0).getRates().get(0).getNet().toString());
            hotel.setName(bookableHotel.getName());
            hotel.setCurrency(bookableHotel.getCurrency());
            hotel.setRateKey(bookableHotel.getRooms().get(0).getRates().get(0).getRateKey());
            hotels.add(hotel);
        }
        hotelBookings.setHotels(hotels);

        TimeDetails timeDetails = new TimeDetails();
        timeDetails.setCheckinTime(checkinDate);
        timeDetails.setCheckoutTime(checkoutDate);
        hotelBookings.setTimeDetails(timeDetails);

        return hotelBookings;
    }

    public String bookHotels(HotelBookings hotelBookings) throws HotelSDKException {

        HotelApiClient apiClient = new HotelApiClient("5pwp98rfeqfwktrv66yn8nf5", "UsYDN4KQDc");
        apiClient.setReadTimeout(40000);
        apiClient.init();
        StatusRS statusRS = apiClient.status();
        System.out.println(statusRS);

        String rateKey = hotelBookings.getHotels().get(0).getRateKey();
        ConfirmRoomBuilder confirmRoom = ConfirmRoom.builder();

        CustomerDetails customerDetails = hotelBookings.getCustomers().get(0);
        confirmRoom.detailed(GuestType.ADULT, Integer.valueOf(customerDetails.getAge()), customerDetails.getFirstName(), customerDetails.getLastName(), 1);

        BookingRS bookingRS = apiClient.confirm(Booking.builder().withHolder("Perico", "Palotes").clientReference("SDK Test").remark("***SDK***TESTING").addRoom(rateKey, confirmRoom).build());

        return bookingRS.getBooking().getReference();

    }

}
