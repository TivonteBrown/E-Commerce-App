package com.revature.eCommerce.models;

public class Products {

    private String productID;
    private int productValue;
    private String seller;
    private String orderHistory;

    public Products (){}

    public Products(String productID, int productValue, String seller, String orderHistory) {
        this.productID = productID;
        this.seller = seller;
        this.productValue = productValue;
        this.orderHistory = orderHistory;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getProductValue() {
        return productValue;
    }

    public void setProductValue(int productValue) {
        this.productValue = productValue;
    }

    public String getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(String orderHistory) {
        this.orderHistory = orderHistory;
    }


}
