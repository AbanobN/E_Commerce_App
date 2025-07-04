package com.javaFullStackProject.e_commerce.controller.customer;

import com.javaFullStackProject.e_commerce.dto.WishListDto;
import com.javaFullStackProject.e_commerce.services.customer.wishList.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishList(@RequestBody WishListDto wishListDto){
        WishListDto postedWishList = wishListService.addProductToWishList(wishListDto);

        if(postedWishList == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishList);
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishListDto>> getWishListByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(wishListService.getWishListByUserId(userId));
    }
}
