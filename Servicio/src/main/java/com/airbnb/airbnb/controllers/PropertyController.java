package com.airbnb.airbnb.controllers;

import com.airbnb.airbnb.entities.Property;
import com.airbnb.airbnb.requests.PropertyRequest;
import com.airbnb.airbnb.servicies.PropertyService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private CloudinaryController cloudinaryController;

    @PostMapping("/register")
    public ResponseEntity<?> registerProperty(@ModelAttribute PropertyRequest request) throws Exception {

        try {
            if (request.getOwner() == null || request.getOwner().isEmpty()
                    || request.getDescription() == null || request.getDescription().isEmpty()
                    || request.getRating() == null
                    || request.getPostalCode() == null || request.getPostalCode().isEmpty()
                    || request.getPropertyTypes() == null
                    || request.getSize() == null || request.getImages() == null
                    || request.getCountry() == null || request.getCountry().isEmpty()
                    || request.getCity() == null
                    || request.getPrice() == null
                    || request.getPriceTypes() == null || request.getTitle() == null) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("error", request));

            }
            List<String> uploadedUrls = cloudinaryController.upload(request.getImages());

            propertyService.createProperty(request.getOwner(), uploadedUrls, request.getDescription(), request.getSize(), request.getAddress(), request.getRating(), request.getPostalCode(), request.getPropertyTypes(), request.getCountry(), request.getCity(), request.getPriceTypes(), request.getTitle(), request.getPrice());
            return ResponseEntity.ok("Propiedad registrada exitosamente.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable String id, @ModelAttribute PropertyRequest request) throws Exception {
        try {
            propertyService.updateProperty(id, request);
            return ResponseEntity.ok("Propiedad actualizada con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable String id) throws Exception {
        try {
            propertyService.deleteProperty(id);
            return ResponseEntity.ok("Propiedad eliminada con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Property>> getAllProperty() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getProperty(@PathVariable String propertyId) {
        Property property = propertyService.getPropertyById(propertyId);
        if (property != null) {
            return ResponseEntity.ok(property);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
