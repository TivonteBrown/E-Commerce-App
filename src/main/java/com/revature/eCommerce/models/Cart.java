package com.revature.eCommerce.models;

public class Cart {
    private String customer;
    private String product;
    private String companyName;
    private int price;

    public Cart(){}

    public Cart(String customer, String product, String companyName, int price) {
        this.customer = customer;
        this.product = product;
        this.companyName = companyName;
        this.price = price;
    }

    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }




}
