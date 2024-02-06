package com.project.test.dao;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import java.util.List;

import com.project.model.Product;
import com.project.dao.ProductDAO;

public class ProductDAOTest {

    private ProductDAO productDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        productDAO = new ProductDAO();
    }

    @Test
    public void testGetAllProducts() throws SQLException {
        List<Product> actualProducts = productDAO.getAllProducts();

        assertEquals(6, actualProducts.size());
        assertEquals(1, actualProducts.get(0).getId());
        assertEquals("milk", actualProducts.get(0).getName());
        assertEquals(2.5, actualProducts.get(0).getPrice());
        assertEquals(1000, actualProducts.get(0).getQuantity());

        assertEquals(2, actualProducts.get(1).getId());
        assertEquals("bread", actualProducts.get(1).getName());
        assertEquals(1.75, actualProducts.get(1).getPrice());
        assertEquals(1000, actualProducts.get(1).getQuantity());

        assertEquals(3, actualProducts.get(2).getId());
        assertEquals("egg", actualProducts.get(2).getName());
        assertEquals(3.2, actualProducts.get(2).getPrice());
        assertEquals(2000, actualProducts.get(2).getQuantity());

        assertEquals(4, actualProducts.get(3).getId());
        assertEquals("cheese", actualProducts.get(3).getName());
        assertEquals(4.5, actualProducts.get(3).getPrice());
        assertEquals(500, actualProducts.get(3).getQuantity());

        assertEquals(5, actualProducts.get(4).getId());
        assertEquals("apple", actualProducts.get(4).getName());
        assertEquals(2.0, actualProducts.get(4).getPrice());
        assertEquals(400, actualProducts.get(4).getQuantity());

        assertEquals(6, actualProducts.get(5).getId());
        assertEquals("banana", actualProducts.get(5).getName());
        assertEquals(1.8, actualProducts.get(5).getPrice());
        assertEquals(300, actualProducts.get(5).getQuantity());
    }

    @Test
    public void testGetProductById() throws SQLException {
        Product actualProduct = productDAO.getProductById(1);
        assertEquals(1, actualProduct.getId());
        assertEquals("milk", actualProduct.getName());
        assertEquals(2.5, actualProduct.getPrice());
        assertEquals(1000, actualProduct.getQuantity());
    }

    @Test
    public void testGetProductByName() throws SQLException {
        Product actualProduct = productDAO.getProductById(1);
        assertEquals(1, actualProduct.getId());
        assertEquals("milk", actualProduct.getName());
        assertEquals(2.5, actualProduct.getPrice());
        assertEquals(1000, actualProduct.getQuantity());
    }
}

