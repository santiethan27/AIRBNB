/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.servicies;


import com.airbnb.airbnb.entities.City;
import com.airbnb.airbnb.repositories.CityRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Katerine
 */
@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
        @Transactional
    public List<String[]> getCities(String countryCode) {
        return cityRepository.getCities(countryCode);
    }
}
