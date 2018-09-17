package com.example.lock.demo.service;

import com.example.lock.demo.entity.Product;

public interface ProductService {

    Product getOneByld(long id);

    Product add(Product product);

    Product getOneOld();

    void save(Product product);
}
