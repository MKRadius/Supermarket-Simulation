package com.project.test.modelTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.project.model.Customer;
import com.project.model.Cashier;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CashierTest {
    private Cashier cashier;

    @BeforeEach
    public void setUp() {
        cashier = new Cashier("Cashier 1");
        System.out.println(cashier.getId());
    }

    @AfterEach
    public void tearDown() {
        cashier = null;
    }

    @Test
    public void testInitialValues() {
        assertEquals("Cashier 1", cashier.getName());
        assertEquals(0, cashier.getQueueLength());
        assertNotNull(cashier.getId());
        assertNull(cashier.getRectangle()); // Assuming the rectangle should be null initially
    }

    @Test
    public void testQueueOperations() {
        Customer customer1 = new Customer("John");
        Customer customer2 = new Customer("Alice");

        cashier.getQueue().add(customer1);
        cashier.getQueue().add(customer2);

        assertEquals(2, cashier.getQueueLength());

        cashier.checkoutCustomer(customer1);
        assertEquals(1, cashier.getQueueLength());
        assertFalse(cashier.getQueue().contains(customer1));
    }

    @Test
    public void testCashierRectangle() {
        assertNull(cashier.getRectangle());
        cashier.setRectangle(null);
        assertNull(cashier.getRectangle());
    }

    @Test
    public void testCashierQueue() {
        ConcurrentLinkedQueue<Customer> queue = cashier.getQueue();
        assertEquals(0, queue.size());
        queue.add(new Customer("John"));
        assertEquals(1, queue.size());
    }

    @Test
    public void testCashierName() {
        assertEquals("Cashier 1", cashier.getName());
        cashier.setName("Cashier 2");
        assertEquals("Cashier 2", cashier.getName());
    }

    @Test
    public void testCashierId() {
        assertNotNull(cashier.getId());
    }



}

