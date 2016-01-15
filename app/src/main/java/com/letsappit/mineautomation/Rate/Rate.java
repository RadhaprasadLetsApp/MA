package com.letsappit.mineautomation.Rate;

import java.util.Date;

/**
 * Created by Admin on 01-01-2016.
 */
public class Rate {
    private String code;
    private String description;
    private int distanceFrom;
    private int distanceTo;
    private float ratePerKm;
    private float fuelEligibility;
    private Date updatedDatetime;

    public Rate(String code, String description, int distanceFrom, int distanceTo, float ratePerKm, float fuelEligibility, Date updatedDatetime) {
        this.code = code;
        this.description = description;
        this.distanceFrom = distanceFrom;
        this.distanceTo = distanceTo;
        this.ratePerKm = ratePerKm;
        this.fuelEligibility = fuelEligibility;
        this.updatedDatetime = updatedDatetime;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDistanceFrom() {
        return this.distanceFrom;
    }

    public void setDistanceFrom(int distanceFrom) {
        this.distanceFrom = distanceFrom;
    }

    public int getDistanceTo() {
        return this.distanceTo;
    }

    public void setDistanceTo(int distanceTo) {
        this.distanceTo = distanceTo;
    }

    public float getRatePerKm() {
        return this.ratePerKm;
    }

    public void setRatePerKm(float ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public float getFuelEligibility() {
        return this.fuelEligibility;
    }

    public void setFuelEligibility(float fuelEligibility) {
        this.fuelEligibility = fuelEligibility;
    }

    public Date getUpdatedDatetime() {
        return this.updatedDatetime;
    }

    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
}
