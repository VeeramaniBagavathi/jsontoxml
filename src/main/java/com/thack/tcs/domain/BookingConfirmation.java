/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.thack.tcs.domain;

/**
 *
 *
 * @author TCS (x076459)
 * @since Oct 17, 2015
 * @version 1.0
 */
public class BookingConfirmation {

    private String result;

    private String bookingRef;

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the bookingRef
     */
    public String getBookingRef() {
        return bookingRef;
    }

    /**
     * @param bookingRef the bookingRef to set
     */
    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }

}
