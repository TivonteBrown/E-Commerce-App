package com.revature.eCommerce.models;

public class Role {

    private String ID;
    private String roleName;

    public Role(){}

    public Role(String ID, String roleName) {
        this.ID = ID;
        this.roleName = roleName;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
