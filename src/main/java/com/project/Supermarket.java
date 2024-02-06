package com.project;

import java.util.ArrayList;

import com.project.model.*;
import com.project.dao.*;

public class Supermarket {
    private int customerCount;

    private ArrayList<Customer> customersList;
    private ArrayList<Cashier> cashiersList;
    private ArrayList<Shelf> shelvesList;
    private ProductDAO productDAO;

    public Supermarket() {
        this.customersList = new ArrayList<Customer>();
        this.cashiersList = new ArrayList<Cashier>();
        this.shelvesList = new ArrayList<Shelf>();
        this.productDAO = new ProductDAO();
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public ArrayList<Cashier> getCashiersList() {
        return cashiersList;
    }

    public ArrayList<Shelf> getShelvesList() {
        return shelvesList;
    }
    
    public ArrayList<Customer> getCustomersList() {
        return customersList;
    }

    // findcashierwithleastlength
    public Cashier findCashierWithLeastLength() {
        Cashier cashierWithLeastLength = cashiersList.get(0);
        for (Cashier cashier : cashiersList) {
            if (cashier.getQueueLength() < cashierWithLeastLength.getQueueLength()) {
                cashierWithLeastLength = cashier;
            }
        }
        return cashierWithLeastLength;
    }

    public void initialize() {
        for (int i = 0; i < 4; i++) {
            Cashier cashier = new Cashier();
            cashiersList.add(cashier);
        }

        for (int i = 0; i < 3; i++) {
            Shelf shelf = new Shelf();
            shelf.setProduct(productDAO.getProductById(i + 1));
            shelvesList.add(shelf);
        }

        for (int i = 0; i < this.getCustomerCount(); i++) {
            Customer customer = new Customer();
            customersList.add(customer);
        }
    }
}
