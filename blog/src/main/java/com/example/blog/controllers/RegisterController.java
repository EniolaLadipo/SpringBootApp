package com.example.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.models.Account;
import com.example.blog.services.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    private AccountService accountService;

    //Sends user the registration page where they can enter their credentials
    @GetMapping("/registration")
    public String showLogin(Model model){
        model.addAttribute("account", new Account());
        return "registration";
    }

    //Receives user data from the registration page
    @PostMapping("/register")
    public String showRegister(Account account, HttpSession session){

        //Saves user details into database
        accountService.registerAccount(account);

        //Adding account object to session store
        session.setAttribute("account", account);

        //Sends user to the posts page
        return "redirect:/posts";
    }

    //Tester function - shows that user data was successfully added to the session
    @GetMapping("/success")
    public String showSuccessPage(Model model, HttpSession session){
        
        //Takes account details from session
        Account account = (Account) session.getAttribute("account");

        model.addAttribute("account", account);

        return "successRegister";
    }
}
