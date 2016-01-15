package com.letsappit.mineautomation.MobileTagReader;

import com.letsappit.mineautomation.Common.TagReader;

import java.util.Date;

/**
 * Created by Admin on 01-01-2016.
 */
public class MobileTagReader extends TagReader {

    private String iMEINumber;
    private String primaryLocationCode;
    private String applicationToken;

    public MobileTagReader(String code, String description, Date updatedOn, String iMEINumber, String primaryLocationCode, String applicationToken) {
        super(code, description,updatedOn);
        this.iMEINumber = iMEINumber;
        this.primaryLocationCode = primaryLocationCode;
        this.applicationToken = applicationToken;
    }

    public String getiMEINumber() {
        return this.iMEINumber;
    }

    public void setiMEINumber(String iMEINumber) {
        this.iMEINumber = iMEINumber;
    }

    public String getApplicationToken() {
        return this.applicationToken;
    }

    public void setApplicationToken(String applicationToken) {
        this.applicationToken = applicationToken;
    }

    public String getPrimaryLocationCode() {
        return this.primaryLocationCode;
    }

    public void setPrimaryLocationCode(String primaryLocationCode) {
        this.primaryLocationCode = primaryLocationCode;
    }
}
