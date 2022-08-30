package com.xc.auth.interfaces.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Test controller.
 *
 * @author xuchao
 * @date 2022 -07-19 10:24:02
 */
@RestController
@Slf4j
@Api(tags = "测试控制器", value = "TestController")
public class TestController {

    /**
     * Test string.
     *
     * @param str the str
     * @return the string
     */
    @GetMapping(value = "/nacos/{str}")
    @ApiOperation(value = "测试方法")
    public String test(@PathVariable String str) {
        return "port: 8801 " + str;
    }
}
