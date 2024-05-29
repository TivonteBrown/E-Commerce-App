package com.revature.eCommerce.dto.request;

public class NewProductsRequest {

    private String productID;
    private String productName;
    private Long productValue;
    private String seller;

    public NewProductsRequest(){

    }

    public NewProductsRequest(String productName, Long productValue){
        this.productName = productName;
        this.productValue = productValue;
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
