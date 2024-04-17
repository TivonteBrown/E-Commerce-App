package com.revature.eCommerce.dao;

import com.revature.eCommerce.models.*;

public class UserDao {


public User Login(String username, String password) {
    //create router service
    RouterService rSer = RouterService.getConnection();
    try (
        PreparedStatement prepState = rSer.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?;")) {
        prepState.setString(1, username);
        prepState.setString(2, password);
        ResultSet rs = prepState.executeQuery();

        if (rs.next()) {
            // if a user with the given username and password exists in the database, return a User

            User accountL =  new User(rs.getInt("UserID"),
            rs.getString("username"), rs.getString("password"));
            return accountL;

        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    // if no user was found, return null
    return null;
}

}