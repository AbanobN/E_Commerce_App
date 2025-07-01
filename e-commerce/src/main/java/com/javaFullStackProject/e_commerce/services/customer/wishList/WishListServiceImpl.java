package com.javaFullStackProject.e_commerce.services.customer.wishList;


import com.javaFullStackProject.e_commerce.dto.WishListDto;
import com.javaFullStackProject.e_commerce.entity.Product;
import com.javaFullStackProject.e_commerce.entity.User;
import com.javaFullStackProject.e_commerce.entity.WishList;
import com.javaFullStackProject.e_commerce.repository.ProductRepository;
import com.javaFullStackProject.e_commerce.repository.UserRepository;
import com.javaFullStackProject.e_commerce.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService{

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final WishListRepository wishListRepository;

    @Override
    public WishListDto addProductToWishList(WishListDto wishListDto){
        Optional<Product> optionalProduct = productRepository.findById(wishListDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(wishListDto.getUserId());

        if(optionalProduct.isPresent() && optionalUser.isPresent()){
            WishList wishList = new WishList();

            wishList.setProduct(optionalProduct.get());
            wishList.setUser(optionalUser.get());

            return wishListRepository.save(wishList).getWishListDto();
        }
        return null;
    }

    @Override
    public List<WishListDto> getWishListByUserId(Long userId){
        return wishListRepository.findAllByUserId(userId).stream().map(WishList::getWishListDto).collect(Collectors.toList());
    }
}
