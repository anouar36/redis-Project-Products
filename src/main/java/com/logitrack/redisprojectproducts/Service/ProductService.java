package com.logitrack.redisprojectproducts.Service;

import com.logitrack.redisprojectproducts.Entity.Product;
import com.logitrack.redisprojectproducts.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Save (Create)
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Get All
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        System.out.println("Fetching from Database... (Not Redis)");
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @CacheEvict(value = "products", key = "#id")
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}