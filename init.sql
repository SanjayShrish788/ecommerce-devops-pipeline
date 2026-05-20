-- Create initial categories
INSERT INTO category (name) VALUES ('Fruits');
INSERT INTO category (name) VALUES ('Toys');
INSERT INTO category (name) VALUES ('Electronics');

-- Create an admin user
-- Password is 'admin123' hashed using BCrypt (cost factor 10)
INSERT INTO customer (username, email, password, role, address) 
VALUES ('admin', 'admin@example.com', '$2a$10$wY1twTgHXbO.80fX.Y/j/OSj09H9B20Z/ZtqQO.4/wV6O4m3.YVUy', 'ADMIN', 'Admin HQ');

-- Optionally add a test user ('user' / 'user123')
INSERT INTO customer (username, email, password, role, address)
VALUES ('user', 'user@example.com', '$2a$10$fWn3F2v.L00cRz7eU7fM/eh/hB9.QOa9w5GkR.n1xP1nF5F1f.D9a', 'USER', 'User Home');
