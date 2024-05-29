package com.revature.eCommerce.dto.responses;


import java.sql.Timestamp;

public class OrderHistoryResponse {
    private String orderID;
    private String productID;
    private String productName;
    private long price;
    private int quantity;
    private long totalPrice;
    private String cartID;
    private String buyer;
    private Timestamp timeOfPurchase;

    public OrderHistoryResponse() {
    }


}
