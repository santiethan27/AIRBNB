/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.controllers;

import com.airbnb.airbnb.servicies.CloudinaryService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/cloud")
public class CloudinaryController {

    @Autowired
    CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public @ResponseBody
    List<String> upload(@RequestParam("file") List<MultipartFile> files) {
        List<String> uploadedFileUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                String uploadedUrl = cloudinaryService.upload(file);
                uploadedFileUrls.add(uploadedUrl);
            } catch (IOException e) {
                // Manejo de errores de subida
                e.printStackTrace();
                // Podrías lanzar una excepción personalizada aquí o simplemente continuar con el siguiente archivo
            }
        }
        return uploadedFileUrls;
    }

    @PostMapping("/upload-single")
    public @ResponseBody
    String uploadSingle(@RequestParam("file") MultipartFile file) {
        String uploadedUrl = new String();
        try {
            uploadedUrl = cloudinaryService.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadedUrl;
    }
}
