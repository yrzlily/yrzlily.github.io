package com.way.fact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author yrz
 */
@SpringBootApplication
@EnableCaching
public class FactApplication {
    public static void main(String[] args) {
        SpringApplication.run(FactApplication.class, args);
    }
}
