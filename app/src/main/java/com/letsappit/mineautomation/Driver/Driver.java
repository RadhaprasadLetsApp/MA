package com.letsappit.mineautomation.Driver;

import com.letsappit.mineautomation.Common.Person;

import java.util.Date;

/**
 * Created by Admin on 13-01-2016.
 */
public class Driver extends Person {

    private String bloodGroup;
    private String truckCode;

    public Driver(String code, String firstName, String lastName, String address, int mobileNumber, String emailID, Date updatedOn, String bloodGroup, String truckCode) {
        super(code, firstName, lastName, address, mobileNumber, emailID, updatedOn);
        this.bloodGroup = bloodGroup;
        this.truckCode = truckCode;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getTruckCode() {
        return this.truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }
}
