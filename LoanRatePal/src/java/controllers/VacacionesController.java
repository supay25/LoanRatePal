package controllers;

import com.cci.service.ServicioVacaciones;
import com.cci.service.VacacionesTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "vacacionesController")
@ViewScoped
public class VacacionesController implements Serializable {

    
    private int id_em;
    private Date inicio;
    private Date fin;
    private String estado;
    
    private VacacionesTO selectVacaciones;
    private List<VacacionesTO> vacaciones;
    private ServicioVacaciones servicio;

    public VacacionesController() {
         servicio = new ServicioVacaciones();
        vacaciones = servicio.verVacaciones();
    }
    
   public void openNew(VacacionesTO vacacion) {
    if (vacacion != null) {
        this.selectVacaciones = vacacion;
    } else {
        // Manejo de caso donde vacacion es null
        // Por ejemplo, mostrar un mensaje de error o inicializar selectVacaciones
        this.selectVacaciones = new VacacionesTO();
    }
}

    
    public void saveEstado() {
        if (selectVacaciones != null) {
            servicio.actualizarEstado(selectVacaciones.getIdEmpleado(), selectVacaciones.getEstado());
            vacaciones = servicio.verVacaciones(); // Actualizar la lista de vacaciones
        }
    }

    public List<VacacionesTO> getVacaciones() {
        return vacaciones;
    }
//zz
    public int getId_em() {
        return id_em;
    }

    public void setId_em(int id_em) {
        this.id_em = id_em;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public VacacionesTO getSelectVacaciones() {
        return selectVacaciones;
    }

    public void setSelectVacaciones(VacacionesTO selectVacaciones) {
        this.selectVacaciones = selectVacaciones;
    }
}