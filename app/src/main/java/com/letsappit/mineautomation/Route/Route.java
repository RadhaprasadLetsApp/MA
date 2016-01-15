package com.letsappit.mineautomation.Route;

import com.letsappit.mineautomation.Common.LocationType;

import java.util.Date;

/**
 * Created by Admin on 01-01-2016.
 */
public class Route {
    private String code;
    private String description;
    private int distance;
    private String rateCode;
    private String startPrimaryLocationCode;
    private String startSecondaryLocationCode;
    private LocationType startSecondaryLocationType;
    private String endPrimaryLocationCode;
    private String endSecondaryLocationCode;
    private LocationType endSecondaryLocationType;
    private Date updatedOn;

    public Route(String code, String description, int distance, String rateCode, String startPrimaryLocationCode, String startSecondaryLocationCode, LocationType startSecondaryLocationType, String endPrimaryLocationCode, String endSecondaryLocationCode, LocationType endSecondaryLocationType, Date updatedOn) {
        this.code = code;
        this.description = description;
        this.distance = distance;
        this.rateCode = rateCode;
        this.startPrimaryLocationCode = startPrimaryLocationCode;
        this.startSecondaryLocationCode = startSecondaryLocationCode;
        this.startSecondaryLocationType = startSecondaryLocationType;
        this.endPrimaryLocationCode = endPrimaryLocationCode;
        this.endSecondaryLocationCode = endSecondaryLocationCode;
        this.endSecondaryLocationType = endSecondaryLocationType;
        this.updatedOn = updatedOn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getRateCode() {
        return rateCode;
    }

    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    public String getStartPrimaryLocationCode() {
        return startPrimaryLocationCode;
    }

    public void setStartPrimaryLocationCode(String startPrimaryLocationCode) {
        this.startPrimaryLocationCode = startPrimaryLocationCode;
    }

    public String getStartSecondaryLocationCode() {
        return startSecondaryLocationCode;
    }

    public void setStartSecondaryLocationCode(String startSecondaryLocationCode) {
        this.startSecondaryLocationCode = startSecondaryLocationCode;
    }

    public LocationType getStartSecondaryLocationType() {
        return startSecondaryLocationType;
    }

    public void setStartSecondaryLocationType(LocationType startSecondaryLocationType) {
        this.startSecondaryLocationType = startSecondaryLocationType;
    }

    public String getEndPrimaryLocationCode() {
        return endPrimaryLocationCode;
    }

    public void setEndPrimaryLocationCode(String endPrimaryLocationCode) {
        this.endPrimaryLocationCode = endPrimaryLocationCode;
    }

    public String getEndSecondaryLocationCode() {
        return endSecondaryLocationCode;
    }

    public void setEndSecondaryLocationCode(String endSecondaryLocationCode) {
        this.endSecondaryLocationCode = endSecondaryLocationCode;
    }

    public LocationType getEndSecondaryLocationType() {
        return endSecondaryLocationType;
    }

    public void setEndSecondaryLocationType(LocationType endSecondaryLocationType) {
        this.endSecondaryLocationType = endSecondaryLocationType;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOnCode(Date updatedOnCode) {
        this.updatedOn = updatedOnCode;
    }
}
