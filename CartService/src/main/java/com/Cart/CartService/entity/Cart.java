package com.Cart.CartService.entity;

import com.Cart.CartService.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = Constants.CART)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    private String id;

    private String userEmail;

    private List<String> productIds = new ArrayList<>();
}
