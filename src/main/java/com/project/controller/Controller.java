package com.project.controller;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.project.Supermarket;
import com.project.model.Customer;
import com.project.model.Cashier;
import com.project.model.Shelf;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller {
    private Supermarket supermarket;
    private int timeInterval = 200;
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @FXML
    private Pane mainPane;

    @FXML
    private TextField customerCountInput;

    @FXML
    private Slider speedSlider;

    @FXML
    private Button startSimulationButton;

    @FXML
    private Rectangle cashierQ1;

    @FXML
    private Rectangle cashierQ2;

    @FXML
    private Rectangle cashierQ3;

    @FXML
    private Rectangle cashierQ4;

    @FXML
    private Rectangle path1;

    @FXML
    private Rectangle path2;

    @FXML
    private Rectangle path3;

    public void createCustomerFigure(Customer customer) {
        Platform.runLater(() -> {
            Circle circle = new Circle(10);
            circle.setFill(Color.RED);

            customer.setX(100);
            customer.setY(360);
            customer.setCircle(circle);

            circle.setLayoutX(customer.getX());
            circle.setLayoutY(customer.getY());

            // System.out.println("Added cirlce: " + circle);
            mainPane.getChildren().add(circle);
        });
    }

    public void updateCustomerFigure(Customer customer, Rectangle rectangle) {
        Platform.runLater(() -> {
            Circle circle = customer.getCircle();

            double circleStartX = circle.getLayoutX(); // Get the current X position of the circle
            double circlestartY = circle.getLayoutY(); // Get the current Y position of the circle

            double randomTargetX = (Math.random() * rectangle.getWidth()) + (rectangle.getLayoutX());   // Get a random X position within the rectangle
            double randomTargetY = (Math.random() * rectangle.getHeight()) + (rectangle.getLayoutY());  // Get a random Y position within the rectangle

            Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(circle.layoutXProperty(), circleStartX), new KeyValue(circle.layoutYProperty(), circlestartY)),
                new KeyFrame(Duration.millis(500), new KeyValue(circle.layoutXProperty(), randomTargetX), new KeyValue(circle.layoutYProperty(), randomTargetY))
            );
            timeline.play();
        });
    }

    public void removeCustomerFigure(Customer customer) {
        Platform.runLater(() -> mainPane.getChildren().remove(customer.getCircle()));
    }
    
    public void initialize() {
        System.out.println("Supermarket simulation");
    }

    @FXML
    void onStartSimulation(MouseEvent event) {
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int intValue = newValue.intValue();
            if (intValue != 0) {
                timeInterval = (int) (10000 / intValue);
            }
        });

        supermarket = new Supermarket();
        supermarket.setCustomerCount(Integer.parseInt(customerCountInput.getText()));
        supermarket.initialize();

        Rectangle[] paths = { path1, path2, path3 };
        Rectangle[] cashierQueues = { cashierQ1, cashierQ2, cashierQ3, cashierQ4 };

        for (int i = 0; i < supermarket.getShelvesList().size(); i++) {
            supermarket.getShelvesList().get(i).setShelfPath(paths[i]);
        }

        for (int i = 0; i < cashierQueues.length; i++) {
            supermarket.getCashiersList().get(i).setRectangle(cashierQueues[i]);
        }

        customerCountInput.setDisable(true);
        speedSlider.setDisable(true);
        startSimulationButton.setDisable(true);

        AtomicInteger customerIndex = new AtomicInteger(0);
        executorService.scheduleAtFixedRate(() -> {
            if (customerIndex.get() < supermarket.getCustomersList().size()) {
                Customer customer = supermarket.getCustomersList().get(customerIndex.getAndIncrement());
                createCustomerFigure(customer);
                simulateCustomerActivities(customer);
            }
        }, 0, timeInterval, TimeUnit.MILLISECONDS);

        for (Cashier cashier : supermarket.getCashiersList()) {
            simulateCashierActivities(cashier);
        }

        
    }

    private void simulateCustomerActivities(Customer customer) {
        Thread customerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (!customer.isReadyToCheckout() && customer.getBuyTimes() < 3) {
                    System.out.println(customer.getName() + " is shopping");
                    Shelf randomShelf = getRandomShelf(supermarket.getShelvesList());
                    updateCustomerFigure(customer, randomShelf.getShelfPath());

                    int randomQuantity = (int) (Math.random() * 10 + 1);

                    // check quantity of product in shelf
                    if (randomQuantity > randomShelf.getProductQuantity()) {
                        customer.setBuyTimes(customer.getBuyTimes() + 1);
                        continue;
                    }

                    customer.addProductToCart(randomShelf.getProduct(), randomQuantity);
                    supermarket.getShelvesList().get(supermarket.getShelvesList().indexOf(randomShelf)).setProductQuantity(randomShelf.getProductQuantity() - randomQuantity);

                    System.out.println(customer.getName() + " added " + randomQuantity + " " + randomShelf.getProduct().getName() + " to cart");
                    // randomShelf.removeProductWithQuantityFromShelf(randomShelf.getProduct(), randomQuantity);

                    customer.setBuyTimes(customer.getBuyTimes() + 1);
                    if (customer.getBuyTimes() == 3) {
                        customer.setReadyToCheckout(true);
                    }
                }

                if (customer.isReadyToCheckout()) {
                    System.out.println(customer.getName() + " is ready to checkout");
                    Cashier leastLengthCashier = supermarket.findCashierWithLeastLength();
                    leastLengthCashier.getQueue().add(customer);
                    customer.setAtDestination(false);
                    updateCustomerFigure(customer, leastLengthCashier.getRectangle());
                    customer.setAtDestination(true);
                    break;
                }

                try {
                    Thread.sleep(timeInterval * 5); // Adjust the time interval as needed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        customerThread.start();
    }

    private void simulateCashierActivities(Cashier cashier) {
        Thread cashierThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (cashier.getQueue().size() > 0) {
                    try {
                        Customer customer = cashier.getQueue().remove(); // Remove the customer from the queue
                        customer.getCircle().setFill(Color.GREEN); // Change the color of the circle to green to indicate that the customer is being processed
                        Thread.sleep((int) (Math.random() * (timeInterval * 10)) + timeInterval); // Simulating cashier processing time

                        System.out.println(customer.getName() + " bought " + customer.getCartString());
                        removeCustomerFigure(customer);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                try {
                    Thread.sleep(timeInterval * 5); // Add a small delay between processing the queue
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        cashierThread.start();
    }

    // Helper method to get a random shelf from the list
    private Shelf getRandomShelf(List<Shelf> shelves) {
        return shelves.get((int) (Math.random() * shelves.size()));
    }
}
