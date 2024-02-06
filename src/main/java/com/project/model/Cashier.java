package com.project.model;

import javafx.scene.shape.Rectangle;

import java.util.concurrent.ConcurrentLinkedQueue;


public class Cashier {
    private static int cashierCount = 0;

    private final String id;
    private String name;

    private ConcurrentLinkedQueue<Customer> queue = new ConcurrentLinkedQueue<Customer>();

    private Rectangle rectangle;

    public Cashier() {
        this.id = String.format("%06d", ++cashierCount);
        this.name = "cas" + this.id;
    }

    public Cashier(String name) {
        this(); // call the default constructor
        this.name = name;
    }

    // getters and setters
    public static int getCashierCount() {
        return cashierCount;
    }

    public String getId() {
        return id;
    }

    // cashier name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // get queue length
    public int getQueueLength() {
        return queue.size();
    }
    
    public ConcurrentLinkedQueue<Customer> getQueue() {
        return queue;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void checkoutCustomer(Customer customer) {
        this.queue.remove(customer);
    }
}
