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

public class ProductsDao implements CrudDao<Products> {

    @Override
    public void save(Products obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn
        .prepareStatement("INSERT INTO products (productID, productName, productValue) VALUES (?, ?, ?)")) {
            ps.setString(1, obj.getProductID());
            ps.setString(2, obj.getProductName());
            ps.setLong(3, obj.getProductValue());


            ps.executeUpdate();
            } catch (SQLException e) {
            throw new RuntimeException("Error inserting product into database", e);
            } catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file", e);
        }
    }


    @Override
    public void update(Products obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
          PreparedStatement ps = conn
          .prepareStatement("UPDATE products SET productName = ?, productValue = ? WHERE productID = ?")) {
            ps.setString(1, obj.getProductName());
            ps.setLong(2, obj.getProductValue());
            ps.setString(3, obj.getProductID());

          ps.executeUpdate();
          }catch (SQLException e) {
            throw new RuntimeException("Error updating product in database", e);
          }catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file", e);
          }
      }

    @Override
    public void delete(String ID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
          PreparedStatement ps = conn
          .prepareStatement("DELETE FROM products WHERE productID = ?")) {
            ps.setString(1, ID);

            ps.executeUpdate();
          }catch (SQLException e) {
            throw new RuntimeException("Error deleting product from database", e);
          }catch (IOException e) {
            throw new RuntimeException("Can't find application.properties file", e);
          }
    }

    @Override
    public List<Products> findAll() {
        List<Products> products = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products"); // Changed table name
             ResultSet rs = ps.executeQuery()) {

          while (rs.next()) {
            Products product = new Products(); // Use Product object
            product.setProductID(rs.getString("productID")); // Assuming productID exists
            product.setProductName(rs.getString("productName")); // Assuming productName exists
            product.setProductValue(rs.getLong("productValue")); // Assuming productValue exists

            products.add(product);
          }
        } catch (SQLException e) {
          throw new RuntimeException("Can't connect to database");
        } catch (IOException e) {
          throw new RuntimeException("Can't find application.properties file");
        }
        return products;

    }

    public List<Products> findProductsByName(String productName) {
      List<Products> products = new ArrayList<>();

      try (Connection conn = ConnectionFactory.getInstance().getConnection();
           PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products WHERE productName = ?")) {
          ps.setString(1, productName);

          try (ResultSet rs = ps.executeQuery()) {
              while (rs.next()) {
                  Products product = new Products();
                  product.setProductID(rs.getString("productID"));
                  product.setProductName(rs.getString("productName"));
                  product.setProductValue(rs.getLong("productValue"));

                  products.add(product);
              }
          }
      } catch (SQLException e) {
          throw new RuntimeException("Error retrieving product by productName from database", e);
      } catch (IOException e) {
          throw new RuntimeException("Can't find application.properties file", e);
      }
      return products;
  }

  public List<Products> findByIdP(String id) {
    List<Products> products = new ArrayList<>();

    try (Connection conn = ConnectionFactory.getInstance().getConnection();
         PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products WHERE productID = ?")) {
        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Products product = new Products();
                product.setProductID(rs.getString("productID"));
                product.setProductName(rs.getString("productName"));
                product.setProductValue(rs.getLong("productValue"));

                products.add(product);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error retrieving product by productID from the database", e);
    } catch (IOException e) {
        throw new RuntimeException("Can't find application.properties file", e);
    }
    return products;
}

    @Override
    public Products findById(String id) {
      return null;
  }


}
