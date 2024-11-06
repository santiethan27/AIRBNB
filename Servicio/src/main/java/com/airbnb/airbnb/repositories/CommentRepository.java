/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.repositories;

import com.airbnb.airbnb.entities.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Katerine
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByPropertyId(String property);
}
