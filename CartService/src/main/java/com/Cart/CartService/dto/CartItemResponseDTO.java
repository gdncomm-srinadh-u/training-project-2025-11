package com.Cart.CartService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDTO {
    private String itemId;
    private String itemName;
    private Double itemPrice;
    private String itemDescription;
}
