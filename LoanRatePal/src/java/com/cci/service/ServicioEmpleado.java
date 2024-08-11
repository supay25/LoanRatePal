/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ServicioEmpleado  extends Service {
    
    
     public boolean Ver(String usuario, String clave) {
        EmpleadoTO c = null;

        try {
            PreparedStatement stmt = super.getConexion().prepareStatement("SELECT COUNT(*) FROM usuario WHERE usuario = ? and clave=? ");
            stmt.setString(1, usuario);
            stmt.setString(2, clave);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                int count = resultado.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println("El error: " + ex);
        }

        return false;
    }
     //xd
   public Integer obtenerPermisoUsuario(String usuario) {
    try {
        PreparedStatement stmt = super.getConexion().prepareStatement("SELECT id_acceso FROM usuario WHERE usuario = ?");
        stmt.setString(1, usuario);
        ResultSet resultado = stmt.executeQuery();

        if (resultado.next()) {
            return resultado.getInt("id_acceso");
        }

        stmt.close();
    } catch (SQLException ex) {
        System.out.println("Error al obtener permiso del usuario: " + ex.getMessage());
    }

    return null;
}

    public String obtenerRolUsuario(String usuario) {
    try {
        PreparedStatement stmt = super.getConexion().prepareStatement("SELECT rol FROM usuario WHERE usuario = ?");
        stmt.setString(1, usuario);
        ResultSet resultado = stmt.executeQuery();

        if (resultado.next()) {
            return resultado.getString("rol");
        }

        stmt.close();
    } catch (SQLException ex) {
        System.out.println("Error al obtener rol del usuario: " + ex.getMessage());
    }

    return null;
}
       public EmpleadoTO obtenerEmpleadoId(int idempleado) {
        EmpleadoTO empleado = null;
        try {
            PreparedStatement stmt = super.getConexion().prepareStatement("SELECT * FROM empleado WHERE idempleado = ? ");
            stmt.setInt(1, idempleado);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                int cedula = resultado.getInt("cedula");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                Double salario = resultado.getDouble("salario");
                int telefono = resultado.getInt("telefono");

                empleado = new EmpleadoTO(idempleado, cedula, nombre, email, salario, telefono);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("El error al obtener empleado por ID: " + ex);
        }
        return empleado;

    }

    public EmpleadoTO obtenerEmpleadoIdPorUsuario(String usuario) {
        EmpleadoTO empleado = null;
        try {
            //  une las tablas usuario y empleado
            PreparedStatement stmt = super.getConexion().prepareStatement(
                    "SELECT e.idempleado, e.cedula, e.nombre, e.email, e.salario, e.telefono  "
                    + "FROM empleado e "
                    + "INNER JOIN usuario u ON e.idempleado = u.id_emple "
                    + "WHERE u.Usuario = ?"
            );
            stmt.setString(1, usuario);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                int idempleado = resultado.getInt("idempleado");
                int cedula = resultado.getInt("cedula");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                double salario = resultado.getDouble("salario");
                int telefono = resultado.getInt("telefono");
        

                empleado = new EmpleadoTO(idempleado, cedula, nombre, email, salario, telefono);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener empleado por usuario: " + ex);
        }
        return empleado;
    }

    public boolean actualizarEmpleado(EmpleadoTO empleado) {
        boolean actualizado = false;
        try {

            PreparedStatement stmt = super.getConexion().prepareStatement(
                    "UPDATE empleado SET email = ?, telefono = ? WHERE idempleado = ?" );
            stmt.setString(1, empleado.getEmail());
            stmt.setInt(2, empleado.getTelefono());
            stmt.setInt(3, empleado.getIdempleado());

            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                actualizado = true;
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar empleado: " + ex);
        }
        return actualizado;
    }
    
   public boolean guardarVacaciones(VacacionesTO vacaciones) {
    try {
        PreparedStatement stmt = super.getConexion().prepareStatement(
            "INSERT INTO vacaciones (id_em, inicio, fin) VALUES (?, ?, ?)"
        );
        stmt.setInt(1, vacaciones.getIdEmpleado());
        stmt.setDate(2, new java.sql.Date(vacaciones.getInicio().getTime()));
        stmt.setDate(3, new java.sql.Date(vacaciones.getFin().getTime()));

        int filasInsertadas = stmt.executeUpdate();
        stmt.close();

        return filasInsertadas > 0;
    } catch (SQLException ex) {
        System.out.println("Error al guardar las vacaciones: " + ex.getMessage());
        return false;
    }
}
    
}
