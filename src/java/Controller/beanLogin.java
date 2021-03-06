/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.TipoFuncionario;
import Model.TipoFuncionarioDB;
import Model.Usuario;
import Model.UsuarioDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Ernesto PC
 */
@Named(value = "beanLogin")
@SessionScoped
public class beanLogin implements Serializable {

    /**
     * Creates a new instance of beanLogin
     */
    String identicacion;
    String contrasena;
    int tipoFun;

    public beanLogin() {
        try {
            final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map<String, Object> session = context.getSessionMap();
        final Object object2 = session.get("Mensaje");

        if (object2 != null) {
            String mensaje = (String) object2;
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Exito!", mensaje));
        }
        } catch (Exception e) {
            FacesContext context2 = FacesContext.getCurrentInstance();
                    context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }
        
    }
    
    @PostConstruct
    public void init() {
        try {
            final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map<String, Object> session = context.getSessionMap();
        final Object object2 = session.get("Mensaje");

        if (object2 != null) {
            String mensaje = (String) object2;
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Exito!", mensaje));
        }
        } catch (Exception e) {
            FacesContext context2 = FacesContext.getCurrentInstance();
                    context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }
        
    }

    public LinkedList<SelectItem> getListaTipoFuncionario() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";
        LinkedList<TipoFuncionario> lista = new LinkedList<TipoFuncionario>();
            TipoFuncionarioDB fDB = new TipoFuncionarioDB();
            TipoFuncionario n = new TipoFuncionario();
            LinkedList resultList = new LinkedList();
            
        try {
            
            lista = fDB.seleccionarTiposFuncionarios();
            
            for (TipoFuncionario tipoFun : lista) {
                id = tipoFun.getId();
                tipo = tipoFun.getTipoUsuario();
                resultList.add(new SelectItem(id, tipo));
            }
            
        } catch (Exception e) {
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }
        
        return resultList;

    }

    public void autenticar() {
        int tipo = 0;
        int identi = 0;
        String contrasena = "";
        tipo = this.getTipoFun();
        identi = Integer.parseInt(this.getIdenticacion());
        contrasena = this.getContrasena();

        try {
            LinkedList<Usuario> lista = new UsuarioDB().validarUsuario(identi, tipo, contrasena);
            if (lista.isEmpty()) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos incorrectos!", "Usuario o contraseña incorrecta!"));

            } else {
                Usuario usuario = lista.get(0);
                if (usuario.cuentaCompleta == false) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("ActualizacionContrasena.xhtml");
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion!", "Debe actualizar los datos que se le piden!"));

                } else {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Principal.xhtml");
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos correctos!", "Datos ingresados correctamente!"));

                }
            }
            

            

        } catch (Exception e) {
             FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));

        }

    }

    public String getIdenticacion() {
        return identicacion;
    }

    public void setIdenticacion(String identicacion) {
        this.identicacion = identicacion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getTipoFun() {
        return tipoFun;
    }

    public void setTipoFun(int tipoFun) {
        this.tipoFun = tipoFun;
    }

}
