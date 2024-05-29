package com.revature.eCommerce.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.eCommerce.models.*;
import com.revature.eCommerce.utils.ConnectionFactory;

public class RoleDao implements CrudDao<Role>  {

    @Override
    public void save(Role obj) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Role obj) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM roles");
        ResultSet rs = ps.executeQuery())  {
            while (rs.next()) {
                Role role = new Role();
                role.setID(rs.getString("id"));
                role.setRoleName(rs.getString("roleName"));

                roles.add(role);
            }
        } catch (SQLException e){
            throw new RuntimeException("Can't connect to database");
        }catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file");
        }
        return roles;
    }

    @Override
    public Role findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }


}
