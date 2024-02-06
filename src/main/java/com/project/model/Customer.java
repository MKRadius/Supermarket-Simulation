package com.project.model;

import java.util.HashMap;
import javafx.scene.shape.Circle;

public class Customer {
    private static int customerCount = 0;

    private final String id;
    private String name;
    private double budget;
    private HashMap<Product, Integer> cart;
    private boolean isAtDestination = true;
    private boolean isReadyToCheckout = false;
    private int buyTimes = 0;

    private double x;
    private double y;
    private Circle circle;

    public static int getCustomerCount() {
        return customerCount;
    }

    public Customer() {
        this.id = String.format("%06d", ++customerCount);
        this.name = "cus" + this.id;
        this.budget = 100 + (int) (Math.random() * 1000);
        this.cart = new HashMap<Product, Integer>();
    }

    public Customer(String name) {
        this();
        this.name = name;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // customer budget
    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    // customer cart
    public HashMap<Product, Integer> getCart() {
        return this.cart;
    }

    public void addProductToCart(Product product, int quantity) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + quantity);
        } else {
            cart.put(product, quantity);
        }
    }

    public String getCartString() {
        String cartString = "";
        for (Product product : cart.keySet()) {
            cartString += "[" + cart.get(product) + "]" + product.getName();

            // add plus if not last item
            if (cart.keySet().toArray()[cart.size() - 1] != product) {
                cartString += " + ";
            }
        }
        return cartString;
    }


    // destination
    public boolean isAtDestination() {
        return isAtDestination;
    }

    public void setAtDestination(boolean isAtDestination) {
        this.isAtDestination = isAtDestination;
    }

    // ready to checkout
    public boolean isReadyToCheckout() {
        return isReadyToCheckout;
    }

    public void setReadyToCheckout(boolean isReadyToCheckout) {
        this.isReadyToCheckout = isReadyToCheckout;
    }

    // buy times
    public int getBuyTimes() {
        return buyTimes;
    }

    public void setBuyTimes(int buyTimes) {
        this.buyTimes = buyTimes;
    }

    // customer location
    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
    
    public double getX() {
        return x;
    }
    
    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) { 
        this.y = y;
    }
}
