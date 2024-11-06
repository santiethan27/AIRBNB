/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.entities;

import com.airbnb.airbnb.enums.PriceTypes;
import com.airbnb.airbnb.enums.PropertyTypes;
import com.airbnb.airbnb.enums.States;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Property {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")    
    private String id;
    
    @OneToOne()
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User owner;
    
    @Enumerated(EnumType.STRING)
    private States state;
    
    @ElementCollection
    @Lob
    private List<String> images;
    private String title;
    private String description;
    
    private double size;
    
    /*@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id",referencedColumnName="id")*/
    
    private String adress;
    
    private int rating;
    
    private String postalCode;
    
    @Enumerated(EnumType.STRING)
    private PropertyTypes propertyTypes;
    
    @OneToOne()
    @JoinColumn(name="pais_nombre", referencedColumnName="pais_codigo")
    private Country country;
    
    @OneToOne()
    @JoinColumn(name="ciudad_nombre", referencedColumnName="ciudad_id")
    private City city;
    
    @Enumerated(EnumType.STRING)
    private PriceTypes priceType;
    
    private BigDecimal price;
}
