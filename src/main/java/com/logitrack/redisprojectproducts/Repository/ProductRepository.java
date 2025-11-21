package com.logitrack.redisprojectproducts.Repository;

import com.logitrack.redisprojectproducts.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
