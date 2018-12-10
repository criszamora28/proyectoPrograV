/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.ProgramaDeas;
import Model.ProgramaDeasDB;
import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
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
@Named(value = "beanMantenimientoProgramas")
@SessionScoped
public class beanMantenimientoProgramas implements Serializable {

    ProgramaDeas programaDeas;
    boolean visible;
    boolean disable;

    Usuario Usuario;
    boolean mostarMenuMantenimiento;
    boolean mostarMenuReportes;
    boolean mostrarMenuPrestamos;

    public beanMantenimientoProgramas() {
        disable = true;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map<String, Object> session = context.getSessionMap();
        final Object object = session.get("Usuario");
        try {
            if (object == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
            } else {
                Usuario = (Usuario) object;
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

    public void cerrarSession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        } catch (Exception e) {
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }

    }

    public List<ProgramaDeas> getListaProgramas() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<ProgramaDeas> lista = new LinkedList<ProgramaDeas>();
        ProgramaDeasDB fDB = new ProgramaDeasDB();
        ProgramaDeas n = new ProgramaDeas();
        try {
            lista = fDB.seleccionarProgramaDeas();
        } catch (Exception e) {
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }
        

        return lista;
    }

    public void insertarPrograma() throws SNMPExceptions, SQLException {
        ProgramaDeas cu = this.programaDeas;
        try {
            LinkedList<ProgramaDeas> lista = new ProgramaDeasDB().seleccionarProgramaDeasId(cu.id);
        if (lista.isEmpty()) {
            ProgramaDeas nProgramaDeas = new ProgramaDeas();
            nProgramaDeas.id = cu.id;
            nProgramaDeas.descripcion = cu.descripcion;
            nProgramaDeas.nombrePrograma = cu.nombrePrograma;

            ProgramaDeasDB db = new ProgramaDeasDB();
            db.InsertarProgramaDeas(nProgramaDeas);

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Programa ingresado correctamente!"));
        } else {
            //mensajito
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Datos incorrectos!"));
        }
        } catch (Exception e) {
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }
        

    }

    public void actualizar() throws SNMPExceptions, SQLException {
        ProgramaDeas us = this.getProgramaDeas();

        try {
            LinkedList<ProgramaDeas> lista = new ProgramaDeasDB().seleccionarProgramaDeasId(us.id);
        if (lista.isEmpty()) {

        } else {
            ProgramaDeas ProgramaDeasUp = lista.get(0);
            ProgramaDeasUp.nombrePrograma = us.nombrePrograma;
            ProgramaDeasUp.descripcion = us.descripcion;

            ProgramaDeasDB upUser = new ProgramaDeasDB();
            upUser.ActualizarProgramaDeas(ProgramaDeasUp);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Programa actualizado!"));
        }
        } catch (Exception e) {
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage("Error", e.toString()));
        }
        

    }

    public void eliminar() throws SNMPExceptions, SQLException {
        ProgramaDeas us = this.getProgramaDeas();

        try {
            LinkedList<ProgramaDeas> lista = new ProgramaDeasDB().seleccionarProgramaDeasId(us.id);
        if (lista == null) {

        } else {
            ProgramaDeas ProgramaDeasDel = lista.get(0);

            ProgramaDeasDB upUser = new ProgramaDeasDB();
            upUser.EliminarProgramaDeas(ProgramaDeasDel);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Programa Eliminado!"));
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

    public ProgramaDeas getProgramaDeas() {
        return programaDeas;
    }

    public void setProgramaDeas(ProgramaDeas programaDeas) {
        this.programaDeas = programaDeas;
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

}
