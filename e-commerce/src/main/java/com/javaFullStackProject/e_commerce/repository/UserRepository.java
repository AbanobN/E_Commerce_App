package com.javaFullStackProject.e_commerce.repository;

import com.javaFullStackProject.e_commerce.entity.User;
import com.javaFullStackProject.e_commerce.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);
    User findByRole(UserRole role);
}
