/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author crisz
 */
@Named(value = "beanPrincipal")
@SessionScoped
public class beanPrincipal implements Serializable {

    /**
     * Creates a new instance of beanPrincipal
     */
    Usuario Usuario;
    boolean mostarMenuMantenimiento;
    boolean mostarMenuReportes;
    boolean mostrarMenuPrestamos;

    public beanPrincipal() {
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	final Map<String, Object> session = context.getSessionMap();
	final Object object = session.get("Usuario");
        try {
            if (object == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
            }else{
                Usuario = (Usuario)object;
                this.mostrarMensaje("Ingreso hecho correctamente", "Bienvenido " + Usuario.nombre);
                if (Usuario.tipoFuncionario.TipoUsuario.equalsIgnoreCase("Administrativo")) {
                    mostarMenuMantenimiento = true;
                    mostarMenuReportes = true;
                    
                } else {
                    if (Usuario.tipoFuncionario.TipoUsuario.equalsIgnoreCase("Docente")) {
                        mostarMenuMantenimiento = false;
                        mostarMenuReportes = false;
                        mostrarMenuPrestamos = true;
                    } else {
                        
                            mostarMenuMantenimiento = true;
                            mostarMenuReportes = true;
                            mostrarMenuPrestamos = true;
                        
                    }
                }
            }
        } catch (Exception e) {
        }
    }
    
    
    @PostConstruct
    private void load()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	final Map<String, Object> session = context.getSessionMap();
	final Object object = session.get("Usuario");
        try {
            if (object == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
            }else{
                Usuario = (Usuario)object;
                
                if (Usuario.tipoFuncionario.TipoUsuario.equalsIgnoreCase("Administrativo")) {
                    mostarMenuMantenimiento = true;
                    mostarMenuReportes = true;
                    
                } else {
                    if (Usuario.tipoFuncionario.TipoUsuario.equalsIgnoreCase("Docente")) {
                        mostarMenuMantenimiento = false;
                        mostarMenuReportes = false;
                        mostrarMenuPrestamos = true;
                    } else {
                        
                            mostarMenuMantenimiento = true;
                            mostarMenuReportes = true;
                            mostrarMenuPrestamos = true;
                        
                    }
                }
            }
        } catch (Exception e) {
        }
        
    }
    
    
    
    private void mostrarMensaje(String encMensaje,String detMensaje){
        FacesContext context = FacesContext.getCurrentInstance();
               context.addMessage(null, new FacesMessage(encMensaje, detMensaje));
    }
    
    public void cerrarSession(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        } catch (Exception e) {
        }
        
    }

    public boolean isMostarMenuReportes() {
        return mostarMenuReportes;
    }

    public void setMostarMenuReportes(boolean mostarMenuReportes) {
        this.mostarMenuReportes = mostarMenuReportes;
    }

    public boolean isMostrarMenuPrestamos() {
        return mostrarMenuPrestamos;
    }

    public void setMostrarMenuPrestamos(boolean mostrarMenuPrestamos) {
        this.mostrarMenuPrestamos = mostrarMenuPrestamos;
    }

    public boolean isMostarMenuMantenimiento() {
        return mostarMenuMantenimiento;
    }

    public void setMostarMenuMantenimiento(boolean mostarMenuMantenimiento) {
        this.mostarMenuMantenimiento = mostarMenuMantenimiento;
    }

    public boolean isMostarMenuReportesMantenimiento() {
        return mostarMenuReportes;
    }

    public void setMostarMenuReportesMantenimiento(boolean mostarMenuReportesMantenimiento) {
        this.mostarMenuReportes = mostarMenuReportesMantenimiento;
    }
    
    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }
    
}
