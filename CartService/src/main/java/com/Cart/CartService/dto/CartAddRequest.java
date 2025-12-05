package com.Cart.CartService.dto;

import lombok.Data;

@Data
public class CartAddRequest {
    private String productId;
    private String name;
    private Double price;
    private int quantity;
    private String categoryID;
}

