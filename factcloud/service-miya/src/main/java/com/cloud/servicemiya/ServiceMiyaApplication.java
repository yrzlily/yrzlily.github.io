package com.cloud.servicemiya;

import brave.sampler.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;

/**
 * @author yrz
 */
@SpringBootApplication
@RestController
public class ServiceMiyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMiyaApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(ServiceMiyaApplication.class);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String callHome(){
        log.info( "{} calling trace miya" , Level.INFO);
        return restTemplate.getForObject("http://localhost:8988/info", String.class);
    }

    @RequestMapping("/miya")
    public String info(){
        log.info( "{} calling trace service-hi" , Level.INFO);
        return "i'm miya";
    }

    @Bean
    public Sampler sampler(){
        return Sampler.ALWAYS_SAMPLE;
    }

}
