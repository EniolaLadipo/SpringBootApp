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
    
    //Presents the login page where user's enter their credentials
    @GetMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("account", new Account());
        return "login";
    }

    //Receives user credentials from login page and queries the database to see if user data is present
    @PostMapping("/login")
    public String verify(Account account, Model model, HttpSession session){
        Optional<Account> accountOptional = accountService.findAccount(account.getUserName(), account.getPassword());

        //The user data was found in the database
        if(accountOptional.isPresent()){
            Account currentAccount = accountOptional.get();
            session.setAttribute("account", currentAccount);

            //Makes a GET request to the PostController
            //Sends the user the blog posts page
            return "redirect:/posts";

        } else {
            //User data was not found with the given credentials
            //An error message appears on the login page
            model.addAttribute("errorMessage", "Invalid username or password, please try again");
            return "login";
        }
    }


    //Test function to see if data could be collected from the session
    @GetMapping("/successLogin")
    public String showSuccessLogin(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("account", account);
        return "successLogin";
    }

    //Logs out the user and sends them back to the starting home page
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
