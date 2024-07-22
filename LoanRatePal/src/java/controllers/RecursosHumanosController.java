/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.cci.service.AsistenciaTO;
import com.cci.service.EmpleadoTO;
import com.cci.service.ServicioAsistencia;
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
    private List<AsistenciaTO> empleados = asistencia.listaAsistencia();
    private AsistenciaTO empleadoSeleccionado;
    private Date currentDate;
    private SimpleDateFormat sdf;
    
    public RecursosHumanosController() {
        currentDate = new Date();
    }
    
    public String getFormattedDate() {
         sdf = new SimpleDateFormat("d 'de' MMMM 'del' yyyy", new Locale("es", "ES"));              
        return sdf.format(currentDate);
    }
    
    public void guardarAsistencia() {
       
        
        System.out.println("Aqui estas " + this.empleadoSeleccionado.getStatus() + sdf.format(currentDate));
        
        asistencia.insertar(this.empleadoSeleccionado, sdf.format(currentDate));
       // this.empleados = asistencia.listaAsistencia();
        
        
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
    
    
    
}
