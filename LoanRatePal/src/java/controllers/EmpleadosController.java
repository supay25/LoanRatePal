/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.cci.service.EmpleadoTO;
import com.cci.service.ServicioEmpleado;
import com.cci.service.VacacionesTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */
@ManagedBean(name = "empleadosController")
@ViewScoped

public class EmpleadosController implements Serializable {

    ServicioEmpleado servicioEmpleado = new ServicioEmpleado();
    private EmpleadoTO empleado;
    private int idempleado;
    private List<Date> vacaciones;
    private int tiempoTranscurrido;

    public EmpleadosController() {

    }

    @PostConstruct
    public void buscarEmpleadoId() {
        idempleado = obtenerEmpleadoSesion();
        if (idempleado != 0) {
            empleado = servicioEmpleado.obtenerEmpleadoId(idempleado);

        }
    }

    public List<Date> getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(List<Date> vacaciones) {
        this.vacaciones = vacaciones;
    }

    public int getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void setTiempoTranscurrido(int tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public ServicioEmpleado getServicioEmpleado() {
        return servicioEmpleado;
    }

    public void setServicioEmpleado(ServicioEmpleado servicioEmpleado) {
        this.servicioEmpleado = servicioEmpleado;
    }

    public EmpleadoTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoTO empleado) {
        this.empleado = empleado;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    private int obtenerEmpleadoSesion() {
        Integer id = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idempleado");
        return id != null ? id : 0;
    }

   

    public void guardarVacaciones() {
        try {
            if (vacaciones != null && vacaciones.size() == 2) {
                Date inicio = vacaciones.get(0);
                Date fin = vacaciones.get(1);

               
                VacacionesTO vacacionesTO = new VacacionesTO(idempleado, inicio, fin);

                boolean guardado = servicioEmpleado.guardarVacaciones(vacacionesTO);

                if (guardado) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Las vacaciones se han guardado correctamente."));
                    vacaciones = null;  // Limpia la selección de vacaciones después de guardar
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron guardar las vacaciones."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Por favor, selecciona un rango de fechas válido."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar las vacaciones."));
        }
    }

    public void guardarCambios() {
        try {
            boolean actualizado = servicioEmpleado.actualizarEmpleado(empleado);

            if (actualizado) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Los datos se han actualizado correctamente."));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar los datos."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar los cambios."));
        }

    }

}
