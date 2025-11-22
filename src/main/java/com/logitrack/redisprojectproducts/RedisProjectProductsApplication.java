package com.logitrack.redisprojectproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisProjectProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisProjectProductsApplication.class, args);
    }

}
