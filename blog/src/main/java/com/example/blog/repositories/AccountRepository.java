package com.example.blog.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
}
