package com.letsappit.mineautomation.Zone;


import com.letsappit.mineautomation.Common.Location;

import java.util.Date;

/**
 * Created by radhaprasadborkar on 02/01/16.
 */
public class Zone extends Location {

    String primaryLocationCode;

    public Zone(String code, String description, Date datetime, int id, String primaryLocationCode) {
        super(code, description, datetime, id);
        this.primaryLocationCode = primaryLocationCode;
    }

    public String getPrimaryLocationCode() {
        return this.primaryLocationCode;
    }

    public void setPrimaryLocationCode(String primaryLocationCode) {
        this.primaryLocationCode = primaryLocationCode;
    }
}
