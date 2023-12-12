package com.example.springsecurity.dataprovider.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
public class Customer {

    @Id
    @Column(name = "customerId")
    private String customerId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "CUSTOMER_AUTHORITY",
            joinColumns = @JoinColumn(name = "customerId"),
            inverseJoinColumns = @JoinColumn(name = "authorityId")
    )
    List<Authority> authorities;

}
