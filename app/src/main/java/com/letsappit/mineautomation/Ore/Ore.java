package com.letsappit.mineautomation.Ore;

import com.letsappit.mineautomation.Common.Item;

import java.util.Date;

/**
 * Created by Admin on 30-12-2015.
 */
public class Ore extends Item{

    private OreType oreType;
    private String grade;
    private String categoryCode;

    public Ore(String code, String description, Date updatedDatetime, OreType oreType, String categoryCode,String grade) {
        super(code, description, updatedDatetime);
        this.oreType = oreType;
        this.categoryCode = categoryCode;
        this.grade=grade;
    }

    public OreType getOreType() {
        return this.oreType;
    }

    public void setOreType(OreType oreType) {
        this.oreType = oreType;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
