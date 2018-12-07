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
    
    @PostConstruct
    private void CargaPagina()
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
                this.mostrarMensaje("Ingreso hecho correctamente", "Bienvenido " + Usuario.nombre);
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
    
    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }
    
}
