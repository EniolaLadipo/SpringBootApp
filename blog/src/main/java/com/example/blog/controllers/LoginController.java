package com.example.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;
import com.example.blog.models.Account;
import com.example.blog.services.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AccountService accountService;
    
    @GetMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("account", new Account());
        return "login";
    }

    @PostMapping("/login")
    public String verify(Account account, Model model, HttpSession session){
        Optional<Account> accountOptional = accountService.findAccount(account.getUserName(), account.getPassword());

        if(accountOptional.isPresent()){
            Account currentAccount = accountOptional.get();
            session.setAttribute("account", currentAccount);

            return "redirect:/posts";

        } else {
            model.addAttribute("errorMessage", "Invalid username or password, please try again");
            return "login";
        }
    }

    @GetMapping("/successLogin")
    public String showSuccessLogin(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("account", account);
        return "successLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
