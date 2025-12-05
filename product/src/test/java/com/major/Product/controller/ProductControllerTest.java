package com.major.Product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.major.Product.Service.ProductService;
import com.major.Product.dto.Productcreatedto;
import com.major.Product.dto.Productresponsedto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(productController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createProduct_Success() throws Exception {
        Productcreatedto requestDto = new Productcreatedto();
        requestDto.setName("Test Product");
        requestDto.setPrice(100.0);
        requestDto.setCategoryID("CAT1");
        requestDto.setStock(10L);

        Productresponsedto responseDto = new Productresponsedto();
        responseDto.setSku("SKU123");
        responseDto.setName("Test Product");
        responseDto.setPrice(100.0);

        when(productService.createProduct(any(Productcreatedto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sku").value("SKU123"))
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    public void createBulkProducts_Success() throws Exception {
        Productcreatedto requestDto = new Productcreatedto();
        requestDto.setName("Test Product");
        requestDto.setPrice(100.0);
        requestDto.setCategoryID("CAT1");
        requestDto.setStock(10L);
        List<Productcreatedto> requestList = Arrays.asList(requestDto);

        Productresponsedto responseDto = new Productresponsedto();
        responseDto.setSku("SKU123");
        responseDto.setName("Test Product");
        List<Productresponsedto> responseList = Arrays.asList(responseDto);

        when(productService.createBulkProducts(anyList())).thenReturn(responseList);

        mockMvc.perform(post("/product/bulkproducts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sku").value("SKU123"))
                .andExpect(jsonPath("$[0].name").value("Test Product"));
    }

    @Test
    public void listProducts_Success() throws Exception {
        Productresponsedto responseDto = new Productresponsedto();
        responseDto.setSku("SKU123");
        List<Productresponsedto> responseList = Arrays.asList(responseDto);

        when(productService.list(anyInt(), anyInt())).thenReturn(responseList);

        mockMvc.perform(get("/product")
                .param("page", "1")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sku").value("SKU123"));
    }

    @Test
    public void searchProducts_Success() throws Exception {
        Productresponsedto responseDto = new Productresponsedto();
        responseDto.setSku("SKU123");
        List<Productresponsedto> responseList = Arrays.asList(responseDto);

        when(productService.search(anyString(), anyInt(), anyInt())).thenReturn(responseList);

        mockMvc.perform(get("/product/search")
                .param("keyword", "test")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sku").value("SKU123"));
    }

    @Test
    public void getProductBySku_Success() throws Exception {
        Productresponsedto responseDto = new Productresponsedto();
        responseDto.setSku("SKU123");

        when(productService.getBySku("SKU123")).thenReturn(responseDto);

        mockMvc.perform(get("/product/SKU123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sku").value("SKU123"));
    }

    @Test
    public void getProductsByIds_Success() throws Exception {
        List<String> productIds = Arrays.asList("1", "2");
        Productresponsedto responseDto = new Productresponsedto();
        responseDto.setSku("SKU123");
        List<Productresponsedto> responseList = Arrays.asList(responseDto);

        when(productService.getProductsByIds(anyList())).thenReturn(responseList);

        mockMvc.perform(post("/product/get-products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productIds)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sku").value("SKU123"));
    }
}
