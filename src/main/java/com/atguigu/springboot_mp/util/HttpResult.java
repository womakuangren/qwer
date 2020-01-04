package com.atguigu.springboot_mp.util;


import lombok.Data;

@Data
public class HttpResult {

// 响应的状态码

    private int code;

// 响应的响应体

    private String body;

    public HttpResult(int statusCode, String toString) {
        this.body = toString;
        this.code = statusCode;
    }
}
