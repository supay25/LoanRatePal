/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import com.cci.service.ServicioEmpleado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "loginController")
@SessionScoped

public class LoginController implements Serializable {
    
    private String usuario;
    private String clave;
    private int cedula;

    public LoginController() {
    }

    public LoginController(String usuario, String clave, int cedula) {
        this.usuario = usuario;
        this.clave = clave;
        this.cedula = cedula;
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

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    
    
    
    
    
     public void redireccionar(String ruta) {
        
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {
        }
    }
  
     
      public void ingresar() {
        ServicioEmpleado s = new ServicioEmpleado();

        if (s.Ver(this.getUsuario(), this.getClave())) {
              int id_acc = s.obtenerPermisoUsuario(this.getUsuario());
             
            if (id_acc == 1) {
                this.redireccionar("/faces/Dashboard.xhtml");
            } else {
//                this.redireccionar("/faces/Solicitudes.xhtml");
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos Invalidos", "La clave o correo no son correctos"));
        }
    }
     
     
}
