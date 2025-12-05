package com.Cart.CartService.controller;

import com.Cart.CartService.dto.ProductResponsesDTO;
import com.Cart.CartService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{userEmail}/{productId}")
    public String addToCart(@PathVariable String userEmail,
                            @PathVariable String productId) {
        return cartService.addToCart(userEmail, productId);
    }

    @GetMapping("/{userEmail}")
    public List<ProductResponsesDTO>  getCart(@PathVariable String userEmail) {
        return cartService.getCart(userEmail);
    }

    @DeleteMapping("/remove/{userEmail}/{productId}")
    public String remove(@PathVariable String userEmail, @PathVariable String productId) {
        return cartService.removeFromCart(userEmail, productId);
    }

    @DeleteMapping("/clear/{userEmail}")
    public String clearCart(@PathVariable String userEmail) {
        return cartService.clearCart(userEmail);
    }
}
