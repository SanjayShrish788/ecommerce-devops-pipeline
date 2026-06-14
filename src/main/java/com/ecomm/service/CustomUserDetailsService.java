package com.ecomm.service;

import com.ecomm.model.Customer;
import com.ecomm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optCustomer = customerRepository.findByUsername(username);
        if (optCustomer.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Customer customer = optCustomer.get();
        return User.withUsername(customer.getUsername())
                .password(customer.getPassword())
                .roles(customer.getRole())
                .build();
    }
}
