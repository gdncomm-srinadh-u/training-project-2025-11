package com.Cart.CartService.exception;

public class ItemNotInCartException extends RuntimeException{
    public ItemNotInCartException(String message) {
        super(message);
    }
}
