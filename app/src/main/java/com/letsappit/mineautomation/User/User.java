package com.letsappit.mineautomation.User;

import com.letsappit.mineautomation.Common.Person;

import java.util.Date;

/**
 * Created by radhaprasadborkar on 05/01/16.
 */
public class User extends Person{

    private String loginName;
    private String loginPassword;
    private UserProfile userProfile;
    private String cardID;
    private String primaryLocationCode;

    public User() {
    }

    public User(String code, String firstName, String lastName, String address, int mobileNumber, String emailID, Date updatedOn, String loginName, String loginPassword, UserProfile userProfile, String cardID, String primaryLocationCode) {
        super(code, firstName, lastName, address, mobileNumber, emailID, updatedOn);
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.userProfile = userProfile;
        this.cardID = cardID;
        this.primaryLocationCode = primaryLocationCode;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return this.loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public UserProfile getUserProfile() {
        return this.userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getCardID() {
        return this.cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getPrimaryLocationCode() {
        return this.primaryLocationCode;
    }

    public void setPrimaryLocationCode(String primaryLocationCode) {
        this.primaryLocationCode = primaryLocationCode;
    }
}
