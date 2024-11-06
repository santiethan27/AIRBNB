//Dev Duque
package com.airbnb.airbnb.controllers;

import com.airbnb.airbnb.entities.ServiceProperty;
import com.airbnb.airbnb.requests.ServicePropertyRequest;
import com.airbnb.airbnb.servicies.ServicePropertyService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serviceproperty")
public class ServicePropertyController {
    
    @Autowired
    ServicePropertyService ServicePropertyService;
    
    
    @PostMapping("/create_ServiceProperty")
    public ResponseEntity<?> registerServiceProperty(@ModelAttribute ServicePropertyRequest request)  throws Exception{
        try{
            
            if(request.getName() == null || request.getName().isEmpty()  
                || request.getProperty() == null || request.getProperty().isEmpty()
            ){
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", request));
            }
        ServicePropertyService.createServiceProperty(request.getName(), request.getProperty());
        return ResponseEntity.ok("Servicio registrado exitosamente.");
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", request));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @ModelAttribute ServicePropertyRequest request) {
        try {
            ServicePropertyService.updatedServiceProperty(id, request);
            return ResponseEntity.ok("Servicio actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    
    @DeleteMapping("/{servicePropertyId}")
    public ResponseEntity<?> deleteUser(@PathVariable String servicePropertyId) {
        try {
            ServicePropertyService.deleteServiceProperty(servicePropertyId);
            return ResponseEntity.ok("Servicio eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    
    @GetMapping("/{property}")
    public ResponseEntity<List<ServiceProperty>> getAllServiceProperty(@PathVariable String property) {
        List<ServiceProperty> serviceProperty = ServicePropertyService.findByPropertyId(property);
        return ResponseEntity.ok(serviceProperty);
    }
}


