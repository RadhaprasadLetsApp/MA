package com.letsappit.mineautomation.Common;

import com.letsappit.mineautomation.Category.Category;

import java.sql.Date;

/**
 * Created by Admin on 29-12-2015.
 */
public class Contractor extends Person{

    public Contractor() {}

    public Contractor(ContractorType contractorType) {
        this.contractorType = contractorType;
    }

    public Contractor(String code, String firstName, String lastName, String address, int mobileNumber, String emailID, Date updatedOn, ContractorType contractorType, Category category) {
        super(code, firstName, lastName, address, mobileNumber, emailID, updatedOn);
        this.contractorType = contractorType;
        this.category = category;
    }

    private ContractorType contractorType;

    private Category category;

    public ContractorType getContractorType() {
        return contractorType;
    }

    private void setContractorType(ContractorType contractorType) {
        this.contractorType = contractorType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
