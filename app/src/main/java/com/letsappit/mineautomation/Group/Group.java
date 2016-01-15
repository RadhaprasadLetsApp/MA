package com.letsappit.mineautomation.Group;

import com.letsappit.mineautomation.PrimaryLocation.PrimaryLocation;

import java.util.List;

/**
 * Created by Admin on 30-12-2015.
 */
public class Group {

    private int groupID;
    private List<PrimaryLocation> primaryLocations_sunday;
    private List<PrimaryLocation> primaryLocations_monday;
    private List<PrimaryLocation> primaryLocations_tuesday;
    private List<PrimaryLocation> primaryLocations_wednesday;
    private List<PrimaryLocation> primaryLocations_thursday;
    private List<PrimaryLocation> primaryLocations_friday;
    private List<PrimaryLocation> primaryLocations_saturday;

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public List<PrimaryLocation> getPrimaryLocations_sunday() {
        return primaryLocations_sunday;
    }

    public void setPrimaryLocations_sunday(List<PrimaryLocation> primaryLocations_sunday) {
        this.primaryLocations_sunday = primaryLocations_sunday;
    }

    public List<PrimaryLocation> getPrimaryLocations_monday() {
        return primaryLocations_monday;
    }

    public void setPrimaryLocations_monday(List<PrimaryLocation> primaryLocations_monday) {
        this.primaryLocations_monday = primaryLocations_monday;
    }

    public List<PrimaryLocation> getPrimaryLocations_tuesday() {
        return primaryLocations_tuesday;
    }

    public void setPrimaryLocations_tuesday(List<PrimaryLocation> primaryLocations_tuesday) {
        this.primaryLocations_tuesday = primaryLocations_tuesday;
    }

    public List<PrimaryLocation> getPrimaryLocations_wednesday() {
        return primaryLocations_wednesday;
    }

    public void setPrimaryLocations_wednesday(List<PrimaryLocation> primaryLocations_wednesday) {
        this.primaryLocations_wednesday = primaryLocations_wednesday;
    }

    public List<PrimaryLocation> getPrimaryLocations_thursday() {
        return primaryLocations_thursday;
    }

    public void setPrimaryLocations_thursday(List<PrimaryLocation> primaryLocations_thursday) {
        this.primaryLocations_thursday = primaryLocations_thursday;
    }

    public List<PrimaryLocation> getPrimaryLocations_friday() {
        return primaryLocations_friday;
    }

    public void setPrimaryLocations_friday(List<PrimaryLocation> primaryLocations_friday) {
        this.primaryLocations_friday = primaryLocations_friday;
    }

    public List<PrimaryLocation> getPrimaryLocations_saturday() {
        return primaryLocations_saturday;
    }

    public void setPrimaryLocations_saturday(List<PrimaryLocation> primaryLocations_saturday) {
        this.primaryLocations_saturday = primaryLocations_saturday;
    }
}
