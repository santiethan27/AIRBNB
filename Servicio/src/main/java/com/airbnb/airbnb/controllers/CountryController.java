/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.controllers;

import com.airbnb.airbnb.entities.City;
import com.airbnb.airbnb.entities.Country;
import com.airbnb.airbnb.servicies.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Katerine
 */
@Controller
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @GetMapping ("/country")
    public ResponseEntity <List<Country>> getallCountries(){
       List<Country> country = countryService.getCountry();
        return ResponseEntity.ok(country);
    }
}
