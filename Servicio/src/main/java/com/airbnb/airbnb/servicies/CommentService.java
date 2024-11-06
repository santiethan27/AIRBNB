/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.servicies;

import com.airbnb.airbnb.entities.Comment;
import com.airbnb.airbnb.entities.Property;
import com.airbnb.airbnb.entities.User;
import com.airbnb.airbnb.repositories.CommentRepository;
import com.airbnb.airbnb.repositories.RepositoryProperty;
import com.airbnb.airbnb.repositories.UserRepository;
import com.airbnb.airbnb.requests.CommentRequest;
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
public class CommentService {

    @Autowired
    private CommentRepository repositoryComment;
    @Autowired
    private RepositoryProperty repositoryProperty;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createComments(String user, String property, String title, String description, List<String> images, String qualification) throws Exception {

        Comment comment = new Comment();
        Optional<Property> optionalProperty = repositoryProperty.findById(property);
        if (optionalProperty.isPresent()) {
            comment.setProperty(optionalProperty.get());
        } else {
            throw new IllegalArgumentException("Propiedad  no encontrada");
        }
        Optional<User> optionalUser = userRepository.findById(user);
        if (optionalUser.isPresent()) {
            comment.setUser(optionalUser.get());
        } else {
            throw new IllegalArgumentException("Usuario  no encontrado");
        }
        comment.setTitle(title);
        comment.setDescription(description);
        comment.setImages(images);
        comment.setQualification(qualification);

        repositoryComment.save(comment);
    }

    @Transactional
    public void updateComments(String commentId, CommentRequest request) throws Exception {
        System.out.println("Entrando");

        Comment comment = repositoryComment.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar con el id" + commentId));

        if (request.getUser() != null) {
            Optional<User> optionalUser = userRepository.findById(request.getUser());
            if (optionalUser.isPresent()) {
                comment.setUser(optionalUser.get());
            } else {
                throw new IllegalArgumentException("Usuario  no encontrado");
            }
        }
        if (request.getProperty() != null) {
            Optional<Property> optionalProperty = repositoryProperty.findById(request.getProperty());
            if (optionalProperty.isPresent()) {
                comment.setProperty(optionalProperty.get());
            } else {
                throw new IllegalArgumentException("Usuario no encontrado");
            }
        }
        if (request.getTitle() != null) {
            comment.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            comment.setDescription(request.getDescription());
        }
        if (request.getQualification() != null) {
            comment.setQualification(request.getQualification());
        }
        repositoryComment.save(comment);

    }

    @Transactional
    public void deleteComment(String commentId) throws Exception {
        Comment comment = repositoryComment.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar con el id" + commentId));
        repositoryComment.delete(comment);

    }

    @Transactional
    public List<Comment> findByPropertyId(String property) {
        return repositoryComment.findByPropertyId(property);
    }
}
