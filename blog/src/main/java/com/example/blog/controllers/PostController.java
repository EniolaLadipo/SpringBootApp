package com.example.blog.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.models.Account;
import com.example.blog.models.Post;
import com.example.blog.services.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    
    @GetMapping("/posts")
    public String showPosts(Model model){
        List<Post> posts = postService.getAllPosts();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma, dd/MM/yyyy");

        for(Post post: posts){
            post.setFormattedDate(post.getTimeCreated().format(formatter));
        }

        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/post/create")
    public String postCreationPage(Model model){
        model.addAttribute("post", new Post());

        return "createPost";
    }

    @PostMapping("/post/create")
    public String createPost(Post post, HttpSession session){
        Account currentAccount = (Account) session.getAttribute("account");
        String creatorName = currentAccount.getUserName();

        post.setCreator(creatorName);
        post.setTimeCreated(LocalDateTime.now());

        postService.createPost(post);

        return "redirect:/posts";
    }
}
