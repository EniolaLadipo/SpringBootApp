package com.example.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.models.Account;
import com.example.blog.repositories.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(String firstname, String lastname, String username, String email, String password){
        Account account = new Account();

        account.setFirstName(firstname);
        account.setLastName(lastname);
        account.setEmail(email);
        account.setUserName(username);
        account.setPassword(password);

        return accountRepository.save(account);
    }
}
