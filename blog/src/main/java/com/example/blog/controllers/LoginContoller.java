package com.example.blog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginContoller {
    
    @GetMapping("/hello")
    public String hello(){
        return "Hello world";
    }
}
