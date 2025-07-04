package com.javaFullStackProject.e_commerce.repository;

import com.javaFullStackProject.e_commerce.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList , Long> {

    List<WishList> findAllByUserId(Long userId);
}
