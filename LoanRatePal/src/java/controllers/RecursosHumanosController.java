/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.cci.service.AsistenciaTO;
import com.cci.service.EmpleadoTO;
import com.cci.service.ServicioAsistencia;
import com.cci.service.ServicioPlanilla;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jose
 */
@ManagedBean(name = "HRController")
@SessionScoped
public class RecursosHumanosController {
    
    ServicioAsistencia asistencia = new ServicioAsistencia();
    ServicioPlanilla planillaServicio = new ServicioPlanilla();
    private List<AsistenciaTO> empleados = asistencia.listaAsistencia();
    private AsistenciaTO empleadoSeleccionado;
    private Date currentDate;
    private SimpleDateFormat sdf;   
    private List<EmpleadoTO> planilla;
    private EmpleadoTO empleadoSeleccionadoTO;
    private Date fechaInicio;
    private Date fechaFinal;
    
    
    public RecursosHumanosController() {
        currentDate = new Date();
    }
    
    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("d'/'MMMM '/'yyyy", new Locale("es", "ES"));                
        return sdf.format(currentDate);
    }
    
    public void guardarAsistencia() {
  
        asistencia.insertar(this.empleadoSeleccionado, currentDate);
       // this.empleados = asistencia.listaAsistencia();
               
    }
    public void buscarPlanillas(){
        
        this.planilla = planillaServicio.listaPlanillaa(fechaInicio, fechaFinal);
    }
    public void guardarNomina(){
        
        planillaServicio.guardar(this.empleadoSeleccionadoTO,fechaInicio,fechaFinal);
        
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    

    
    
    

    public EmpleadoTO getEmpleadoSeleccionadoTO() {
        return empleadoSeleccionadoTO;
    }

    public void setEmpleadoSeleccionadoTO(EmpleadoTO empleadoSeleccionadoTO) {
        this.empleadoSeleccionadoTO = empleadoSeleccionadoTO;
    }
    

    public List<AsistenciaTO> getEmpleados() {
        
        return empleados;
    }

    public void setEmpleados(List<AsistenciaTO> empleados) {
        this.empleados = empleados;
    }

    public AsistenciaTO getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(AsistenciaTO empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }
    

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public List<EmpleadoTO> getPlanilla() {
        return planilla;
    }

    public void setPlanilla(List<EmpleadoTO> planilla) {
        this.planilla = planilla;
    }
    
    
    
}
