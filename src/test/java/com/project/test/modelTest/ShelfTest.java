package com.project.test.modelTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.project.model.Product;
import com.project.model.Shelf;

import javafx.scene.shape.Rectangle;


public class ShelfTest {
    private Shelf shelf;
    private Product testProduct;

    @BeforeEach
    public void setUp() {
        shelf = new Shelf();
        testProduct = new Product(1, "Test Product", 10.0, 2);
    }

    @Test
    public void testProductOperations() {
        shelf.setProduct(testProduct);
        assertEquals(testProduct, shelf.getProduct());

        shelf.setProductQuantity(5);
        assertEquals(5, shelf.getProductQuantity());
    }

    @Test
    public void testShelfPath() {
        Rectangle testRectangle = new Rectangle(10, 20);
        shelf.setShelfPath(testRectangle);
        assertEquals(testRectangle, shelf.getShelfPath());
    }
}

