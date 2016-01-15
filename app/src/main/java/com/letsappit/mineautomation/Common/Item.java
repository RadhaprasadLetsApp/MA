package com.letsappit.mineautomation.Common;

import java.util.Date;

/**
 * Created by Admin on 30-12-2015.
 */
public class Item {

    private String code;
    private String description;
    private Date updatedDatetime;

    public Item() {    }

    public Item(String code, String description, Date updatedDatetime) {
        this.code = code;
        this.description = description;
        this.updatedDatetime = updatedDatetime;
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

    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
}
