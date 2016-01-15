package com.letsappit.mineautomation.Common;

import java.util.Date;

/**
 * Created by Admin on 30-12-2015.
 */
public class Person {

    public Person() { }

    public Person(String code, String firstName, String lastName, String address, int mobileNumber, String emailID, Date updatedOn) {
        this.code = code;
        this.firstName = firstName;
        this.LastName = lastName;
        this.Address = address;
        this.mobileNumber = mobileNumber;
        this.emailID = emailID;
        this.updatedOn = updatedOn;
    }

    private String code;
    private String firstName;
    private String LastName;
    private String Address;
    private int mobileNumber;
    private String emailID;
    private Date updatedOn;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public int getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailID() {
        return this.emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
