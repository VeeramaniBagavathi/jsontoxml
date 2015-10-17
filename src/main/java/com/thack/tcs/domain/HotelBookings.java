/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.thack.tcs.domain;

import java.util.List;

/**
 *
 *
 * @author TCS (x076459)
 * @since Oct 17, 2015
 * @version 1.0
 */
public class HotelBookings {

    private List<Hotels> hotels;

    private List<CustomerDetails> customers;

    private TimeDetails timeDetails;

    /**
     * @return the hotels
     */
    public List<Hotels> getHotels() {
        return hotels;
    }

    /**
     * @param hotels the hotels to set
     */
    public void setHotels(List<Hotels> hotels) {
        this.hotels = hotels;
    }

    /**
     * @return the customers
     */
    public List<CustomerDetails> getCustomers() {
        return customers;
    }

    /**
     * @param customers the customers to set
     */
    public void setCustomers(List<CustomerDetails> customers) {
        this.customers = customers;
    }

    /**
     * @return the timeDetails
     */
    public TimeDetails getTimeDetails() {
        return timeDetails;
    }

    /**
     * @param timeDetails the timeDetails to set
     */
    public void setTimeDetails(TimeDetails timeDetails) {
        this.timeDetails = timeDetails;
    }

}
