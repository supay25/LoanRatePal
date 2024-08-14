package com.cci.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioRegistro extends Service {

    
    //Guarda al empleado y al usuario en el bd
    public void guardarEmpleadoYUsuario(EmpleadoTO empleado, UsuarioTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement psEmpleado = null;
        PreparedStatement psUsuario = null;
        ResultSet rs = null;

        try {
            conn = super.getConexion(); // Obtén una nueva conexión cada vez
            if (conn == null || conn.isClosed()) {
                throw new SQLException("Connection is closed or null");
            }
            conn.setAutoCommit(false); // Iniciar transacción

            // Insertar en la tabla empleado
            String sqlEmpleado = "INSERT INTO empleado (cedula, nombre, email, salario, telefono) VALUES (?, ?, ?, ?, ?)";
            psEmpleado = conn.prepareStatement(sqlEmpleado, PreparedStatement.RETURN_GENERATED_KEYS);
            psEmpleado.setInt(1, empleado.getCedula());
            psEmpleado.setString(2, empleado.getNombre());
            psEmpleado.setString(3, empleado.getEmail());
            psEmpleado.setDouble(4, empleado.getSalario());
            psEmpleado.setInt(5, empleado.getTelefono());
            psEmpleado.executeUpdate();

            // Obtener el ID generado para el empleado
            rs = psEmpleado.getGeneratedKeys();
            int idEmpleadoGenerado = 0;
            if (rs.next()) {
                idEmpleadoGenerado = rs.getInt(1); // Asignar el id generado
            }

            // Insertar en la tabla usuario, asociando el idEmpleado
            String sqlUsuario = "INSERT INTO usuario (usuario, clave, rol, id_emple) VALUES (?, ?, ?, ?)";
            psUsuario = conn.prepareStatement(sqlUsuario);
            psUsuario.setString(1, usuario.getUsuario());
            psUsuario.setString(2, usuario.getClave());
            psUsuario.setString(3, usuario.getRol());
            psUsuario.setInt(4, idEmpleadoGenerado); // Asignar el idEmpleado generado
            psUsuario.executeUpdate();

            // Confirmar transacción
            conn.commit();
        } catch (SQLException e) {
            if (conn != null && !conn.isClosed()) {
                try {
                    conn.rollback(); // Revertir transacción en caso de error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace(); // Manejo de excepción durante el rollback
                }
            }
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Manejo de excepción durante el cierre del ResultSet
                }
            }
            if (psEmpleado != null) {
                try {
                    psEmpleado.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Manejo de excepción durante el cierre del PreparedStatement
                }
            }
            if (psUsuario != null) {
                try {
                    psUsuario.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Manejo de excepción durante el cierre del PreparedStatement
                }
            }
            // No cierres la conexión aquí para mantenerla abierta para futuras operaciones
        }
    }
}
