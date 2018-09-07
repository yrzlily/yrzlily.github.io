package com.way.fact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author yrz
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
public class FactApplication {
    public static void main(String[] args) {
        SpringApplication.run(FactApplication.class, args);
    }
}
