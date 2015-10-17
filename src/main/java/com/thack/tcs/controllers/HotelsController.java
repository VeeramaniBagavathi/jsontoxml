/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.thack.tcs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotelbeds.distribution.hotel_api_sdk.types.HotelSDKException;
import com.thack.tcs.domain.BookingConfirmation;
import com.thack.tcs.domain.HotelBookings;
import com.thack.tcs.services.AvailabilityService;

/**
 *
 *
 * @author TCS (x076459)
 * @since Oct 17, 2015
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/hotels")
public class HotelsController {

    @Autowired
    private AvailabilityService availabilityService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public HotelBookings testHotel(@RequestParam(value = "numberOfAdults", defaultValue = "1", required = false) String numberOfAdults,
                    @RequestParam(value = "numberOfChildren", defaultValue = "0", required = false) String numberOfChildren, @RequestParam(value = "lat", required = false) String lat,
                    @RequestParam(value = "longi") String longi, @RequestParam(value = "priceRange") String priceRange, @RequestParam(value = "checkinDate") String checkinDate,
                    @RequestParam(value = "checkoutDate") String checkoutDate, @RequestParam(value = "kmsAround") String kmsAround) throws HotelSDKException {

        HotelBookings hotelBookings = availabilityService.getAvailabilities(numberOfAdults, numberOfChildren, lat, longi, priceRange, checkinDate, checkoutDate);
        return hotelBookings;
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    @ResponseBody
    public BookingConfirmation bookHotel(@RequestBody HotelBookings hotelBookings) throws HotelSDKException {

        String ref = availabilityService.bookHotels(hotelBookings);
        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setBookingRef(ref);
        bookingConfirmation.setResult("SUCCESS");
        return bookingConfirmation;
    }

}
