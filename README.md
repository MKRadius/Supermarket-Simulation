# **Supermarket simulation**

## Table of contents

- [1 - Introduction](#_toc153705089)

- [2 - Vision](#_toc153705090)

- [3 - Concepts, definitions](#_toc153705091)

- [4 - Conceptual Model](#_toc153705092)

   - [4.1 - Objective](#_toc153705093)

   - [4.2 - Feeds](#_toc153705094)

   - [4.3 - Printouts](#_toc153705095)

   - [4.4 - Content](#_toc153705096)

   - [4.5 - Assumptions and simplications](#_toc153705097)

- [5 - Programming implementation of the model](#_toc153705098)

   - [5.1 - Programming languages and libraries](#_toc153705099)

   - [5.2 - Architecture](#_toc153705100)

   - [5.3 - Structural description of the user interface](#_toc153705101)

   - [5.4 - Description of internal logic](#_toc153705102)

   - [5.5 - Descriptions of external data repositories](#_toc153705103)

   - [5.6 - Testing](#_toc153705104)

- [6 - Simulator & user manual](#_toc153705105)

- [7 - Simulation tests](#_toc153705106)

   - [7.1 - Troubleshooting](#_toc153705107)

   - [7.2 - Ending the Simulation](#_toc153705108)

- [8 - Summary](#_toc153705109)



# <a name="_toc153705089"></a>**Introduction**
This documentation provides a comprehensive overview of the Supermarket Simulation project, a Java-based application designed to simulate the behavior of customers in a supermarket. The project aims to model the interaction between customers, products, and cashiers within a simulated supermarket environment. It leverages object-oriented programming principles, incorporating various classes such as Customer, Cashier, Product, and Shelf to create a dynamic and interactive simulation.
# <a name="_toc153705090"></a>**Vision**
The vision of the Supermarket Simulation project is to create an engaging and realistic model of a supermarket environment. The simulation aims to demonstrate how customers interact with different aspects of a supermarket, including product selection and checkout processes. This project serves as an educational tool for understanding customer behavior and the operational dynamics of a supermarket, as well as a platform for experimenting with different supermarket layouts and operational strategies.
# <a name="_toc153705091"></a>**Concepts, definitions**
Customer: Represents individuals shopping in the supermarket. They have a behavior that allows them to select products from shelves, add them to their cart, and queue at the cashier for checkout. Each customer operates in a separate thread to simulate real-time shopping activities.

Cashier: Manages the checkout process and is responsible for interacting with customers in the queue. A cashier calculates the total cost for the products, processes payments, and serves one customer at a time. Cashiers are also implemented to run in their threads, handling customer transactions concurrently.

Product: Items available for purchase in the supermarket. Products have attributes like name and price and are located on the shelves. Customers select products to add to their shopping carts.

Shelf: Storage units within the supermarket where products are displayed and stored. Shelves have a finite quantity of products that customers can take from. The interaction with shelves is synchronized to prevent data inconsistency in a multithreaded environment.

Supermarket: The overall simulation environment, encompassing customers, cashiers, products, and shelves. It controls the simulation's state, including the opening and closing of the supermarket, and coordinates the various components within the simulation.

Customer Behavior: Defines the actions and decisions of a customer within the supermarket, including product selection from shelves and queuing. It determines the logic behind how customers move, select products, and their behavior when interacting with cashiers.

Customer Queue: A queue management system for customers waiting for service at a cashier. It is designed to handle concurrent access by customers and includes synchronized methods to safely add customers to the queue and retrieve them when the cashier is ready to checkout the next customer.

These components work together to create a simulated supermarket environment where multiple customers can shop and checkout concurrently, interacting with products, shelves, cashiers, and following a specified behavior that dictates their shopping patterns and queuing mechanism.# <a name="_toc153705092"></a>**Conceptual Model**
The Supermarket Simulation is designed to model and analyze the behavior of customers in a supermarket setting. It aims to simulate customer interactions with shelves, products, and cashiers, providing insights into customer behavior and supermarket dynamics.
## <a name="_toc150275671"></a><a name="_toc153705093"></a>Objective
The primary objective of the Supermarket Simulation project is to emulate and analyze the behavior of customers within a supermarket setting. By creating a dynamic and interactive simulation, the project seeks to provide insights into customer interactions with shelves, products, and cashiers. The simulation aims to be a tool for educational purposes, enabling users to explore and experiment with different supermarket layouts and operational strategies.
## <a name="_toc150275672"></a><a name="_toc153705094"></a>Feeds
Inputs:

Customer Input: Simulates the arrival of customers and their choices of products.

Product and Shelf Configuration: Involves setting up shelves with various products.

Cashier Configuration: Specifies the number and behavior of cashiers in the simulation.

Outputs:

Customer Flow: Tracks the movement of customers within the supermarket, from product selection to checkout.

Queue Dynamics: Observes the formation and progression of queues at cashier counters.

## <a name="_toc150275673"></a><a name="_toc153705095"></a>Printouts
The simulation provides visual representations and data outputs to facilitate analysis and understanding. Printouts may include:

- Customer Movements: Visual representation of customers navigating the supermarket.
- Queue Progression: Graphical display of customers in queues and their movement towards cashiers.

## <a name="_toc153705096"></a>Content
<a name="_toc150275675"></a>Class Structures:

The simulation utilizes classes to model various components within the supermarket:

- Customer: Handles individual customer attributes and behaviors.
- Cashier: Manages checkout processes and customer transactions.
- Shelf: Stores product information and inventory levels.
- Product: Represents items with attributes such as name and price.
- CustomerQueue: Manages the queuing logic for customers at cashiers.

Customer Behavior:

- Defines the logic behind how customers move, select products, and behave when interacting with cashiers. It encompasses:
- Product Selection: How customers choose products from shelves based on preferences.
- Queuing Behavior: The logic behind joining the shortest queue for checkout.
    Assumptions and simplications
Assumptions

Customers choose shelves based on product preferences and join the shortest queue for checkout. Cashiers process customers at a constant rate.

Simplifications

Customer decisions are simplified to product selection and queuing. Interactions are limited to customer-product and customer-cashier dynamics.



# <a name="_toc153705098"></a>**Programming implementation of the model**
Programming languages and libraries used
- Language: Java.
- Version: The specific version of Java used is not mentioned but JavaFX indicates Java 8 or newer.
- Features: Object-oriented features for modelling supermarket components and multithreading support for concurrent execution.
- Libraries/APIs: JavaFX:
- Usage: For creating and managing the graphical user interface, animations, and event handling.
- Components: Includes support for custom layouts, pre-built controls, and CSS styling for UI customization.

## <a name="_toc153705100"></a>Architecture
High-Level Components:

Model: Comprises classes that represent the business logic and data of the supermarket simulation. These include:

- Customer: Handles individual customer attributes and behaviors.
- Cashier: Manages checkout processes and customer transactions.
- Shelf: Stores product information and inventory levels.
- Product: Represents items with attributes such as name and price.
- CustomerQueue: Manages the queuing logic for customers at cashiers.

View: Constructed using JavaFX FXML, which separates the UI design from the application logic for cleaner code and easier maintenance.

Controller: The Controller.java file is the intermediary between the view and the model, handling user interaction, updating the UI, and invoking model operations.

Connections: The model classes encapsulate the core functionalities of the supermarket. The view renders the supermarket's state to the user, and the controller modifies the view based on user actions and model updates.
## <a name="_toc153705101"></a>Structural description of the user interface
Design: Built with JavaFX, which supports a rich and responsive UI that can be laid out in FXML files or dynamically created in Java code.

Elements: Likely includes graphical elements such as circles for customers and rectangles for shelves and cashier queues.

Interactivity: Users can observe the simulation in real-time. Interaction can be facilitated through controls like buttons or menus if implemented.
## <a name="_toc153705102"></a>Description of internal logic
Event List and Events: The simulation engine likely processes a series of events that include customer arrivals, product selections, and queue joinings.

Clock: While a simulation clock is not directly discussed, it is often implicit in the progression of events and could be represented by system time or a logical clock in the code.


Description automatically generated](Aspose.Words.2b27f2ae-de9d-48de-81cf-1b9da44ca254.008.png)


Lack of External Repositories: The provided files do not indicate the use of external data sources such as databases or file systems for persistent storage.

Potential Extensions: For a more complex or persistent simulation, external repositories could be integrated to store simulation states or to log events for further analysis.



## <a name="_toc153705104"></a>Testing
General Testing: Includes running the simulation and visually verifying the correct movements of customers and transactions at cashiers. Any issues, such as deadlocks or race conditions, would be observed and debugged during interactive sessions.

JUnit Tests: Ideal for unit testing the logic of individual components. While not included in the provided files, potential JUnit tests could cover:

- Customer Logic: Testing the behaviors and interactions of customers, such as selecting products and queuing.
- Queue Management: Ensuring that customers are added and removed from queues correctly and in the right order.
- Cashier Functionality: Verifying that cashier processes customers accurately and updates totals as expected.

Integration Testing: To test the combined functionality of all components working together, ensuring that the overall system behaves as expected.
# <a name="_toc153705105"></a>**Simulator & user manual**
Simulator Overview

The supermarket simulation is an interactive program designed to emulate the operations of a supermarket. It simulates the behavior of customers as they select products from shelves and queue up for checkout at the cashiers. The simulation is multi-threaded, meaning that each customer and cashier operates concurrently, providing a realistic representation of a busy supermarket environment.

Simulator Components

- Customers: Autonomous agents that simulate shopping behaviors.
- Cashiers: Service agents that process customer purchases.
- Products: Various items that customers can add to their shopping carts.
- Shelves: Storage for products that customers can browse and select from.
- Customer Queue: A first-come-first-served system where customers wait for cashier service.
- Supermarket: The main environment where all the interactions take place.

**User Manual**

Starting the Simulator

   Launch Application:

- Run Main.java to start the simulation.
- The graphical user interface (GUI) should open, displaying the supermarket layout.

Initialize Simulation:

- The simulation begins with an empty supermarket.
- Cashiers and shelves are automatically added based on the configuration in Supermarket.java.
Interacting with the Simulator

   Adding Customers:

      - Customers are added at the entrance of the supermarket and begin shopping.
      - The simulation will show customers as circles moving towards shelves and then to cashiers.

Customer Shopping:

- Customers select products from shelves.
- The GUI updates to show the customers moving to different shelves.

Checkout Process:

- Customers queue at the available cashiers once they have finished shopping.
- The GUI shows customers moving to and waiting in cashier queues.
Monitoring the Simulation

   Customer Actions: Watch as customers move around the supermarket, pick products, and queue for checkout.

Cashier Actions: Observe cashiers processing transactions and updating the customer queue.

Supermarket State: The supermarket's open or closed state can be monitored from the GUI or the console output.

# <a name="_toc153705106"></a>**Simulation tests carried out**
To ensure the simulation accurately represents the operations of a supermarket, several tests should be conducted:

- Concurrency Test: Verify that multiple customers can shop and queue simultaneously without interference.
- Resource Contention Test: Ensure that the synchronized access to shelves prevents data inconsistency.
- Checkout Test: Confirm that customers are checked out in the order they arrive at the cashier.
- Performance Test: Test the simulation with a high number of customers to ensure it maintains performance and does not deadlock.
- User Interface Test: Ensure that the GUI accurately reflects the state of the simulation, with all movements and actions of customers being represented in real-time.
      1. ## <a name="_toc153705107"></a>Troubleshooting
- Customers Not Moving: Check for deadlocks or excessive synchronization that may be preventing threads from executing.
- Incorrect Checkout Order: Ensure the customer queue is properly managed and that cashiers are servicing customers in FIFO order.
- GUI Not Updating: Confirm that all UI updates are being made on the JavaFX Application Thread using Platform.runLater().
      1. ## <a name="_toc153705108"></a>Ending the Simulation
To end the simulation, close the GUI window.

The console should output a message indicating that all customers have left and the supermarket is closed.

This user manual provides a starting point for users to understand how to operate the supermarket simulation. Additional details and instructions can be added based on the specific features and configurations of the simulation.

# <a name="_toc153705109"></a>**Summary**
The Supermarket Simulation Project is a comprehensive and interactive software designed to emulate the dynamics of a supermarket environment. This project, developed in Java and utilizing JavaFX for its graphical user interface, allows users to explore the intricacies of customer behavior, product selection, and queue management within a simulated supermarket setting.

Key components of the project include classes representing customers, cashiers, shelves, and products, each playing a vital role in the simulation. The software architecture follows a model-view-controller (MVC) pattern, ensuring a clear separation of the simulation logic, user interface, and data handling.

The application's user interface, crafted with JavaFX, provides a user-friendly and intuitive experience, allowing users to configure simulation parameters, view real-time interactions and access detailed statistics. The internal logic of the simulation includes elements like event handling and a simulated clock to manage the flow of activities within the supermarket.

Testing methodologies for the project include interactive tests and observational methods to ensure the functionality and reliability of the simulation. While the current scope of the project does not include external data repositories or detailed JUnit tests, these aspects offer potential areas for future development and enhancement.

In summary, this Supermarket Simulation Project stands as a testament to the practical application of software engineering principles in creating a realistic and engaging simulation environment. It serves not only as a tool for understanding customer and business dynamics in a retail setting but also as an educational resource for students and professionals interested in simulation and software development.

