package com.xc.auth;

import com.xc.jwt.annotation.EnableAuthClient;
import com.xc.swagger2.EnableBrickSwagger;
import com.xc.user.annotation.EnableLoginArgResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The type Nacosclient application.
 *
 * @author xuchao
 * @date 2022 -07-20 08:57:35
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableBrickSwagger
@EnableAuthClient
@EnableLoginArgResolver
@Slf4j
public class AuthApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(AuthApplication.class, args);

        ConfigurableEnvironment environment = context.getEnvironment();
        String appName = environment.getProperty("spring.application.name");
        String port = environment.getProperty("server.port");
        String hostAddress = InetAddress.getLocalHost().getHostAddress();

        //启动完成后在控制台提示项目启动成功，并且输出当前服务对应的swagger接口文档访问地址
        //http://localhost:8080/doc.html
        log.info("应用{}启动成功!swagger地址：http://{}:{}/doc.html",appName,hostAddress,port);
    }

}
