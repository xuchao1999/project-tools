package com.xc.testapp;

import com.xc.jwt.annotation.EnableAuthClient;
import com.xc.user.annotation.EnableLoginArgResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthClient
@EnableLoginArgResolver
public class TestappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestappApplication.class, args);
    }

}
