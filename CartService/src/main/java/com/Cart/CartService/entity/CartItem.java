package com.Cart.CartService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private String itemId;
    private String itemName;
    private Double itemPrice;
    private String itemDescription;
}
