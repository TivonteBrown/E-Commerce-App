package com.revature.eCommerce.models;

public class User {
    private String userID;
    private String name;
    private String password;
    private String email;
    private String purchaseHistory;
    private String roleID;
    private int money;


    public User() {
    }

        public User(String userID, String name, String password, String email, String purchaseHistory, String roleID,
            int money) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.purchaseHistory = purchaseHistory;
        this.roleID = roleID;
        this.money = money;
    }

    public String getUserID() {
        return userID;
    }


    public void setUserID(String userID) {
        this.userID = userID;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPurchaseHistory() {
        return purchaseHistory;
    }


    public void setPurchaseHistory(String purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }


    public String getRoleID() {
        return roleID;
    }


    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }


    public int getMoney() {
        return money;
    }


    public void setMoney(int money) {
        this.money = money;
    }

}
