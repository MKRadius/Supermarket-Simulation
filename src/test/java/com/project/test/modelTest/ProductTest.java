package com.project.test.modelTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.project.model.Product;

public class ProductTest {
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product(1, "Test Product", 10.0, 5);
    }

    @Test
    public void testInitialValues() {
        assertEquals(1, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals(10.0, product.getPrice());
        assertEquals(5, product.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        product.setQuantity(8);
        assertEquals(8, product.getQuantity());
    }

    @Test
    public void testGetId() {
        assertEquals(1, product.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Test Product", product.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(10.0, product.getPrice());
    }

    @Test
    public void testGetQuantity() {
        assertEquals(5, product.getQuantity());
    }
}
