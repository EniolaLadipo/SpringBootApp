package com.example.blog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;

    //Getters

    public String getFirstName(){
        return firstname;
    }

    public String getLasttName(){
        return lastname;
    }

    public String getUserName(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    //Setters

    public void setFirstName(String firstname){
        this.firstname = firstname;
    }

    public void setLastName(String lastname){
        this.lastname = lastname;
    }

    public void setUserName(String username){
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
