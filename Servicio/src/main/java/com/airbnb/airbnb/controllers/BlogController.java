/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.controllers;

import com.airbnb.airbnb.entities.Blog;
import com.airbnb.airbnb.requests.BlogRequest;
import com.airbnb.airbnb.servicies.BlogService;
import com.airbnb.airbnb.servicies.CommentService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Katerine
 */
@RestController
@RequestMapping("/blog")
@Controller

public class BlogController {
     @Autowired
    private BlogService blogService;

    @Autowired
    private CloudinaryController cloudinaryController;
    
    @PostMapping("register")
    public ResponseEntity<?> registerBlog(@ModelAttribute BlogRequest request) throws Exception {
            try {
            if (request.getUser() == null || request.getUser().isEmpty()
                   || request.getTitle()== null || request.getTitle().isEmpty()
                    || request.getDescription()== null || request.getDescription().isEmpty()
                    || request.getImages() == null) {
                
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", request));
            }
            List<String> uploadedUrls = cloudinaryController.upload(request.getImages());

            blogService.createBlog(request.getTitle(), request.getDescription(), uploadedUrls, request.getUser());
            return ResponseEntity.ok("Blog  registrado exitosamente.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", request));
        }
    }
    
    @GetMapping("/getblog")
    public ResponseEntity<List<Blog>> getAllBlogs(){
       List<Blog> blog = blogService.getAllBlog();
       return ResponseEntity.ok(blog); 
    }
}
