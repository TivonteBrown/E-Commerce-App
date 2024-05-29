package com.revature.eCommerce.models;

import java.sql.Timestamp;
import java.util.UUID;

public class OrderHistory {
    private String orderID;
    private String productID;
    private String productName;
    private long price;
    private int quantity;
    private long totalOrderPrice;
    private String cartID;
    private String buyer;
    private Timestamp timeOfPurchase;
    private String pending;

    public OrderHistory() {
    }

    public OrderHistory(Cart cart){
        this.productID = cart.getProductID();
        this.productName = cart.getProductName();
        this.price = cart.getitemPrice();
        this.quantity = cart.getQuantity();
        this.cartID = cart.getCartID();
        this.buyer = cart.getBuyer();

        //orderID, totalOrderPrice, timeOfPurchase

    }

    public OrderHistory(String productID, String productName, long price, int quantity, long totalPrice,
            String cartID, String buyer, Timestamp timeOfPurchase) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.totalOrderPrice = totalOrderPrice;
        this.cartID = cartID;
        this.buyer = buyer;
        this.timeOfPurchase = timeOfPurchase;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(long totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Timestamp getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(Timestamp timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }



}