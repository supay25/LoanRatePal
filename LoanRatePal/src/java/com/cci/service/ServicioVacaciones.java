package com.cci.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioVacaciones extends Service {

    // enlista las vacaciones solicitada
    public List<VacacionesTO> verVacaciones() {
        List<VacacionesTO> listaRetorno = new ArrayList<>();
        try {
            System.out.println("Creating statement for Vacaciones...");
            PreparedStatement stmt1 = super.getConexion().prepareStatement(
                    "SELECT id_em, inicio, fin, estado FROM loanratepal.vacaciones");

            ResultSet rs1 = stmt1.executeQuery();

            while (rs1.next()) {
                VacacionesTO vacaciones = new VacacionesTO();
                vacaciones.setIdEmpleado(rs1.getInt("id_em"));
                vacaciones.setInicio(rs1.getDate("inicio"));
                vacaciones.setFin(rs1.getDate("fin"));
                vacaciones.setEstado(rs1.getString("estado"));

                listaRetorno.add(vacaciones);
            }

            rs1.close();
            stmt1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaRetorno;

    }

    //Actualiza el estado de la vacación solicitada.
    public void actualizarEstado(int idEmpleado, String nuevoEstado) {
        try {
            PreparedStatement stmt = super.getConexion().prepareStatement(
                    "UPDATE loanratepal.vacaciones SET estado = ? WHERE id_em = ?");
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idEmpleado);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
