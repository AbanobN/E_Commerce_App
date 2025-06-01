package com.javaFullStackProject.e_commerce.services.customer.cart;

import com.javaFullStackProject.e_commerce.dto.AddProductInCartDto;
import com.javaFullStackProject.e_commerce.dto.CartItemsDto;
import com.javaFullStackProject.e_commerce.dto.OrderDto;
import com.javaFullStackProject.e_commerce.entity.CartItem;
import com.javaFullStackProject.e_commerce.entity.Order;
import com.javaFullStackProject.e_commerce.entity.Product;
import com.javaFullStackProject.e_commerce.entity.User;
import com.javaFullStackProject.e_commerce.enums.OrderStatus;
import com.javaFullStackProject.e_commerce.repository.CartItemsRepository;
import com.javaFullStackProject.e_commerce.repository.OrderRepository;
import com.javaFullStackProject.e_commerce.repository.ProductRepository;
import com.javaFullStackProject.e_commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto){

        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);

        Optional<CartItem> optionalCartItem = cartItemRepository.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());

        if(optionalCartItem.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product already exists in cart");
        }
        else{
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());


            if(optionalProduct.isPresent() && optionalUser.isPresent()){

                CartItem cartItem = new CartItem();
                cartItem.setProduct(optionalProduct.get());
                cartItem.setPrice(optionalProduct.get().getPrice());
                cartItem.setQuantity(1L);
                cartItem.setUser(optionalUser.get());
                cartItem.setOrder(activeOrder);

                CartItem updatedCart = cartItemRepository.save(cartItem);

                activeOrder.setAmount(activeOrder.getAmount() + cartItem.getPrice());
                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cartItem.getPrice());

                activeOrder.getCartItems().add(cartItem);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(null);

            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Or Product not found");
            }
        }
    }



    @Override
    public OrderDto getCartByUserId(Long userId){

        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);

        List<CartItemsDto> cartItemsDto = activeOrder.getCartItems().stream().map(CartItem::getCartDto).toList();

        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDto);

        return orderDto;
    }
}

