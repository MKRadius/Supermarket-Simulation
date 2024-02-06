-- Drop previous database if it exists
DROP DATABASE IF EXISTS supermarket;

-- Create a new database
CREATE DATABASE supermarket;

-- Use the newly created database
USE supermarket;

-- Create a table for storing Currency objects
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL
);

-- Populate the table with sample data for eight currencies
INSERT INTO product (name, price, quantity) VALUES
    ('milk', 2.50, 1000),
    ('bread', 1.75, 1000),
    ('egg', 3.20, 2000),
    ('cheese', 4.50, 500),
    ('apple', 2.00, 400),
    ('banana', 1.80, 300);

-- Drop the user account appuser if it exists
DROP USER IF EXISTS 'appuser'@'localhost';

-- Create the user account appuser
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'password123';

-- Grant necessary privileges to the appuser
GRANT ALL PRIVILEGES ON supermarket.* TO 'appuser'@'localhost';

-- Flush privileges to apply changes
FLUSH PRIVILEGES;
