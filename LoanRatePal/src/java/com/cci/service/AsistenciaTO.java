/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

/**
 *
 * @author Jose
 */
public class AsistenciaTO {
    int idasistencia;
    String fecha;
    int idEmpleado;
    String Status;
    String nombre; 
     

    public AsistenciaTO() {
    }

    public AsistenciaTO(int idasistencia, String fecha, int idEmpleado, String Status) {
        this.idasistencia = idasistencia;
        this.fecha = fecha;
        this.idEmpleado = idEmpleado;
        this.Status = Status;
    }

    public int getIdasistencia() {
        return idasistencia;
    }

    public void setIdasistencia(int idasistencia) {
        this.idasistencia = idasistencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
