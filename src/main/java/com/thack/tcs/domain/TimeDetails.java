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
public class TimeDetails {

    private String checkinTime;

    private String checkoutTime;

    /**
     * @return the checkinTime
     */
    public String getCheckinTime() {
        return checkinTime;
    }

    /**
     * @param checkinTime the checkinTime to set
     */
    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    /**
     * @return the checkoutTime
     */
    public String getCheckoutTime() {
        return checkoutTime;
    }

    /**
     * @param checkoutTime the checkoutTime to set
     */
    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

}
