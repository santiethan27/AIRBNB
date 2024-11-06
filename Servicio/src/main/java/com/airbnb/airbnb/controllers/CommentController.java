/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.controllers;

import com.airbnb.airbnb.entities.Comment;
import com.airbnb.airbnb.requests.CommentRequest;
import com.airbnb.airbnb.servicies.CommentService;  
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@Controller

public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CloudinaryController cloudinaryController;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerComment(@ModelAttribute CommentRequest request) throws Exception {
        try {
            if (request.getUser() == null || request.getUser().isEmpty()
                    || request.getProperty() == null || request.getProperty().isEmpty()
                    || request.getTitle() == null || request.getTitle().isEmpty()
                    || request.getDescription() == null || request.getDescription().isEmpty()
                    || request.getQualification() == null || request.getDescription().isEmpty()
                    || request.getImages() == null) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("error", request));
            }
            List<String> uploadedUrls = cloudinaryController.upload(request.getImages());

            commentService.createComments(request.getUser(), request.getProperty(), request.getTitle(), request.getDescription(), uploadedUrls, request.getQualification());
            return ResponseEntity.ok("Comentario registrado exitosamente.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", request));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable String id, @ModelAttribute CommentRequest request) throws Exception {
        try {
            commentService.updateComments(id, request);
            return ResponseEntity.ok("Comentario actualizado conexito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id) throws Exception {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.ok("Comnetario eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/{property}")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable String property) {
        List<Comment> comments = commentService.findByPropertyId(property);
        return ResponseEntity.ok(comments);
    }
    
}
