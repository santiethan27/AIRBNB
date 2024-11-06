/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "City")
public class City {

    @Id
    @Column(name = "ciudad_id")
    private int ciudadID;
    
    @Column(name = "ciudad_nombre", length = 35, nullable = false)
    private String ciudadNombre;
    
    @Column(name = "pais_codigo", length = 3, nullable = false)
    private String paisCodigo;
    
    @Column(name = "ciudad_distrito", length = 20, nullable = false)
    private String ciudadDistrito;
    
    @Column(name = "ciudad_poblacion", nullable = false)
    private int ciudadPoblacion;


    public City() {
    }

    public int getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(int ciudadID) {
        this.ciudadID = ciudadID;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }

    public String getPaisCodigo() {
        return paisCodigo;
    }

    public void setPaisCodigo(String paisCodigo) {
        this.paisCodigo = paisCodigo;
    }

    public String getCiudadDistrito() {
        return ciudadDistrito;
    }

    public void setCiudadDistrito(String ciudadDistrito) {
        this.ciudadDistrito = ciudadDistrito;
    }

    public int getCiudadPoblacion() {
        return ciudadPoblacion;
    }

    public void setCiudadPoblacion(int ciudadPoblacion) {
        this.ciudadPoblacion = ciudadPoblacion;
    }
}
