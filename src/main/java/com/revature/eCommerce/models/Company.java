package com.revature.eCommerce.models;

public class Company {
    private String companyID;

    public Company () {}

    public Company(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }


}
