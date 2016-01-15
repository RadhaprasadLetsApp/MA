package com.letsappit.mineautomation.Common;

/**
 * Created by Admin on 30-12-2015.
 */
public class Vehicle {
    private String code;
    private String regNo;
    private VehicleType transporterType;

    public Vehicle() {
    }

    public Vehicle(VehicleType transporterType) {
        this.transporterType = transporterType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public VehicleType getTransporterType() {
        return transporterType;
    }




}
