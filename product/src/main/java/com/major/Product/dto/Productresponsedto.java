package com.major.Product.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Productresponsedto {
    private String sku;
    private String name;
    private String description;
    private Double price;
    private String categoryID;
    private boolean active;
    private List<String> image;

}
