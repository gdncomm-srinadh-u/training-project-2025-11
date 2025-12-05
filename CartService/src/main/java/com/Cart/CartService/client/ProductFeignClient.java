package com.Cart.CartService.client;

import com.Cart.CartService.Constants;
import com.Cart.CartService.dto.ProductResponsesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = Constants.PRODUCT_SERVICE, url = "http://localhost:8099")
public interface ProductFeignClient {
    @PostMapping("/product/get-products")
    List<ProductResponsesDTO> getProductsByIds(@RequestBody List<String> productIds);
}
