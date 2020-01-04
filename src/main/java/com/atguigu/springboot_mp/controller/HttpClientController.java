package com.atguigu.springboot_mp.controller;

import com.atguigu.springboot_mp.service.HttpAPIService;
import com.atguigu.springboot_mp.service.impl.HttpAPIServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HttpClientController {

    @Resource
    private HttpAPIServiceImpl httpAPIService;

    @RequestMapping("httpclient")
    public String test() throws Exception {
        String str = httpAPIService.doGet("http://www.baidu.com");
        System.out.println(str);
        return "hello";
    }
}
