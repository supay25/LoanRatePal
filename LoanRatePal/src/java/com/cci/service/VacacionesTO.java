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
 * @author User
 */
public class VacacionesTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idEmpleado;
    private Date inicio;
    private Date fin;
    private String estado;

    public VacacionesTO() {
    }

    //zz
    public VacacionesTO(int idEmpleado, Date inicio, Date fin, String estado) {
        this.idEmpleado = idEmpleado;
        this.inicio = inicio;
        this.fin = fin;
        this.estado = estado;
    }

    public VacacionesTO(int idEmpleado, Date inicio, Date fin) {
        this.idEmpleado = idEmpleado;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
