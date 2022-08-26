package com.xc.gateway;

import com.xc.jwt.annotation.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述： *
 *
 * @author xc
 * @date 20220826 08:07:15
 * @since v1.0
 */
@SpringBootApplication
@EnableAuthClient
@EnableDiscoveryClient
@EnableConfigurationProperties
public class GatewayApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @author xc
     * @date 20220826 08:07:15
     * @since v1.0
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
