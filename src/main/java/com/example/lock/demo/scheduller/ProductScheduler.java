package com.example.lock.demo.scheduller;

import com.example.lock.demo.entity.Product;
import com.example.lock.demo.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class ProductScheduler {

    private final ProductService productService;

    public ProductScheduler(ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(fixedDelay = 3000L)
    public void init(){
        Product product = new Product();
        product.setName("Test"+ System.currentTimeMillis());
        product.setCount(new Random(1000).nextInt());
        product.setDescription("Test"+ System.currentTimeMillis());
        product.setExpirationDate(new Date());
        product.setStatus("NEW");
        productService.save(product);
    }
}
