package com.berkay.ecommerce_management.repository;

import com.berkay.ecommerce_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsBySku(String name);

}
