package com.revature.eCommerce.models;

public class Wallet {
    
    private int currency;
    private int currencyType;

    public Wallet(){}

    public Wallet(int currency, int currencyType) {
        this.currency = currency;
        this.currencyType = currencyType;
    }
    public int getCurrency() {
        return currency;
    }
    public void setCurrency(int currency) {
        this.currency = currency;
    }
    public int getCurrencyType() {
        return currencyType;
    }
    public void setCurrencyType(int currencyType) {
        this.currencyType = currencyType;
    }

}