/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import java.util.Date;

/**
 *
 * @author User
 */
public class VacacionesTO {
    private int idEmpleado;
    private Date inicio;
    private Date fin;

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
    
    
}
