package com.Cart.CartService.service;

import com.Cart.CartService.dto.ProductResponsesDTO;

import java.util.List;

public interface CartService {
    String addToCart(String userEmail, String productId);
    List<ProductResponsesDTO>  getCart(String userEmail);
    String removeFromCart(String userEmail, String productId);
    String clearCart(String userEmail);
}
