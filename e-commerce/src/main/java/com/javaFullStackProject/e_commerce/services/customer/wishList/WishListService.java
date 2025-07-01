package com.javaFullStackProject.e_commerce.services.customer.wishList;

import com.javaFullStackProject.e_commerce.dto.WishListDto;

import java.util.List;

public interface WishListService {
    WishListDto addProductToWishList(WishListDto wishListDto);
    List<WishListDto> getWishListByUserId(Long userId);
}
