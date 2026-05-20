package com.ecomm.repository;

import com.ecomm.model.Cart;
import com.ecomm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomer(Customer customer);
}
