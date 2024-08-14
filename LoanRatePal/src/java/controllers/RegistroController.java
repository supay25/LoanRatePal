package controllers;

import com.cci.service.EmpleadoTO;
import com.cci.service.ServicioRegistro;
import com.cci.service.UsuarioTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author natal
 */

@ManagedBean(name = "registroController")
@SessionScoped
public class RegistroController implements Serializable {

    private EmpleadoTO selectedEmpleado;
    private UsuarioTO selectedUsuario;
    private ServicioRegistro servicioRegistro = new ServicioRegistro();

    public RegistroController() {
        openNewEmpleado();
        openNewUsuario();
    }

    public void openNewUsuario() {
        this.selectedUsuario = new UsuarioTO();
    }

    public void openNewEmpleado() {
        this.selectedEmpleado = new EmpleadoTO();
    }

    public void guardarUsuario() {
        try {
            servicioRegistro.guardarEmpleadoYUsuario(selectedEmpleado, selectedUsuario);
        } catch (Exception e) {
            e.printStackTrace(); // Manejar el error de manera adecuada
        }
    }

    // Getters y Setters de selectedEmpleado y selectedUsuario
    public EmpleadoTO getSelectedEmpleado() {
        return selectedEmpleado;
    }

    public void setSelectedEmpleado(EmpleadoTO selectedEmpleado) {
        this.selectedEmpleado = selectedEmpleado;
    }

    public UsuarioTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioTO selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }
}