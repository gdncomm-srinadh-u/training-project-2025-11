package com.major.cloudGateway.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "customer-microservice", url = "http://localhost:8081")
public interface CustomerClient {

    @GetMapping("/auth/validate")
    String validateToken(String token);
}
