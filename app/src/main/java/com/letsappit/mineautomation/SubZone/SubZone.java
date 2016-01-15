package com.letsappit.mineautomation.SubZone;

import com.letsappit.mineautomation.Common.Location;

import java.util.Date;

/**
 * Created by Admin on 29-12-2015.
 */
public class SubZone extends Location {

    private String zoneCode;

    public SubZone(String code, String description, Date datetime, int id, String zoneCode) {
        super(code, description, datetime, id);
        this.zoneCode = zoneCode;
    }

    public String getZoneCode() {
        return this.zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }
}
