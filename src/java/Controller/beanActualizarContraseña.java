/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import Model.UsuarioDB;
import Model.Validadores;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author crisz
 */
@Named(value = "beanActualizarContraseña")
@SessionScoped
public class beanActualizarContraseña implements Serializable {

    /**
     * Creates a new instance of beanActualizarContraseña
     */
    String contraseñaNueva;
    String contraseñaRepiteNueva;
    String contraseñaActual;
    String codigoVerificacion;
    boolean mostrarCajaCodigo;

    Usuario Usuario;

    public beanActualizarContraseña() {
        mostrarCajaCodigo = false;
        UsuarioDB oUsuarioDB = new UsuarioDB();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

        try {
            final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            final Map<String, Object> session = context.getSessionMap();
            final Object object = session.get("Usuario");
            Usuario = (Usuario) object;
            if (Usuario.cuentaCompleta == false) {
                this.mostrarCajaCodigo = true;
            }
        } catch (Exception e) {
        }

    }

    public void cerrarSession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        } catch (Exception e) {
        }

    }

    public void cambiarContrasena() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        UsuarioDB oUsuarioDB = new UsuarioDB();
        try {
            final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            final Map<String, Object> session = context.getSessionMap();
            final Object object = session.get("Usuario");
            Usuario = (Usuario) object;
        } catch (Exception e) {
        }

        if (this.Usuario == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error al hacer el ingreso"));
            return;
        }

        String codigoverificacion = Usuario.codigoVerificacion;

        if (Validadores.validarVacio(this.contraseñaActual)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La contraseña actual no puede estar vacia"));
            return;
        }

        if (Validadores.validarVacio(this.contraseñaNueva)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La contraseña nueva no puede estar vacia"));
            return;
        }

        if (Validadores.validarVacio(this.contraseñaRepiteNueva)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La contraseña nueva no puede estar vacia"));
            return;
        }

        if (Validadores.validarVacio(this.codigoVerificacion)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El codigo de verificacion no puede estar vacio"));
            return;
        }

        if (!this.contraseñaNueva.matches("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,12}$")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "La contraseña debe tener al entre 8 y 12 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula. "));
            return;
        }

        if (!this.contraseñaNueva.equals(this.contraseñaRepiteNueva)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "Las contraseñas no son iguales "));
            return;
        }

        if (this.codigoVerificacion.equals(codigoverificacion) == false) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "Codigo de verificacion incorrecto!"));
            return;
        }

        try {
            Usuario.contrasena = this.contraseñaRepiteNueva;
            Usuario.cuentaCompleta = true;
            
            oUsuarioDB.ActualizarUsuarioContraseña(Usuario);
            oUsuarioDB.ActualizarUsuarioCuentaCompleta(Usuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("Principal.xhtml");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion!", "Debe actualizar los datos que se le piden!"));

        } catch (Exception e) {
        }

    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseñaNueva() {
        return contraseñaNueva;
    }

    public void setContraseñaNueva(String contraseñaNueva) {
        this.contraseñaNueva = contraseñaNueva;
    }

    public String getContraseñaRepiteNueva() {
        return contraseñaRepiteNueva;
    }

    public void setContraseñaRepiteNueva(String contraseñaRepiteNueva) {
        this.contraseñaRepiteNueva = contraseñaRepiteNueva;
    }

    public String getContraseñaActual() {
        return contraseñaActual;
    }

    public void setContraseñaActual(String contraseñaActual) {
        this.contraseñaActual = contraseñaActual;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public boolean isMostrarCajaCodigo() {
        return mostrarCajaCodigo;
    }

    public void setMostrarCajaCodigo(boolean mostrarCajaCodigo) {
        this.mostrarCajaCodigo = mostrarCajaCodigo;
    }

}
