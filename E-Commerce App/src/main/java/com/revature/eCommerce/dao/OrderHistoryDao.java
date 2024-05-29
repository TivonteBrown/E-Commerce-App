package com.revature.eCommerce.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.revature.eCommerce.models.Cart;
import com.revature.eCommerce.models.OrderHistory;
import com.revature.eCommerce.utils.ConnectionFactory;

public class OrderHistoryDao implements CrudDao<OrderHistory> {

    @Override
    public void save(OrderHistory obj) {

       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       timestamp.setNanos(0);

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ordersHistory (orderID, productID, productName, price, quantity, totalPrice, userID, buyer, timeOfPurchase, Pending) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, obj.getOrderID());
            ps.setString(2, obj.getProductID());
            ps.setString(3, obj.getProductName());
            ps.setLong(4, obj.getPrice());
            ps.setInt(5, obj.getQuantity());
            ps.setLong(6, obj.getTotalOrderPrice());
            ps.setString(7, obj.getCartID());
            ps.setString(8, obj.getBuyer());
            ps.setTimestamp(9, timestamp);
            ps.setString(10, "1-87fg897sfdg900dfg");

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting order into ordersHistory table", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file", e);
        }
    }


    @Override
    public void update(OrderHistory obj) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<OrderHistory> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<OrderHistory> createOrderHistoryByID(OrderHistory obj) {
        List<OrderHistory> OrderHistoryCarts = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart WHERE cartID = ?");
        ) {
            ps.setString(1, obj.getCartID());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderHistory OrderHistory = new OrderHistory();
                    OrderHistory.setCartID(rs.getString("cartID"));
                    OrderHistory.setBuyer(rs.getString("buyer"));
                    OrderHistory.setProductID(rs.getString("productID"));
                    OrderHistory.setProductName(rs.getString("productName"));
                    OrderHistory.setPrice(rs.getLong("price"));
                    OrderHistory.setQuantity(rs.getInt("quantities"));
                    OrderHistory.setTotalOrderPrice(obj.getTotalOrderPrice());

                    OrderHistoryCarts.add(OrderHistory);
                }
            }
                } catch (SQLException e) {
                    throw new RuntimeException("Error retrieving items from cart: " + e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException("Can't find application.properties file");
                }
                return OrderHistoryCarts;
            }

    public List<OrderHistory> getOrderHistory() {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM ordersHistory");
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setOrderID(rs.getString("orderID"));
                orderHistory.setProductID(rs.getString("productID"));
                orderHistory.setProductName(rs.getString("productName"));
                orderHistory.setPrice(rs.getLong("price"));
                orderHistory.setQuantity(rs.getInt("quantity"));
                orderHistory.setTotalOrderPrice(rs.getLong("totalPrice"));
                orderHistory.setCartID(rs.getString("userID"));
                orderHistory.setBuyer(rs.getString("buyer"));
                orderHistory.setTimeOfPurchase(rs.getTimestamp("timeOfPurchase"));
                orderHistory.setPending(rs.getString("Pending"));

                orderHistoryList.add(orderHistory);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving order history from database", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file", e);
        }
        return orderHistoryList;
    }

    public List<OrderHistory> getOrderHistoryByUserID(String userID) {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM ordersHistory WHERE userID = ?");
            ) {
            ps.setString(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderHistory orderHistory = new OrderHistory();
                    orderHistory.setOrderID(rs.getString("orderID"));
                    orderHistory.setProductID(rs.getString("productID"));
                    orderHistory.setProductName(rs.getString("productName"));
                    orderHistory.setPrice(rs.getLong("price"));
                    orderHistory.setQuantity(rs.getInt("quantity"));
                    orderHistory.setTotalOrderPrice(rs.getLong("totalPrice"));
                    orderHistory.setCartID(rs.getString("userID"));
                    orderHistory.setBuyer(rs.getString("buyer"));
                    orderHistory.setTimeOfPurchase(rs.getTimestamp("timeOfPurchase"));
                    orderHistory.setPending(rs.getString("Pending"));

                    orderHistoryList.add(orderHistory);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving order history from database", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file", e);
        }
        return orderHistoryList;
    }

    @Override
    public OrderHistory findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }



}
