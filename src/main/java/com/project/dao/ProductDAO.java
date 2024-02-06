package com.project.dao;

import com.project.model.Product;
import com.project.datasource.MariaDBConnection;
import java.sql.*;
import java.util.*;

public class ProductDAO {
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = MariaDBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
            while (resultSet.next()) {
                Product product = new Product( 
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int id) {
        Product product = null;
        try {
            Connection connection = MariaDBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Product getProductByName(String name) {
        Product product = null;
        try {
            Connection connection = MariaDBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
