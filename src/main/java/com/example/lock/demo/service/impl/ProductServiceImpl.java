package com.example.lock.demo.service.impl;

import com.example.lock.demo.entity.Product;
import com.example.lock.demo.repository.ProductsRepository;
import com.example.lock.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Component
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Product getOneByld(long id) {
        return productsRepository.getOne(id);
    }

    @Override
    public Product add(Product product) {
        product.setExpirationDate(new Date());
        product.setStatus("NEW");
        product.setDescription("hz");
        return productsRepository.save(product);
    }

    @Override
    @Transactional
    public Product getOneOld() {
        Product aNew = productsRepository.findTop1ByStatusOrderByCountAsc("NEW");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return aNew;
    }

    @Override
    @Transactional
    public void save(Product product) {
        productsRepository.save(product);
    }
}
