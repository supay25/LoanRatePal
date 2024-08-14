/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jose
 */

//Funciones
public class ServicioAsistencia extends Service {

    
    //Inserta en la tabla de asistencia
    public void insertar(AsistenciaTO asistencia, Date fecha) {
        try {
            if (tiendaExists(fecha, asistencia.getIdEmpleado())) {
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al insertar usuario", "Hubo un error al insertar el usuario"));

                PreparedStatement stmt = super.getConexion().prepareStatement("Update asistencia SET status = ?  WHERE fecha = ? and idEmpleado = ?");
                //stmt.setInt(1, userTO.getId());

                stmt.setString(1, asistencia.getStatus());
                stmt.setDate(2,new java.sql.Date(fecha.getTime()));
                stmt.setInt(3, asistencia.getIdEmpleado());
                stmt.execute();

                stmt.close();
                return;
            } else {

                if ("".equals(fecha) || "".equals(asistencia.getIdEmpleado()) || "".equals(asistencia.getStatus())) {
                    // Your code here
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Campos Vacios"));
                } else {
                    PreparedStatement stmt = super.getConexion().prepareStatement("INSERT INTO asistencia (fecha, idEmpleado,status) VALUES (?,?,?)");
                    //stmt.setInt(1, userTO.getId());

                    stmt.setDate(1, new java.sql.Date(fecha.getTime()));
                    stmt.setInt(2, asistencia.getIdEmpleado());
                    stmt.setString(3, asistencia.getStatus());
                    stmt.execute();

                    stmt.close();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asistencia Guardada", "Proceso: Exitoso"));
                }

            }

            //super.getConexion().close();
        } catch (SQLException ex) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al insertar Tienda", "Hubo un error al insertar la tienda: " + ex.getMessage()));
        }
    }

    //Revisa si ya hay una tienda existente por el nombre
    public boolean tiendaExists(Date fecha, int empleado) {
        try {
            PreparedStatement checkStmt = super.getConexion().prepareStatement("SELECT COUNT(*) FROM asistencia WHERE fecha = ? and idEmpleado = ?");
            checkStmt.setDate(1, new java.sql.Date(fecha.getTime()));
            checkStmt.setInt(2, empleado);

            ResultSet result = checkStmt.executeQuery();
            if (result.next()) {
                int count = result.getInt(1);
                return count > 0; // If count > 0, the user already exists
            }
        } catch (SQLException ex) {
            System.out.println("Error checking if user exists: " + ex.getMessage());
        }
        return false;
    }

    
    //Crea una lista para la Asitencia
    public List<AsistenciaTO> listaAsistencia() {

        List<AsistenciaTO> listaRetorno = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("d'/'MMMM '/'yyyy", new Locale("es", "ES"));  
        Date fecha = new Date();
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());

        // First query to get asistencia records
        try {
            PreparedStatement stmt1 = super.getConexion().prepareStatement("SELECT  e.idEmpleado,e.cedula, e.nombre ,a.fecha, a.status FROM loanratepal.empleado e JOIN loanratepal.asistencia a ON a.idEmpleado = e.idempleado WHERE a.fecha = ?;");
            stmt1.setDate(1, sqlDate);
            ResultSet rs1 = stmt1.executeQuery();

            while (rs1.next()) {
                int idEmpleado = rs1.getInt("e.idEmpleado");
                Date fechaPuntual = rs1.getDate("a.fecha");
                String nombreEmpleado = rs1.getString("e.nombre");
                String status = rs1.getString("a.status");

                AsistenciaTO objetoLista = new AsistenciaTO();
                objetoLista.setIdEmpleado(idEmpleado);
                objetoLista.setStatus(status);
                objetoLista.setFecha(fechaPuntual);
                objetoLista.setNombre(nombreEmpleado);

                listaRetorno.add(objetoLista);

            }

            // Check if listaRetorno is empty and perform the second query if needed
            if (listaRetorno.isEmpty()) {
                String sql2 = "SELECT e.idEmpleado, e.cedula, e.nombre "
                        + "FROM empleado e";

                try (PreparedStatement stmt2 = super.getConexion().prepareStatement(sql2);
                        ResultSet rs2 = stmt2.executeQuery()) {

                    while (rs2.next()) {
                        int idEmpleado = rs2.getInt("idEmpleado");
                        String nombreEmpleado = rs2.getString("nombre");

                        // Create AsistenciaTO with default values or adjust as needed
                        AsistenciaTO objetoLista = new AsistenciaTO();
                        objetoLista.setIdEmpleado(idEmpleado);
                        objetoLista.setNombre(nombreEmpleado);
                        objetoLista.setStatus("Asignar");

                        listaRetorno.add(objetoLista);
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaRetorno;

    }
}
