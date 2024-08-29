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
    
    //Gets all the posts in the database and displayes them on the post page
    @GetMapping("/posts")
    public String showPosts(Model model){
        List<Post> posts = postService.getAllPosts();

        model.addAttribute("posts", posts);
        return "posts";
    }

    //Sends a page for the user to create a post
    @GetMapping("/post/create")
    public String postCreationPage(Model model){
        model.addAttribute("post", new Post());

        return "createPost";
    }

    //Receives data from the "createPost" page and saves it into the database
    @PostMapping("/post/create")
    public String createPost(Post post, HttpSession session){

        //Collects the username from the session
        Account currentAccount = (Account) session.getAttribute("account");
        String creatorName = currentAccount.getUserName();

        post.setCreator(creatorName);

        //Takes the current date and time
        post.setTimeCreated(LocalDateTime.now());

        //Formats date in a more user-friendly format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma, dd/MM/yyyy");
        post.setFormattedDate(post.getTimeCreated().format(formatter));

        post.setAccountId(currentAccount);

        //Post is saved to the database
        postService.createPost(post);

        //User is sent back to the posts page
        return "redirect:/posts";
    }
}
