package com.project.model;

import javafx.scene.shape.Rectangle;

public class Shelf {
    private static int shelfCount = 0;

    private final String id;
    private Product product;

    private Rectangle rectangle;

    public Shelf() {
        this.id = String.format("%06d", ++shelfCount);
    }

    // getters and setters
    public static int getShelfCount() {
        return shelfCount;
    }

    public String getId() {
        return id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getProductQuantity() {
        return this.product.getQuantity();
    }

    public void setProductQuantity(int quantity) {
        this.product.setQuantity(quantity);
    }

    // shelf path (in rectangle shape)
    public void setShelfPath(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getShelfPath() {
        return rectangle;
    }

    public String toString() {
        return "Shelf " + this.getId();
    }
}
