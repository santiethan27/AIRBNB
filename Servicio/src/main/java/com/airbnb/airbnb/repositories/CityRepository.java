/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.repositories;

import com.airbnb.airbnb.entities.City;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Katerine
 */
@Repository
public interface  CityRepository extends JpaRepository <City, Integer>{
    @Query(value = "SELECT pais_codigo, ciudad_nombre FROM city WHERE pais_codigo = :countryCode", nativeQuery = true)
    List<String[]> getCities(@Param("countryCode") String countryCode);  
}
