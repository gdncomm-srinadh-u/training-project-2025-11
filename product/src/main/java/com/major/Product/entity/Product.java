package com.major.Product.entity;


import com.major.Product.Constants;
import lombok.Data;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Constants.Product)
@Data
public class Product {
    @Id
    private String sku;
    private String name;
    private String description;
    private Double price;
    private String categoryID;
    private Long Stock;

    private boolean active = true;


}
