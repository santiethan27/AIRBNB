//Dev Duque
package com.airbnb.airbnb.controllers;

import com.airbnb.airbnb.repositories.IEmailService;
import com.airbnb.airbnb.requests.EmailDTO;
import com.airbnb.airbnb.requests.EmailFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendemail")
public class MailController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/sendmessage")
    public ResponseEntity<?> recesiveRequestEmail(@RequestBody EmailDTO emailDTO) {
        
        System.out.println("Mensaje Recibido" + emailDTO);
        emailService.sendEmail(emailDTO.getToUser(), emailDTO.getSubject(), emailDTO.getMessage());
        Map<String, String> answer = new HashMap<>();
        answer.put("Estado", "Enviado");

        return ResponseEntity.ok(answer);
    }

    @PostMapping("/sendmessagefile")
    public ResponseEntity<?> recesiveRequestEmailWithFile(@ModelAttribute EmailFile emailFile) {
        try {
            String fileName = emailFile.getFile().getOriginalFilename();
            Path path = Paths.get("src/main/resources/files" + fileName);

            Files.createDirectories(path.getParent());
            Files.copy(emailFile.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            File file = path.toFile();

            emailService.sendEmailWithFile(emailFile.getToUser(), emailFile.getSubject(), emailFile.getMessage(), file);
            Map<String, String> answerr = new HashMap<>();
            answerr.put("Estado", "Enviado");
            answerr.put("Archivo", fileName);
            
            return ResponseEntity.ok(answerr);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar correo con archivo" + e.getMessage());
        }
    }
    
    
}
