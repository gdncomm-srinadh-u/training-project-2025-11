package com.Cart.CartService.dto;

import lombok.Data;

@Data
public class ProductResponsesDTO {
    private String sku;
    private String name;
    private String description;
    private Double price;
    private String categoryID;
    private boolean active;
}
