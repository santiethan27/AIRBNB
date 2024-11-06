/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.servicies;

import com.airbnb.airbnb.entities.Blog;
import com.airbnb.airbnb.entities.User;
import com.airbnb.airbnb.repositories.BlogRepository;
import com.airbnb.airbnb.repositories.UserRepository;
import com.airbnb.airbnb.requests.BlogRequest;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Katerine
 */
@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createBlog(String title, String description, List<String> images, String user) throws Exception {
        Blog blog = new Blog();
        Optional<User> optionalUser = userRepository.findById(user);
        if (optionalUser.isPresent()) {
            blog.setUser(optionalUser.get());
        } else {
            throw new IllegalArgumentException("Usuario  no encontrado");
        }
        blog.setTitle(title);
        blog.setDescription(description);
        blog.setImages(images);
        blogRepository.save(blog);
    }

    @Transactional
    public void updateBlog(String id, BlogRequest request) throws Exception {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar con el id" + id));

        if (request.getUser() != null) {
            Optional<User> optionalUser = userRepository.findById(request.getUser());
            if (optionalUser.isPresent()) {
                blog.setUser(optionalUser.get());
            } else {
                throw new IllegalArgumentException("Usuario  no encontrado");
            }
        }
        if (request.getTitle() != null) {
            blog.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            blog.setDescription(request.getDescription());
        }

        blogRepository.save(blog);

    }

    @Transactional
    public void deleteComment(String id) throws Exception {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar con el id" + id));
        blogRepository.delete(blog);

    }
    
    @Transactional
    public List<Blog> getAllBlog(){
        return blogRepository.findAll();
    }
    
}
