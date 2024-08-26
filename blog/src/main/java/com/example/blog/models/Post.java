package com.example.blog.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String body;
    
    private String creator;
    private LocalDateTime timeCreated;


    //Holds the user-friendly format of the date which will be updated in the PostController
    private String formattedDate;

    //Getters

    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    public String getCreator(){
        return creator;
    }

    public LocalDateTime getTimeCreated(){
        return timeCreated;
    }

    public String getFormattedDate(){
        return formattedDate;
    }


    //Setters

    public void setTitle(String title){
        this.title = title;
    }

    public void setBody(String body){
        this.body = body;
    }

    public void setCreator(String creator){
        this.creator = creator;
    }

    public void setTimeCreated(LocalDateTime timeCreated){
        this.timeCreated = timeCreated;
    }

    public void setFormattedDate(String formattedDate){
        this.formattedDate = formattedDate;
    }
}