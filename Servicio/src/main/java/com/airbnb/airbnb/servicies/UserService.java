/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.servicies;

import com.airbnb.airbnb.auth.AuthResponse;
import com.airbnb.airbnb.entities.Country;
import com.airbnb.airbnb.entities.User;
import com.airbnb.airbnb.enums.Rol;
import com.airbnb.airbnb.jwt.JwtService;
import com.airbnb.airbnb.repositories.CountryRepository;
import com.airbnb.airbnb.repositories.UserRepository;
import com.airbnb.airbnb.requests.UserRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//duque
import com.airbnb.airbnb.servicies.EmailServiceImpl; 

/**
 *
 * @author Usuario
 */
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CountryRepository countryRepository;
    
    //duque
    @Autowired
    private EmailServiceImpl emailService;
    
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse registerUser(String first_name, String last_name, String email, String password, String phone, String Country, byte[] photo, Date birthDate) throws Exception {
        try {
            User user = new User();
            user.setFirst_name(first_name);
            user.setLast_name(last_name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setPhone(phone);
            Optional<Country> optionalCountry = countryRepository.findById(Country);
            if (optionalCountry.isPresent()) {
                user.setCountry(optionalCountry.get());
            } else {
                throw new IllegalArgumentException("Pais no encontrado");
            }
            user.setPhoto(photo);
            user.setBirthdate(birthDate);
            user.setRol(Rol.USER);
            userRepository.save(user);

            //duque
            String subject = "¡Bienvenido a nuestro sitio!";
            String message = "¡Hola " + first_name + " " + last_name + "!\n\nBienvenido a nuestro sitio. Gracias por registrarte.";
            String[] toUser = {email};
            emailService.sendEmail(toUser, subject, message);
             //duque
            
            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .user(user)
                    .build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public AuthResponse loginUser(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserDetails userDetails = userRepository.findByEmail(email).orElseThrow();
            User user = userRepository.findByEmail(email).orElseThrow();
            String token = jwtService.getToken(userDetails);
            return AuthResponse.builder()
                    .token(token)
                    .user(user)
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException("Correo o contraseña incorrecta");
        }
    }

    public void updateUser(String userId, UserRequest request) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (request.getFirstName() != null) {
                user.setFirst_name(request.getFirstName());
            }
            if (request.getLastName() != null) {
                user.setLast_name(request.getLastName());
            }
            if (request.getEmail() != null) {
                user.setEmail(request.getEmail());
            }
            if (request.getPassword() != null) {
                user.setPassword(request.getPassword());
            }
            if (request.getPhone() != null) {
                user.setPhone(request.getPhone());
            }
            if (request.getCountry() != null) {
                Optional<Country> optionalCountry = countryRepository.findById(request.getCountry());
                if (optionalCountry.isPresent()) {
                    user.setCountry(optionalCountry.get());
                } else {
                    throw new IllegalArgumentException("Pais no encontrado");
                }
            }
            if (request.getPhoto() != null) {
                byte[] photo = request.getPhoto().getBytes();
                user.setPhoto(photo);
            }
            if (request.getBirthDate() != null) {
                user.setBirthdate(request.getBirthDate());
            }
            if (request.getRol() != null) {
                user.setRol(request.getRol());
            }

            userRepository.save(user);
        } else {

        }
    }

    public AuthResponse verify(String token) {
        String userName = jwtService.getUsernameFromToken(token);
        User user = userRepository.findByEmail(userName).orElseThrow();
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .user(user)
                .build();
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public User getUserById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
