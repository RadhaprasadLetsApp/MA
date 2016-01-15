package com.letsappit.mineautomation.Machine;

import java.util.Date;

/**
 * Created by Admin on 30-12-2015.
 */
public class Machine {

    String code;
    String regNo;
    String description;
    String cardId;
    String category;
    String loadingContractor;
    Date regDate;

    public Machine(String code, String regNo, String description, String cardId, String categoryCode, String loadingContractor, Date regDate) {
        this.code = code;
        this.regNo = regNo;
        this.description = description;
        this.cardId = cardId;
        this.category = categoryCode;
        this.loadingContractor = loadingContractor;
        this.regDate = regDate;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegNo() {
        return this.regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCardId() {
        return this.cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String categoryCode) {
        this.category = categoryCode;
    }

    public String getContractor() {
        return this.loadingContractor;
    }

    public void setContractor(String loadingContractor) {
        this.loadingContractor = loadingContractor;
    }

    public Date getRegDate() {
        return this.regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
