package com.Cart.CartService.service.impl;

import com.Cart.CartService.Constants;
import com.Cart.CartService.client.ProductFeignClient;
import com.Cart.CartService.dto.ProductResponsesDTO;
import com.Cart.CartService.entity.Cart;
import com.Cart.CartService.repository.CartRepository;
import com.Cart.CartService.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductFeignClient client;

    @Override
    public String addToCart(String userEmail, String productId) {

        Cart cart = cartRepository.findByUserEmail(userEmail)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserEmail(userEmail);
                    return newCart;
                });

        cart.getProductIds().add(productId);
        cartRepository.save(cart);

        return "Product " + productId + Constants.ADDED_TO_CART  + userEmail;
    }

    @Override
    public List<ProductResponsesDTO> getCart(String userEmail) {

        List<String> productIds = cartRepository.findByUserEmail(userEmail)
                .map(Cart::getProductIds)
                .orElse(List.of());

        if (productIds.isEmpty()) {
            return List.of();
        }

        return client.getProductsByIds(productIds);
    }

    @Override
    public String removeFromCart(String userEmail, String productId) {

        Cart cart = cartRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new RuntimeException(Constants.CART_NOT_FOUND));

        cart.getProductIds().remove(productId);
        cartRepository.save(cart);

        return "Removed " + productId + Constants.FROM_CART_FOR+ userEmail;
    }

    @Override
    public String clearCart(String userEmail) {

        Cart cart = cartRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new RuntimeException(Constants.CART_NOT_FOUND));

        cart.getProductIds().clear();
        cartRepository.save(cart);

        return Constants.CART_CLEARED_FOR+ userEmail;
    }
}
