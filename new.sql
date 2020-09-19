CREATE SCHEMA IF NOT EXISTS hibernate;
DROP TABLE IF EXISTS hibernate.products CASCADE;
CREATE TABLE hibernate.products (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), price DECIMAL);
INSERT INTO hibernate.products (name, price) VALUES
('Bread', 25.50),
('Butter', 120.99),
('Coffee', 450.90),
('Cheese', 650),
('Tea', 250.80),
('Milk', 79.90),
('Tomatoes', 150.99),
('Cucumbers', 89.90),
('Potatoes', 49.00),
('Eggs', 130.49);

DROP TABLE IF EXISTS hibernate.customers CASCADE;
CREATE TABLE hibernate.customers (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255));
INSERT INTO hibernate.customers (name) VALUES
('Tom'),
('Billy'),
('Edd'),
('Bob');

DROP TABLE IF EXISTS hibernate.customer_products CASCADE;
CREATE TABLE hibernate.customer_products (customer_id INT NOT NULL, product_id INT NOT NULL, dealPrice DECIMAL, FOREIGN KEY (customer_id) REFERENCES customers (id), FOREIGN KEY (product_id) REFERENCES products (id));
INSERT INTO hibernate.customer_products (customer_id, product_id, dealPrice) VALUES
(1, 1, 24.00),
(1, 2, 115.00),
(1, 4, 600.00),
(2, 3, 460.00),
(2, 5, 225.99),
(3, 6, 72.50),
(3, 3, 450.00),
(3, 2, 110.00),
(3, 1, 22.90),
(4, 8, 79.90),
(4, 9, 33.00),
(4, 10, 100.49);
