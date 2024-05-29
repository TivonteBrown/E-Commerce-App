package com.revature.eCommerce.models;

import com.revature.eCommerce.dto.request.NewProductsDelete;
import com.revature.eCommerce.dto.request.NewProductsRequest;
import com.revature.eCommerce.dto.request.NewProductsUpdate;

import java.util.UUID;

public class Products {

    private String productID;
    private String productName;
    private Long productValue;
    private String seller;

    public Products (){}

    public Products(NewProductsRequest req){
        this.productID = UUID.randomUUID().toString();
        this.productName = req.getProductName();
        this.productValue = req.getProductValue();
    }

    public Products(NewProductsDelete req){
        this.productID = req.getProductID();
        this.productName = req.getProductName();
        this.productValue = req.getProductValue();
    }

    public Products(NewProductsUpdate req){
        this.productID = req.getProductID();
        this.productName = req.getProductName();
        this.productValue = req.getProductValue();
    }

    public Products(String productName, Long productValue) {
        this.productID = UUID.randomUUID().toString();
        this.productName = productName;
        this.productValue = productValue;
    }

    //TODO add a constructor including seller

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

    public Long getProductValue() {
        return productValue;
    }

    public void setProductValue(Long productValue) {
        this.productValue = productValue;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }



}