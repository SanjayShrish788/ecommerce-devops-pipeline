package com.ecomm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecomm.model.Customer;
import com.ecomm.model.Category;
import com.ecomm.model.Product;
import com.ecomm.repository.CustomerRepository;
import com.ecomm.repository.CategoryRepository;
import com.ecomm.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.math.BigDecimal;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(CustomerRepository customerRepository,
                                        CategoryRepository categoryRepository,
                                        ProductRepository productRepository,
                                        PasswordEncoder passwordEncoder) {
        return args -> {
            // Seed admin account
            if (customerRepository.findByUsername("admin").isEmpty()) {
                Customer admin = new Customer();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                admin.setAddress("Admin HQ");
                customerRepository.save(admin);
            }

            // Seed a default USER account (replaces the broken BCrypt hash in init.sql)
            if (customerRepository.findByUsername("user").isEmpty()) {
                Customer user = new Customer();
                user.setUsername("user");
                user.setEmail("user@example.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole("USER");
                user.setAddress("User Home");
                customerRepository.save(user);
            }

            // Seed default categories and products
            if (categoryRepository.findAll().isEmpty()) {
                Category cat1 = new Category();
                cat1.setName("Electronics");
                categoryRepository.save(cat1);

                Category cat2 = new Category();
                cat2.setName("Fruits");
                categoryRepository.save(cat2);

                Product p1 = new Product();
                p1.setName("Smartphone");
                p1.setDescription("Latest model smartphone with advanced features");
                p1.setPrice(new BigDecimal("699.99"));
                p1.setQuantity(50);
                p1.setCategory(cat1);
                p1.setImage("https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=300&h=200&fit=crop");
                productRepository.save(p1);

                Product p2 = new Product();
                p2.setName("Wireless Headphones");
                p2.setDescription("Premium noise-cancelling wireless headphones");
                p2.setPrice(new BigDecimal("149.99"));
                p2.setQuantity(30);
                p2.setCategory(cat1);
                p2.setImage("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=300&h=200&fit=crop");
                productRepository.save(p2);

                Product p3 = new Product();
                p3.setName("Fresh Apple");
                p3.setDescription("Crisp and fresh red apples, locally sourced");
                p3.setPrice(new BigDecimal("1.99"));
                p3.setQuantity(200);
                p3.setCategory(cat2);
                p3.setImage("https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=300&h=200&fit=crop");
                productRepository.save(p3);

                Product p4 = new Product();
                p4.setName("Banana Bunch");
                p4.setDescription("Sweet and ripe bananas");
                p4.setPrice(new BigDecimal("0.99"));
                p4.setQuantity(150);
                p4.setCategory(cat2);
                p4.setImage("https://images.unsplash.com/photo-1571771894821-ce9b6c11b08e?w=300&h=200&fit=crop");
                productRepository.save(p4);
            }
        };
    }
}
