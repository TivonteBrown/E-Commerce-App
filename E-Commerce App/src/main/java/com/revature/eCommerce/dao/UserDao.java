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

public class UserDao implements CrudDao<User> {

    @Override
    public void save(User obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = conn
                .prepareStatement("INSERT INTO accounts (userID, name, password, email, roleID) VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, obj.getUserID());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getRoleID());

            ps.executeUpdate();
            } catch (SQLException e) {
            throw new RuntimeException("Error inserting user into database", e);
            } catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file", e);
        }
   }

    @Override
    public void update(User obj) {
        // TODO Auto-generated method stub

    }


    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM accounts");
        ResultSet rs = ps.executeQuery())  {
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("userID"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRoleID(rs.getString("roleID"));

                users.add(user);
            }
        } catch (SQLException e){
            throw new RuntimeException("Can't connect to database");
        }catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file");
        }
        return users;

    }


    public List<User> findAllWithRole() {
        List<User> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM accounts_with_roles");
        ResultSet rs = ps.executeQuery())  {
            while (rs.next()) {
                User user = new User();
                Role role = new Role();
                user.setUserID(rs.getString("userID"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRoleID(rs.getString("roleID"));
                role.setID(rs.getString("id"));
                role.setRoleName(rs.getString("roleName"));
                user.setRole(role);

                users.add(user);
            }
        } catch (SQLException e){
            throw new RuntimeException("Can't connect to database");
        }catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file");
        }
        return users;


    }

    @Override
    public User findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}