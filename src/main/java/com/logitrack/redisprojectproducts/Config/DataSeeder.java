package com.logitrack.redisprojectproducts.Config;

import com.logitrack.redisprojectproducts.Entity.Product;
import com.logitrack.redisprojectproducts.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Product("iPhone 15", 1200.0, "Apple Smartphone"));
                repository.save(new Product("Samsung S24", 1100.0, "Android Flagship"));
                repository.save(new Product("MacBook Pro", 2500.0, "Laptop for Devs"));
                System.out.println("Fake data inserted!");
            }
        };
    }
}