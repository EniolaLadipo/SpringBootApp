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
public class HomeController {

    @Autowired
    private AccountService accountService;
    
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("account", new Account());
        return "login";
    }


    //Registration endpoint
    @PostMapping("/register")
    public String register(Account account, HttpSession session){

        //Saves user details into database
        accountService.registerAccount(account);

        //Adding account object to session store
        session.setAttribute("account", account);

        //Directs to success endpoint
        return "redirect:/success";
    }    

    //Success endpoint
    @GetMapping("/success")
    public String showSuccessPage(Model model, HttpSession session){
        
        //Takes account details from session
        Account account = (Account) session.getAttribute("account");

        model.addAttribute("account", account);

        return "success";
    }
}
