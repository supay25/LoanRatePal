/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author natal
 */

// Atributos
public class UsuarioTO implements Serializable {

    private int idUsuario;
    private String usuario;
    private String clave;
    private String rol;
    

    // constructor
    public UsuarioTO() {
    }

    public UsuarioTO(int idUsuario, String usuario, String clave, String rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;

    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }



 
}
