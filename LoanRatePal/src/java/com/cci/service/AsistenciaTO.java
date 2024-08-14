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
 * @author Jose
 */

//Atributos
public class AsistenciaTO implements Serializable{
    int idasistencia;
    Date fecha;
    int idEmpleado;
    String Status;
    String nombre; 
     
    
//Constructores
    public AsistenciaTO() {
    }

    public AsistenciaTO(int idasistencia, Date fecha, int idEmpleado, String Status) {
        this.idasistencia = idasistencia;
        this.fecha = fecha;
        this.idEmpleado = idEmpleado;
        this.Status = Status;
    }

    
    //Getters y setters
    public int getIdasistencia() {
        return idasistencia;
    }

    public void setIdasistencia(int idasistencia) {
        this.idasistencia = idasistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
}
