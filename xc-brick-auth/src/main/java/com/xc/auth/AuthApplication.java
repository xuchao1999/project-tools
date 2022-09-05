package com.xc.auth;

import com.xc.jwt.annotation.EnableAuthClient;
import com.xc.swagger2.EnableBrickSwagger;
import com.xc.user.annotation.EnableLoginArgResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableBrickSwagger
@EnableLoginArgResolver
@EnableAuthClient
@Slf4j
public class AuthApplication {
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
