package com.logitrack.redisprojectproducts.Service;

import com.logitrack.redisprojectproducts.Entity.Product;
import com.logitrack.redisprojectproducts.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        System.out.println("🔴 (Database) Fetching product from PostgreSQL for ID: " + id);

        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @CacheEvict(value = "products", key = "#id")
    public Product updateProduct(Long id, Product productDetails) {
        System.out.println("🟡 (Update) Updating Database & Clearing Cache for ID: " + id);

        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        System.out.println("⚫ (Delete) Deleting from Database & Cache for ID: " + id);
        productRepository.deleteById(id);
    }
}