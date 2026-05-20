CREATE DATABASE IF NOT EXISTS ecommjava;
USE ecommjava;

CREATE TABLE IF NOT EXISTS category (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER',
    address TEXT
);

-- init.sql provides raw SQL schema if needed, but hibernate handles table creation.
-- The inserts have been moved to CommandLineRunner in Spring Boot for proper BCrypt encoding.


INSERT INTO customer (username, email, password, role, address)
VALUES ('user', 'user@example.com', '$2a$10$fWn3F2v.L00cRz7eU7fM/eh/hB9.QOa9w5GkR.n1xP1nF5F1f.D9a', 'USER', 'User Home');
