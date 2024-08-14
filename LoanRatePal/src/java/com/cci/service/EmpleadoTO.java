/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ADMIN
 */

//Atributos
public class EmpleadoTO implements Serializable {

 	int idempleado;
    int cedula;
    String nombre;
    String email;
    double salario;
    int telefono;
    int dias;
    String estado_laboral;
    double salarioBruto;
    double salarioNeto;
    Date fechaIncio;
    Date fechaFin;
    String rol;
    
    
    //Constructores
    public EmpleadoTO() {
        
    }

    public EmpleadoTO(int idempleado, int cedula, String nombre, String email, double salario, int telefono, String estado_laboral) {
        this.idempleado = idempleado;
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.salario = salario;
        this.telefono = telefono;
        this.estado_laboral = estado_laboral;
    }
    
    public EmpleadoTO(int idempleado, int cedula, String nombre, String email, double salario, int telefono) {
        this.idempleado = idempleado;
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.salario = salario;
        this.telefono = telefono;
    }

    
    //Getter y Setters
    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public double getSalarioNeto() {
        return salarioNeto;
    }

    public void setSalarioNeto(double salarioNeto) {
        this.salarioNeto = salarioNeto;
    }

    public Date getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(Date fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
  
    
    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEstado_laboral() {
        return estado_laboral;
    }

    public void setEstado_laboral(String estado_laboral) {
        this.estado_laboral = estado_laboral;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    

  
}