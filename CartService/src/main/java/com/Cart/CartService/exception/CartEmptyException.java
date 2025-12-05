package com.Cart.CartService.exception;

public class CartEmptyException extends RuntimeException{
    public CartEmptyException(String message) {
        super(message);
    }
}

