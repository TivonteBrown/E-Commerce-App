package com.revature.eCommerce.services;

import com.revature.eCommerce.dao.RoleDao;

public class RoleService {
    private final RoleDao roleDao;

    public RoleService (RoleDao roleDao){
        this.roleDao = roleDao;
    }

    public String getRoleIDByName(String name){

        return roleDao.findAll().stream()
        .filter(r -> r.getRoleName().equals(name))
        .findFirst().get().getID();
    }

}
