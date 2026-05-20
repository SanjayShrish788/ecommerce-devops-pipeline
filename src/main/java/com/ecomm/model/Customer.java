package com.ecomm.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 20)
    private String role = "USER";

    @Column(columnDefinition = "TEXT")
    private String address;

}
