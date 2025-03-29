package com.javaFullStackProject.e_commerce.repository;

import com.javaFullStackProject.e_commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
