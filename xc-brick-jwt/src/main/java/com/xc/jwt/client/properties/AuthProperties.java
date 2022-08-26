package com.xc.jwt.client.properties;

import com.xc.jwt.client.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @ClassName: AuthClientProperties
 * @description: todo
 * @Author: Chao Xu
 * @Date: 2022/8/21 17:35
 **/

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class AuthProperties {
    private TokenInfo tokenInfo;

}