package com.example.lock.demo.repository;

import com.example.lock.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "Select * from products where status = ?1 order by count asc LIMIT 1 for update of products skip locked", nativeQuery = true)
    Product findTop1ByStatusOrderByCountAsc(String status);
}
