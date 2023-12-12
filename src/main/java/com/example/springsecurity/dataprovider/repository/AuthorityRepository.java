package com.example.springsecurity.dataprovider.repository;

import com.example.springsecurity.dataprovider.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
