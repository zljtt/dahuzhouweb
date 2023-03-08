package com.zljtt.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${test.hello:default}")
    private String testHello;

    @GetMapping("/hello")
    public String Hello() {
        return "hello world";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "post " + name;
    }
}
