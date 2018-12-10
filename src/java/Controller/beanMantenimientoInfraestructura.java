/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Infraestructura;
import Model.InfraestructuraDB;
import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Ernesto PC
 */
@Named(value = "beanMantenimientoInfraestructura")
@SessionScoped
public class beanMantenimientoInfraestructura implements Serializable {

    boolean visible;
    boolean disable;
    int estado;
    Infraestructura infra;
    Usuario Usuario;
    boolean mostarMenuMantenimiento;
    boolean mostarMenuReportes;
    boolean mostrarMenuPrestamos;

    public beanMantenimientoInfraestructura() {
         disable = true;
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
                    mostrarMenuPrestamos = true;
                    
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
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));
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
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }
        
    }

    public List<Infraestructura> getListaInfraestructura() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<Infraestructura> lista = new LinkedList<Infraestructura>();
        InfraestructuraDB fDB = new InfraestructuraDB();
        Infraestructura n = new Infraestructura();
        try {
            lista = fDB.seleccionarInfraestructura();
        } catch (Exception e) {
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }
        

        return lista;
    }

    public void insertarInfraestructura() throws SNMPExceptions, SQLException {
        Infraestructura cu = this.infra;
        try {
            LinkedList<Infraestructura> lista = new InfraestructuraDB().seleccionarInfraestructuraId(cu);
        if (lista.isEmpty()) {
            Infraestructura nInfraestructura = new Infraestructura();
            nInfraestructura.idInfraestructura = cu.idInfraestructura;
            nInfraestructura.descripcion = cu.descripcion;

            InfraestructuraDB db = new InfraestructuraDB();
            db.InsertarInfraestructura(nInfraestructura);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Infraestructura ingresada correctamente!"));
        } else {
            //mensajito
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error!", "Datos incorrectos!"));
        }
        } catch (Exception e) {
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }
        

    }

    public void actualizar() throws SNMPExceptions, SQLException {
        Infraestructura us = this.getInfra();

        try {
            LinkedList<Infraestructura> lista = new InfraestructuraDB().seleccionarInfraestructuraId(us);
        if (lista.isEmpty()) {

        } else {
            Infraestructura InfraestructuraUp = lista.get(0);
            InfraestructuraUp.idInfraestructura = us.idInfraestructura;
            InfraestructuraUp.descripcion = us.descripcion;
            InfraestructuraUp.disponibilidad= (estado==0)?true:false;

            InfraestructuraDB upUser = new InfraestructuraDB();
            upUser.ActualizarInfraestructura(InfraestructuraUp);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Infraestructura actualizada correctamente!"));

        }
        } catch (Exception e) {
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }
        

    }

    public void eliminar() throws SNMPExceptions, SQLException {
        Infraestructura us = this.getInfra();

        try {
            LinkedList<Infraestructura> lista = new InfraestructuraDB().seleccionarInfraestructuraId(us);
        if (lista == null) {

        } else {
            Infraestructura InfraestructuraDel = lista.get(0);

            InfraestructuraDB upUser = new InfraestructuraDB();
            upUser.EliminarInfraestructura(InfraestructuraDel);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Infraestructura eliminada correctamente!"));

        }
        } catch (Exception e) {
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }
        
    }

    public void limpiaCampos() {
        visible = true;
        disable = false;

    }

    public void ocultar() {
        visible = false;
        disable = true;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public boolean isMostarMenuMantenimiento() {
        return mostarMenuMantenimiento;
    }

    public void setMostarMenuMantenimiento(boolean mostarMenuMantenimiento) {
        this.mostarMenuMantenimiento = mostarMenuMantenimiento;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public Infraestructura getInfra() {
        return infra;
    }

    public void setInfra(Infraestructura infra) {
        this.infra = infra;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }



}
