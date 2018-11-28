/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.ProgramaDeas;
import Model.ProgramaDeasDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
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

    public beanMantenimientoProgramas() {
        disable = true;
    }

    public List<ProgramaDeas> getListaProgramas() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<ProgramaDeas> lista = new LinkedList<ProgramaDeas>();
        ProgramaDeasDB fDB = new ProgramaDeasDB();
        ProgramaDeas n = new ProgramaDeas();
        lista = fDB.seleccionarProgramaDeas();

        return lista;
    }



    public void insertarPrograma() throws SNMPExceptions, SQLException {
        ProgramaDeas cu = this.programaDeas;
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

    }

    public void actualizar() throws SNMPExceptions, SQLException {
        ProgramaDeas us = this.getProgramaDeas();

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

    }

    public void eliminar() throws SNMPExceptions, SQLException {
        ProgramaDeas us = this.getProgramaDeas();

        LinkedList<ProgramaDeas> lista = new ProgramaDeasDB().seleccionarProgramaDeasId(us.id);
        if (lista == null) {

        } else {
            ProgramaDeas ProgramaDeasDel = lista.get(0);

            ProgramaDeasDB upUser = new ProgramaDeasDB();
            upUser.EliminarProgramaDeas(ProgramaDeasDel);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Programa Eliminado!"));
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

}
