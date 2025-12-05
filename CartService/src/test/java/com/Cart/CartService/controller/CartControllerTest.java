package com.Cart.CartService.controller;

import com.Cart.CartService.dto.ProductResponsesDTO;
import com.Cart.CartService.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CartController.class, excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration.class,
        org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration.class
})
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private com.Cart.CartService.repository.CartRepository cartRepository;

    @Test
    public void testAddToCart() throws Exception {
        String userEmail = "test@example.com";
        String productId = "prod123";
        String expectedResponse = "Item added to cart";

        given(cartService.addToCart(userEmail, productId)).willReturn(expectedResponse);

        mockMvc.perform(post("/cart/add/{userEmail}/{productId}", userEmail, productId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

        verify(cartService).addToCart(userEmail, productId);
    }

    @Test
    public void testGetCart() throws Exception {
        String userEmail = "test@example.com";
        ProductResponsesDTO item1 = new ProductResponsesDTO();
        item1.setSku("sku1");
        item1.setName("Product 1");
        item1.setPrice(10.0);

        ProductResponsesDTO item2 = new ProductResponsesDTO();
        item2.setSku("sku2");
        item2.setName("Product 2");
        item2.setPrice(20.0);

        List<ProductResponsesDTO> cartItems = Arrays.asList(item1, item2);

        given(cartService.getCart(userEmail)).willReturn(cartItems);

        mockMvc.perform(get("/cart/{userEmail}", userEmail))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sku").value("sku1"))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[0].price").value(10.0))
                .andExpect(jsonPath("$[1].sku").value("sku2"))
                .andExpect(jsonPath("$[1].name").value("Product 2"))
                .andExpect(jsonPath("$[1].price").value(20.0));

        verify(cartService).getCart(userEmail);
    }

    @Test
    public void testRemoveFromCart() throws Exception {
        String userEmail = "test@example.com";
        String productId = "prod123";
        String expectedResponse = "Item removed from cart";

        given(cartService.removeFromCart(userEmail, productId)).willReturn(expectedResponse);

        mockMvc.perform(delete("/cart/remove/{userEmail}/{productId}", userEmail, productId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

        verify(cartService).removeFromCart(userEmail, productId);
    }

    @Test
    public void testClearCart() throws Exception {
        String userEmail = "test@example.com";
        String expectedResponse = "Cart cleared";

        given(cartService.clearCart(userEmail)).willReturn(expectedResponse);

        mockMvc.perform(delete("/cart/clear/{userEmail}", userEmail))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

        verify(cartService).clearCart(userEmail);
    }
}
