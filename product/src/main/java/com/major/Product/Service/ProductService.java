package com.major.Product.Service;


import com.major.Product.dto.Productresponsedto;
import com.major.Product.dto.Productcreatedto;
import jakarta.validation.Valid;
import java.util.List;

public interface ProductService {

    public Productresponsedto createProduct(@Valid Productcreatedto dto);

    List<Productresponsedto> list(int page, int size);

    List<Productresponsedto> search(String keyword, int page, int size);

    List<Productresponsedto> createBulkProducts(List<Productcreatedto> dtoList);

    Productresponsedto getBySku(String sku);

    List<Productresponsedto> getProductsByIds(List<String> productIds);
}