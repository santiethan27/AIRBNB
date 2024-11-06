/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.controllers;


import com.airbnb.airbnb.entities.City;
import com.airbnb.airbnb.servicies.CityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Katerine
 */
@RestController
@RequestMapping("/city")
@Controller
public class CityController {
    @Autowired
    private CityService cityService;
    
    @GetMapping("/list/{countryCode}")
    public List<String[]> countries(@PathVariable String countryCode) {
        return cityService.getCities(countryCode);
    }
}
