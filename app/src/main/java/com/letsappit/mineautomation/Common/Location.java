package com.letsappit.mineautomation.Common;

import java.util.Date;

/**
 * Created by radhaprasadborkar on 04/01/16.
 */
public class Location {
    public int getId() {
        return this.id;
    }

    private int id;
    private String code,description;
    private Date updatedOn;
    private LocationType locationType;

    public Location(String code, String description, Date datetime,int id) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.updatedOn = datetime;
    }
    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getUpdatedOn() {
        return this.updatedOn;
    }


}
