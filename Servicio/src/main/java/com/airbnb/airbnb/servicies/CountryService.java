/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.servicies;

import com.airbnb.airbnb.entities.Country;
import com.airbnb.airbnb.repositories.CountryRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Katerine
 */
 @Service
public class CountryService {
    @Autowired 
    private CountryRepository countryRepository;
          @Transactional
           public List <Country> getCountry(){
           return countryRepository.findAll();
     }
    }

