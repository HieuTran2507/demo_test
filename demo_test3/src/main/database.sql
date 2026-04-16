create database demo_test3;

use demo_test3;

CREATE TABLE customers (
   id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100)
);

CREATE TABLE products (
   id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100),
   price DOUBLE
);

CREATE TABLE orders (
   id INT AUTO_INCREMENT PRIMARY KEY,
   customer_id INT,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE order_items (
   id INT AUTO_INCREMENT PRIMARY KEY,
   order_id INT,
   product_id INT,
   quantity INT,
   FOREIGN KEY (order_id) REFERENCES orders(id),
   FOREIGN KEY (product_id) REFERENCES products(id)
);

select o.id as order_id, c.name as customer_name, p.name as product_name, oi.quantity
from orders o inner join customers c on o.customer_id = c.id
inner join order_items oi on oi.order_id = o.id
inner join products p on p.id = oi.product_id;


