/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.controllers;

import com.airbnb.airbnb.auth.AuthResponse;
import com.airbnb.airbnb.entities.User;
import com.airbnb.airbnb.requests.ForgotPasswordRequest;
import com.airbnb.airbnb.requests.UserRequest;
import com.airbnb.airbnb.servicies.EmailServiceImpl;

import com.airbnb.airbnb.servicies.UserService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //duque
    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@ModelAttribute UserRequest request, @RequestPart("photo") MultipartFile photo) {
        try {
            if (request.getFirstName() == null || request.getFirstName().isEmpty()
                    || request.getLastName() == null || request.getLastName().isEmpty()
                    || request.getEmail() == null || request.getEmail().isEmpty()
                    || request.getPassword() == null || request.getPassword().isEmpty()
                    || request.getPhone() == null || request.getPhone().isEmpty()
                    || request.getCountry() == null || request.getCountry().isEmpty()
                    || request.getBirthDate() == null) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Los datos no son correctos."));
            }

            byte[] photoBytes = photo.getBytes();

            AuthResponse response = userService.registerUser(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), request.getPhone(), request.getCountry(), photoBytes, request.getBirthDate());

            String subject = "¡Bienvenido a nuestro sitio!";
            String message = "¡Hola " + request.getFirstName() + " " + request.getLastName() + "!\n\nBienvenido a nuestro sitio. Gracias por registrarte.";
            String[] toUser = {request.getEmail()};
            emailService.sendEmail(toUser, subject, message);

            return ResponseEntity.ok(response);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "El correo ya esta en uso"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@ModelAttribute UserRequest request) {
        try {
            if (request.getEmail() == null || request.getEmail().isEmpty()
                    || request.getPassword() == null || request.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Falta ingresar el email o la contraseña"));
            }
            AuthResponse response = userService.loginUser(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    //DUQUE
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            if (request.getEmail() == null || request.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Falta ingresar el email"));
            }

            emailService.sendPasswordResetEmail(request.getEmail());
            return ResponseEntity.ok(Collections.singletonMap("message", "Se ha enviado un correo electrónico con instrucciones para restablecer la contraseña."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }//DUQUE

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @ModelAttribute UserRequest request) {
        try {
            userService.updateUser(userId, request);
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "verify")
    public ResponseEntity<?> verify(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        try {
            String token = authorizationHeader.substring(7);
            AuthResponse response = userService.verify(token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
