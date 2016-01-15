package com.letsappit.mineautomation.Activity;

import java.util.Date;

/**
 * Created by Admin on 30-12-2015.
 */
public class Activity {
    private String code;
    private String description;
    private String category;
    private Date updatedOn;

    public Activity(String code, String description, String category, Date updatedOn) {
        this.code = code;
        this.description = description;
        this.category = category;
        this.updatedOn = updatedOn;
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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
