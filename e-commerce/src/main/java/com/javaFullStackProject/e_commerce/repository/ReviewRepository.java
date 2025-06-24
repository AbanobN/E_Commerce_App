package com.javaFullStackProject.e_commerce.repository;

import com.javaFullStackProject.e_commerce.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
