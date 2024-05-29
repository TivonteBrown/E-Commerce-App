package com.revature.eCommerce.dto.responses;

import com.revature.eCommerce.models.Role;
import com.revature.eCommerce.models.User;

public class Principal {
    private String userID;
    private String name;
    private Role role;

    public Principal (){

    }

    public Principal (User user){
        this.userID = user.getUserID();
        this.name = user.getName();
        this.role = user.getRole();

    }


    public Principal(String userID, String name, Role role) {
        this.userID = userID;
        this.name = name;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



}
