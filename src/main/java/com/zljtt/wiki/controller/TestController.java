package com.zljtt.wiki.controller;

import com.zljtt.wiki.domain.Test;
import com.zljtt.wiki.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/hello")
    public String Hello() {
        return "hello world";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "post " + name;
    }

    @GetMapping("/test/list")
    public List<Test> list() {
        return testService.list();
    }
}
