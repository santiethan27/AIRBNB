/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.requests;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Katerine
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CommentRequest {
    private String user;
    private String property;
    private String title;
    private String description;
    private List<MultipartFile> images;
    private String qualification;
}
