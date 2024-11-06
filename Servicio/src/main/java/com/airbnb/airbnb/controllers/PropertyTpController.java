//Dev Duque
package com.airbnb.airbnb.controllers;

import com.airbnb.airbnb.entities.PropertyTypes;
import com.airbnb.airbnb.requests.PropertyTpRequest;
import com.airbnb.airbnb.servicies.PropertyTpService;
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
@RequestMapping("/property-type")
@Controller
public class PropertyTpController {

    @Autowired
    private CloudinaryController cloudinaryController;

    @Autowired
    private PropertyTpService propertyTpService;

    @PostMapping("/register-propertytype")
    public ResponseEntity<?> registerPropertyType(@ModelAttribute PropertyTpRequest request) throws Exception {
        try {
            if (request.getTitle() == null || request.getTitle().isEmpty()
                    || request.getImages() == null) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("error", request));
            }
            String uploadedUrls = cloudinaryController.uploadSingle(request.getImages());

            propertyTpService.createPropertyType(request.getTitle(), uploadedUrls);
            return ResponseEntity.ok("Tipo de priedad registrada exitosamente.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", request));
        }
    }
    
      @PutMapping("/{id}")
    public ResponseEntity<?> updatePropertyType(@PathVariable String id, @ModelAttribute PropertyTpRequest request) throws Exception {
        try {
            propertyTpService.updatedPropertyType(id, request);
            return ResponseEntity.ok("Tipo de propiedad actualizada con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    
     @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePropertyType(@PathVariable String id) throws Exception {
        try {
            propertyTpService.deletePropertyType(id);
            return ResponseEntity.ok("Tipo de propiedad eliminada con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    
    @GetMapping("/all-propertytype")
    public ResponseEntity<List<PropertyTypes>> getAllPropertyType() {
        List<PropertyTypes> propertiesType = propertyTpService.getAllPropertyType();
        return ResponseEntity.ok(propertiesType);
    }

}
