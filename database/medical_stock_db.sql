
CREATE DATABASE IF NOT EXISTS medical_stock_db;

USE medical_stock_db;

-- 1. Admin users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(30) DEFAULT 'ADMIN'
);

-- Demo admin account
-- Username: admin
-- Password: admin123

INSERT IGNORE INTO users
(username, password, role)
VALUES
('admin', SHA2('admin123', 256), 'ADMIN');


-- 2. Medicines table
CREATE TABLE IF NOT EXISTS medicines (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    batch VARCHAR(50),
    manufacturer VARCHAR(100),
    quantity INT NOT NULL DEFAULT 0,
    price DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    manufacturing_date DATE,
    expiry_date DATE
);


-- 3. Stock transactions table
CREATE TABLE IF NOT EXISTS stock_transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    medicine_id INT NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    quantity INT NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (medicine_id)
    REFERENCES medicines(id)
    ON DELETE CASCADE
);


-- Check database tables
SHOW TABLES;

-- Check admin account
SELECT * FROM users;

-- Check medicine records
SELECT * FROM medicines;
