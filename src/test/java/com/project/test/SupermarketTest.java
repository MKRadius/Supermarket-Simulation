package com.project.test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.project.Supermarket;
import com.project.model.Cashier;
import com.project.model.Customer;
import com.project.model.Shelf;

public class SupermarketTest {

    private Supermarket supermarket;

    @BeforeEach
    public void setUp() {
        supermarket = new Supermarket();
        supermarket.setCustomerCount(50);
        supermarket.initialize();
    }

    @Test
    public void testInitialize() {
        ArrayList<Cashier> cashiers = supermarket.getCashiersList();
        ArrayList<Shelf> shelves = supermarket.getShelvesList();
        ArrayList<Customer> customers = supermarket.getCustomersList();

        assertEquals(4, cashiers.size());
        assertEquals(3, shelves.size());
        assertEquals(50, customers.size());
    }

    @Test
    public void testFindCashierWithLeastLength() {
        supermarket.getCashiersList().get(0).getQueue().add(new Customer());
        supermarket.getCashiersList().get(0).getQueue().add(new Customer());
        supermarket.getCashiersList().get(0).getQueue().add(new Customer());
        supermarket.getCashiersList().get(1).getQueue().add(new Customer());
        supermarket.getCashiersList().get(1).getQueue().add(new Customer());
        supermarket.getCashiersList().get(2).getQueue().add(new Customer());
        supermarket.getCashiersList().get(2).getQueue().add(new Customer());
        supermarket.getCashiersList().get(3).getQueue().add(new Customer());

        Cashier cashierWithLeastLength = supermarket.findCashierWithLeastLength();
        assertEquals(supermarket.getCashiersList().get(3), cashierWithLeastLength);
    }
}
