/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Usuario
 */
import javax.persistence.*;

@Entity
@Table(name = "Pais")
public class Country {

    @Id
    @Column(name = "pais_codigo", length = 3)
    private String paisCodigo;

    @Column(name = "pais_nombre", length = 52, nullable = false)
    private String paisNombre;

    @Column(name = "pais_continente", length = 50, nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'Asia'")
    private String paisContinente;

    @Column(name = "pais_region", length = 26, nullable = false, columnDefinition = "VARCHAR(26) DEFAULT ''")
    private String paisRegion;

    @Column(name = "pais_area", nullable = false, columnDefinition = "FLOAT DEFAULT 0.00")
    private Float paisArea;

    @Column(name = "pais_independencia")
    private Integer paisIndependencia;

    @Column(name = "pais_poblacion", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer paisPoblacion;

    @Column(name = "pais_expectativa_de_vida")
    private Float paisExpectativaDeVida;

    @Column(name = "pais_producto_interno_bruto")
    private Float paisProductoInternoBruto;

    @Column(name = "pais_producto_interno_bruto_antiguo")
    private Float paisProductoInternoBrutoAntiguo;

    @Column(name = "pais_nombre_local", length = 45, nullable = false, columnDefinition = "VARCHAR(45) DEFAULT ''")
    private String paisNombreLocal;

    @Column(name = "pais_gobierno", length = 45, nullable = false, columnDefinition = "VARCHAR(45) DEFAULT ''")
    private String paisGobierno;

    @Column(name = "pais_jefe_de_estado", length = 60)
    private String paisJefeDeEstado;

    @Column(name = "pais_capital")
    private Integer paisCapital;

    @Column(name = "pais_codigo_2", length = 2, nullable = false)
    private String paisCodigo2;

    // Constructor, getters y setters
    // Constructor
    public Country() {
    }

    // Getters y setters
    public String getPaisCodigo() {
        return paisCodigo;
    }

    public void setPaisCodigo(String paisCodigo) {
        this.paisCodigo = paisCodigo;
    }

    public String getPaisNombre() {
        return paisNombre;
    }

    public void setPaisNombre(String paisNombre) {
        this.paisNombre = paisNombre;
    }

    public String getPaisContinente() {
        return paisContinente;
    }

    public void setPaisContinente(String paisContinente) {
        this.paisContinente = paisContinente;
    }

    public String getPaisRegion() {
        return paisRegion;
    }

    public void setPaisRegion(String paisRegion) {
        this.paisRegion = paisRegion;
    }

    public Float getPaisArea() {
        return paisArea;
    }

    public void setPaisArea(Float paisArea) {
        this.paisArea = paisArea;
    }

    public Integer getPaisIndependencia() {
        return paisIndependencia;
    }

    public void setPaisIndependencia(Integer paisIndependencia) {
        this.paisIndependencia = paisIndependencia;
    }

    public Integer getPaisPoblacion() {
        return paisPoblacion;
    }

    public void setPaisPoblacion(Integer paisPoblacion) {
        this.paisPoblacion = paisPoblacion;
    }

    public Float getPaisExpectativaDeVida() {
        return paisExpectativaDeVida;
    }

    public void setPaisExpectativaDeVida(Float paisExpectativaDeVida) {
        this.paisExpectativaDeVida = paisExpectativaDeVida;
    }

    public Float getPaisProductoInternoBruto() {
        return paisProductoInternoBruto;
    }

    public void setPaisProductoInternoBruto(Float paisProductoInternoBruto) {
        this.paisProductoInternoBruto = paisProductoInternoBruto;
    }

    public Float getPaisProductoInternoBrutoAntiguo() {
        return paisProductoInternoBrutoAntiguo;
    }

    public void setPaisProductoInternoBrutoAntiguo(Float paisProductoInternoBrutoAntiguo) {
        this.paisProductoInternoBrutoAntiguo = paisProductoInternoBrutoAntiguo;
    }

    public String getPaisNombreLocal() {
        return paisNombreLocal;
    }

    public void setPaisNombreLocal(String paisNombreLocal) {
        this.paisNombreLocal = paisNombreLocal;
    }

    public String getPaisGobierno() {
        return paisGobierno;
    }

    public void setPaisGobierno(String paisGobierno) {
        this.paisGobierno = paisGobierno;
    }

    public String getPaisJefeDeEstado() {
        return paisJefeDeEstado;
    }

    public void setPaisJefeDeEstado(String paisJefeDeEstado) {
        this.paisJefeDeEstado = paisJefeDeEstado;
    }

    public Integer getPaisCapital() {
        return paisCapital;
    }

    public void setPaisCapital(Integer paisCapital) {
        this.paisCapital = paisCapital;
    }

    public String getPaisCodigo2() {
        return paisCodigo2;
    }

    public void setPaisCodigo2(String paisCodigo2) {
        this.paisCodigo2 = paisCodigo2;
    }
}
