package com.example.lock.demo.controller;

import com.example.lock.demo.entity.Product;
import com.example.lock.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/old")
    public Product getOld() {
        Product omeOld = productService.getOneOld();
        log.info(String.valueOf(omeOld.getId()));
        return omeOld;
    }

    @GetMapping("/product/{id}")
    public Product getById(@PathVariable Long id) {
        Product omeOld = productService.getOneByld(id);
        log.info(String.valueOf(omeOld.getId()));
        return omeOld;
    }

    @PostMapping("/add")
    public Product add(@RequestBody Product product) {
        Product addedProduct = productService.add(product);
        return addedProduct;
    }
}
