/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author crisz
 */
@Named(value = "beanSolicitarCuenta")
@SessionScoped
public class beanSolicitarCuenta implements Serializable {

    private String cedula;
    private String correo;

    public beanSolicitarCuenta() {
    }

    public void validacionCorreo() {
        
        
        
        if (!correo.matches(".+@.+\\.[a-z]+")) {
            throw new ValidatorException(new FacesMessage("Formato de mail incorrecto"));
        }
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
