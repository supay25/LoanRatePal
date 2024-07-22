/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

/**
 *
 * @author ADMIN
 */
public class EmpleadoTO {

    int idempleado;
    int cedula;
    String nombre;
    String email;
    double salario;
    int telefono;
    int idrol;
    int acceso;
    
    
    public EmpleadoTO() {
        
    }

    public EmpleadoTO(int idempleado, int cedula, String nombre, String email, double salario, int telefono, int idrol, int acceso) {
        this.idempleado = idempleado;
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.salario = salario;
        this.telefono = telefono;
        this.idrol = idrol;
        this.acceso = acceso;
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

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public int getAcceso() {
        return acceso;
    }

    public void setAcceso(int acceso) {
        this.acceso = acceso;
    }

}
