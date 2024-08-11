/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jose
 */
public class ServicioPlanilla extends Service {

    public List<EmpleadoTO> listaPlanillaa(Date fechaInicio, Date fechaFin) {
        List<EmpleadoTO> retorno = new ArrayList<>();

        // Query to retrieve employees and count of days they were present within the specified date range
        String query = "SELECT \n"
                + "    e.idEmpleado, \n"
                + "    e.cedula, \n"
                + "    e.nombre, \n"
                + "    e.salario,\n"
                + "    COUNT(CASE \n"
                + "            WHEN a.status = 'Presente' AND a.fecha BETWEEN ? AND ? \n"
                + "            THEN 1 \n"
                + "            ELSE NULL \n"
                + "         END) AS diasPresente,\n"
                + "    (e.salario / DAY(LAST_DAY(?))) * \n"
                + "    COUNT(CASE \n"
                + "            WHEN a.status = 'Presente' AND a.fecha BETWEEN ? AND ? \n"
                + "            THEN 1 \n"
                + "            ELSE NULL \n"
                + "         END) AS salarioNetoBruto,\n"
                + "    ((e.salario / DAY(LAST_DAY(?))) * \n"
                + "    COUNT(CASE \n"
                + "            WHEN a.status = 'Presente' AND a.fecha BETWEEN ? AND ? \n"
                + "            THEN 1 \n"
                + "            ELSE NULL \n"
                + "         END)) * 0.87 AS salarioNeto\n"
                + "FROM \n"
                + "    loanratepal.empleado e\n"
                + "LEFT JOIN \n"
                + "    loanratepal.asistencia a \n"
                + "ON \n"
                + "    e.idEmpleado = a.idEmpleado\n"
                + "GROUP BY \n"
                + "    e.idEmpleado, \n"
                + "    e.cedula, \n"
                + "    e.nombre, \n"
                + "    e.salario;";

        try (PreparedStatement stmt = super.getConexion().prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(fechaFin.getTime()));
            stmt.setDate(3, new java.sql.Date(fechaFin.getTime())); // For LAST_DAY
            stmt.setDate(4, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(5, new java.sql.Date(fechaFin.getTime()));
            stmt.setDate(6, new java.sql.Date(fechaFin.getTime())); // For LAST_DAY
            stmt.setDate(7, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(8, new java.sql.Date(fechaFin.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    EmpleadoTO empleado = new EmpleadoTO();
                    empleado.setIdempleado(rs.getInt("idEmpleado"));
                    empleado.setCedula(rs.getInt("cedula"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setSalario(rs.getDouble("salario"));
                    empleado.setDias(rs.getInt("diasPresente"));
                    empleado.setSalarioBruto(rs.getInt("salarioNetoBruto"));
                    empleado.setSalarioNeto(rs.getInt("salarioNeto"));

                    retorno.add(empleado);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return retorno;
    }

    public void guardar(EmpleadoTO nomina,Date fechaInicio, Date fechaFin) {
        try {
            if (tiendaExists(nomina.getIdempleado(), fechaInicio, fechaFin)) {
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al insertar usuario", "Hubo un error al insertar el usuario"));

                PreparedStatement stmt = super.getConexion().prepareStatement("Update nomina SET diasTrabajados = ?,salarioneto = ?, salarioBruto = ?  WHERE fechaInicio = ? and id_empleado = ? and fechaFin = ?");
                //stmt.setInt(1, userTO.getId());

                stmt.setInt(1, nomina.getDias());
                stmt.setInt(2, (int) nomina.getSalarioNeto());
                stmt.setInt(3,(int) nomina.getSalarioBruto());
                stmt.setDate(4, new java.sql.Date(fechaInicio.getTime()));
                stmt.setInt(5,(int) nomina.getIdempleado());
                stmt.setDate(6, new java.sql.Date(fechaFin.getTime()));
                stmt.execute();

                stmt.close();
                return;
            } else {

                if ("".equals(fechaInicio) || "".equals(nomina.getIdempleado()) || "".equals(nomina.getSalarioNeto())) {
                    // Your code here
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Campos Vacios"));
                } else {
                    PreparedStatement stmt = super.getConexion().prepareStatement("INSERT INTO nomina (id_empleado,salarioneto,fechaInicio,salarioBruto,diasTrabajados,fechaFin) VALUES (?,?,?,?,?,?)");
                    //stmt.setInt(1, userTO.getId());

                    
                    stmt.setInt(1, (int) nomina.getIdempleado());
                    stmt.setInt(2, (int) nomina.getSalarioNeto());
                    stmt.setDate(3, new java.sql.Date(fechaInicio.getTime()));                   
                    stmt.setInt(4, (int) nomina.getSalarioBruto());
                    stmt.setInt(5, (int) nomina.getDias());
                    stmt.setDate(6, new java.sql.Date(fechaFin.getTime()));
                    
                    stmt.execute();

                    stmt.close();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nomina Guardada", "Proceso: Exitoso"));
                }

            }

            //super.getConexion().close();
        } catch (SQLException ex) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al insertar Nomina", "Hubo un error al insertar la nomina: " + ex.getMessage()));
        }
    }

    public boolean tiendaExists(int idEmpleado, Date fechaInicio, Date fechaFin) {
        try {
            PreparedStatement checkStmt = super.getConexion().prepareStatement("SELECT COUNT(*) FROM nomina WHERE fechaInicio = ? and id_empleado = ? and fechaFin = ?");
            checkStmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            checkStmt.setInt(2, idEmpleado);
            checkStmt.setDate(3, new java.sql.Date(fechaFin.getTime()));

            ResultSet result = checkStmt.executeQuery();
            if (result.next()) {
                int count = result.getInt(1);
                return count > 0; // If count > 0, the user already exists
            }
        } catch (SQLException ex) {
            System.out.println("Error checking if nomina exists: " + ex.getMessage());
        }
        return false;
    }
}
