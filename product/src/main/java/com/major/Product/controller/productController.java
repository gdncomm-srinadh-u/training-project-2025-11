package com.major.Product.controller;

import com.major.Product.Constants;
import com.major.Product.Service.ProductService;
import com.major.Product.dto.Productcreatedto;
import com.major.Product.dto.Productresponsedto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = Constants.Product_Service_management, description = "APIs for managing Products")
public class productController {


    private final ProductService productService;

    public productController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public Productresponsedto create(@Valid @RequestBody Productcreatedto dto) {
        return productService.createProduct(dto);
    }

    @PostMapping("/bulkproducts")
    public List<Productresponsedto> createBulk(@RequestBody List<Productcreatedto> products) {
        return productService.createBulkProducts(products);
    }


    @GetMapping
    public List<Productresponsedto> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        return productService.list(page, size);
    }

    @GetMapping("/search")
    public List<Productresponsedto> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return productService.search(keyword, page, size);
    }

    @GetMapping("/{sku}")
    public Productresponsedto get(@PathVariable String sku) {
        return productService.getBySku(sku);
    }

    @PostMapping("/get-products")
    public List<Productresponsedto> getProducts(@RequestBody List<String> productIds) {
        return productService.getProductsByIds(productIds);
    }

}