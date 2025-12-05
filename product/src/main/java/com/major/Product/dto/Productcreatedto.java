package com.major.Product.dto;
import com.major.Product.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Productcreatedto {

    @NotBlank(message = Constants.Name_Not_Blank)
    private String name;

    private String description;

    @NotBlank
    private String categoryID;

    @NotNull
    @Positive(message = Constants.Price_Not_Greater_than_Zero)
    private Double price;
    @Positive(message = Constants.Stock_Greater_than_Zero)
    private Long Stock;

    private List<String> image;
    private boolean active = true;


}
