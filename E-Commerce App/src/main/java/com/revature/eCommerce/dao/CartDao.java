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

public class CartDao implements CrudDao<Cart> {

    @Override
    public void save(Cart obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO cart (cartID, buyer, productID, productName, price, quantities) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, obj.getCartID());
            ps.setString(2, obj.getBuyer());
            ps.setString(3, obj.getProductID());
            ps.setString(4, obj.getProductName());
            ps.setLong(5, obj.getPrice());
            ps.setInt(6, obj.getQuantity());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting cart into database", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file", e);
        }
    }

    @Override
    public void update(Cart obj) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String ID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM cart WHERE cartid = ?")) {
            ps.setString(1, ID);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting cart from database", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file", e);
        }
    }


    public void deleteAll(String ID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM cart WHERE cartid = ?")) {
            ps.setString(1, ID);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting cart from database", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file", e);
        }
    }

    public void deleteItem(String cartID, String productName) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM cart WHERE cartID = ? AND productName = ?")) {
            ps.setString(1, cartID);
            ps.setString(2, productName);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting cart from database", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file", e);
        }
    }

    public List<Cart> findByCartId(String cartId) {
    List<Cart> carts = new ArrayList<>();
    try (Connection conn = ConnectionFactory.getInstance().getConnection();
         PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart WHERE cartID = ?");
    ) {
        ps.setString(1, cartId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartID(rs.getString("cartID"));
                cart.setBuyer(rs.getString("buyer"));
                cart.setProductID(rs.getString("productID"));
                cart.setProductName(rs.getString("productName"));
                cart.setPrice(rs.getLong("price"));
                cart.setQuantity(rs.getInt("quantities"));

                carts.add(cart);
            }
        }
            } catch (SQLException e) {
                throw new RuntimeException("Error retrieving items from cart: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException("Can't find application.properties file");
            }
            return carts;
        }


    @Override
    public List<Cart> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cart findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }


}
