package com.cloud.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * client配置
 * @author yrz
 */
@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableDiscoveryClient
@RefreshScope
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Value("${democonfigclient.message}")
    private String foo;

    @RequestMapping("/hi")
    public String index(){
        return foo;
    }

}
