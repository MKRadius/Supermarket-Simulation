package com.project.test.modelTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.project.model.Customer;
import com.project.model.Product;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.HashMap;

public class CustomerTest {
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer("John");
    }

    @Test
    public void testInitialValues() {
        assertEquals("John", customer.getName());
        assertEquals(0, customer.getCart().size());
        assertTrue(customer.isAtDestination());
        assertFalse(customer.isReadyToCheckout());
        assertEquals(0, customer.getBuyTimes());
        assertNotNull(customer.getId());
    }

    @Test
    public void testAddProductToCart() {
        Product product = new Product(1, "Product A", 10.0, 2);
        customer.addProductToCart(product, 2);
        HashMap<Product, Integer> cart = customer.getCart();
        assertTrue(cart.containsKey(product));
        assertEquals(2, cart.get(product));
    }

    @Test
    public void testCustomerPosition() {
        customer.setAtDestination(false);
        customer.setReadyToCheckout(true);
        customer.setAtDestination(true);
        customer.setReadyToCheckout(false);
        assertTrue(customer.isAtDestination());
        assertFalse(customer.isReadyToCheckout());
    }

    @Test
    public void testBuyTimes() {
        customer.setBuyTimes(1);
        assertEquals(1, customer.getBuyTimes());
    }

    @Test
    public void testCustomerCiscleOnStage() {
        Circle circle = new Circle(10);
        circle.setFill(Color.RED);

        customer.setX(100);
        customer.setY(360);
        customer.setCircle(circle);

        circle.setLayoutX(customer.getX());
        circle.setLayoutY(customer.getY());

        assertEquals(100, customer.getX());
        assertEquals(360, customer.getY());
        assertEquals(circle, customer.getCircle());
    }
}
