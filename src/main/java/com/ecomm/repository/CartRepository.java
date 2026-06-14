package com.ecomm.repository;

import com.ecomm.model.Cart;
import com.ecomm.model.Customer;
import com.ecomm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomer(Customer customer);
    // Used to de-duplicate cart entries (prevents adding same product as a new row)
    Optional<Cart> findByCustomerAndProduct(Customer customer, Product product);
}
