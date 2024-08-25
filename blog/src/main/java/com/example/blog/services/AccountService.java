package com.example.blog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.models.Account;
import com.example.blog.repositories.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    public Account registerAccount(Account account){
        return accountRepository.save(account);
    }

    public Optional<Account> findAccount(String username, String password){
        return accountRepository.findByUsernameAndPassword(username, password);
    }
}
