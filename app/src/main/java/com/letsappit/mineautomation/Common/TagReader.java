package com.letsappit.mineautomation.Common;

import java.util.Date;

/**
 * Created by Admin on 02-01-2016.
 */
public class TagReader {
    String code;
    String description;
    Date updatedOn;

    public TagReader(String code, String description,Date updatedOn) {
        this.code = code;
        this.description = description;
        this.updatedOn=updatedOn;
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

    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
