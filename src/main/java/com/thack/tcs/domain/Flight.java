/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.thack.tcs.domain;

public class Flight {

    private String flightNumber;
    private String flightDepartureDate;
    private String arrStation;
    private String depStation;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(final String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightDepartureDate() {
        return flightDepartureDate;
    }

    public void setFlightDepartureDate(final String flightDepartureDate) {
        this.flightDepartureDate = flightDepartureDate;
    }

    public String getArrStation() {
        return arrStation;
    }

    public void setArrStation(final String arrStation) {
        this.arrStation = arrStation;
    }

    public String getDepStation() {
        return depStation;
    }

    public void setDepStation(final String depStation) {
        this.depStation = depStation;
    }

}
