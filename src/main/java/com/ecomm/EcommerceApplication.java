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
            // Check if admin exists
            if (customerRepository.findByUsername("admin").isEmpty()) {
                Customer admin = new Customer();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                admin.setAddress("Admin HQ");
                customerRepository.save(admin);
            }
            
            // Add a default category if none exist
            if (categoryRepository.findAll().isEmpty()) {
                Category cat1 = new Category();
                cat1.setName("Electronics");
                categoryRepository.save(cat1);
                
                Category cat2 = new Category();
                cat2.setName("Fruits");
                categoryRepository.save(cat2);
                
                // Add default products
                Product p1 = new Product();
                p1.setName("Smartphone");
                p1.setDescription("Latest model smartphone");
                p1.setPrice(new BigDecimal("699.99"));
                p1.setQuantity(50);
                p1.setCategory(cat1);
                p1.setImage("https://via.placeholder.com/300x200?text=Smartphone");
                productRepository.save(p1);

                Product p2 = new Product();
                p2.setName("Apple");
                p2.setDescription("Fresh red apples");
                p2.setPrice(new BigDecimal("1.99"));
                p2.setQuantity(200);
                p2.setCategory(cat2);
                p2.setImage("https://via.placeholder.com/300x200?text=Apple");
                productRepository.save(p2);
            }
        };
    }
}
