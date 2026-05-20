package com.ecomm.service;

import com.ecomm.model.Cart;
import com.ecomm.model.Customer;
import com.ecomm.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartItems(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }

    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }
}
