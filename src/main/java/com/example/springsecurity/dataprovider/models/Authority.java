package com.example.springsecurity.dataprovider.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "AUTHORITY")
public class Authority {

    @Id
    @Column(name = "authorityId")
    private String id;

    @Column(name = "role")
    private String role;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "authorities")
    List<Customer> customers;
}
