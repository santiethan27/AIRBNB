/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.requests;

import com.airbnb.airbnb.entities.City;
import com.airbnb.airbnb.entities.Country;
import com.airbnb.airbnb.enums.PropertyTypes;
import com.airbnb.airbnb.enums.States;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {
    private String owner;
    private String title;
    private States state;
    private String description;
    private Double size;
    private String address;
    private Integer rating;
    private String postalCode;
    private String propertyTypes;
    private String country;
    private Integer city;
    private String priceTypes;
    private List<MultipartFile> images;
    private BigDecimal price;
}
