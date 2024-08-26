package com.example.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//Home Controller

@Controller
public class HomeController {
    
    //Starting page - Asks the user to either login or register
    @GetMapping("/")
    public String showHome(){
        return "home";
    }

}
