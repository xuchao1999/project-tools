package com.xc.testapp.controller;

import com.xc.jwt.client.entity.JwtUserInfo;
import com.xc.jwt.client.entity.Token;
import com.xc.jwt.client.service.JwtTokenService;
import com.xc.user.annotation.LoginUser;
import com.xc.user.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "测试控制器", value = "TestController")
public class TestController {
    @Resource
    private JwtTokenService tokenService;
    @GetMapping("/test")
    @ApiOperation(notes = "测试", value = "测试")
    @ApiImplicitParam(name = "参数1", value = "t")
    public String test(@RequestParam String t, @LoginUser SysUser user){
        System.out.println(t);
        System.out.println(user);
        return t;
    }

    @GetMapping("/getToken")
    public String getToken(){
        JwtUserInfo jwtUserInfo = new JwtUserInfo(2L, "账号", "张三", "123456", 3L, 4L);
        Token token = tokenService.generateUserToken(jwtUserInfo, 6000);
        return token.getToken();
    }
}
