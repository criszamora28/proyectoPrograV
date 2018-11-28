/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Infraestructura;
import Model.InfraestructuraDB;
import Model.Recurso;
import Model.RecursoDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Ernesto PC
 */
@Named(value = "beanMantenimientRecurso")
@SessionScoped
public class beanMantenimientRecurso implements Serializable {

    /**
     * Creates a new instance of beanMantenimientRecurso
     */
    boolean visible;
    boolean disable;
    int estado;
    Recurso recurso;

    public beanMantenimientRecurso() {
        disable = true;
    }

    public List<Recurso> getListaRecurso() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<Recurso> lista = new LinkedList<Recurso>();
        RecursoDB fDB = new RecursoDB();
        Recurso n = new Recurso();
        lista = fDB.seleccionarRecurso();

        return lista;
    }

//    public LinkedList<SelectItem> getListaEstado() throws SNMPExceptions, SQLException {
//
//        LinkedList resultList = new LinkedList();
//        resultList.add(new SelectItem(0, "Activo"));
//        resultList.add(new SelectItem(1, "Inactivo"));
//
//        return resultList;
//
//    }

    public void insertarRecurso() throws SNMPExceptions, SQLException {
        Recurso cu = this.recurso;
        LinkedList<Recurso> lista = new RecursoDB().seleccionarRecursoId(cu.id);
        if (lista.isEmpty()) {
            Recurso nRecurso = new Recurso();
            nRecurso.id = cu.id;
            nRecurso.tipo = cu.tipo;
            nRecurso.descripcion = cu.descripcion;
            

            RecursoDB db = new RecursoDB();
            db.InsertarRecurso(nRecurso);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Recurso ingresado correctamente!"));
        } else {
            //mensajito
        }

    }

    public void actualizar() throws SNMPExceptions, SQLException {
        Recurso us = this.getRecurso();

        LinkedList<Recurso> lista = new RecursoDB().seleccionarRecursoId(us.id);
        if (lista.isEmpty()) {

        } else {
            Recurso RecursoUp = lista.get(0);
            RecursoUp.id = us.id;
            RecursoUp.descripcion = us.descripcion;
            RecursoUp.tipo = us.tipo;
//            RecursoUp.estadoRecurso = (estado==0)?true:false;

            RecursoDB upUser = new RecursoDB();
            upUser.ActualizarRecurso(RecursoUp);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Recurso actualizado correctamente!"));

        }

    }

    public void eliminar() throws SNMPExceptions, SQLException {
        Recurso us = this.getRecurso();

        LinkedList<Recurso> lista = new RecursoDB().seleccionarRecursoId(us.id);
        if (lista == null) {

        } else {
            Recurso RecursoDel = lista.get(0);

            RecursoDB upUser = new RecursoDB();
            upUser.EliminarRecurso(RecursoDel);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Recurso eliminado correctamente!"));
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

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    
    
}
