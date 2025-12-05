package com.Cart.CartService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemRequestDTO {
    private String itemName;
    private Double itemPrice;
    private String itemDescription;
}
