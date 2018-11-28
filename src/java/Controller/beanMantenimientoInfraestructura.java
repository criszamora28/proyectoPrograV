/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Infraestructura;
import Model.InfraestructuraDB;
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
@Named(value = "beanMantenimientoInfraestructura")
@SessionScoped
public class beanMantenimientoInfraestructura implements Serializable {

    boolean visible;
    boolean disable;
    int estado;
    Infraestructura infra;

    public beanMantenimientoInfraestructura() {
        disable = true;
    }

    public List<Infraestructura> getListaInfraestructura() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<Infraestructura> lista = new LinkedList<Infraestructura>();
        InfraestructuraDB fDB = new InfraestructuraDB();
        Infraestructura n = new Infraestructura();
        lista = fDB.seleccionarInfraestructura();

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

    public void insertarInfraestructura() throws SNMPExceptions, SQLException {
        Infraestructura cu = this.infra;
        LinkedList<Infraestructura> lista = new InfraestructuraDB().seleccionarInfraestructuraId(cu);
        if (lista.isEmpty()) {
            Infraestructura nInfraestructura = new Infraestructura();
            nInfraestructura.idInfraestructura = cu.idInfraestructura;
            nInfraestructura.descripcion = cu.descripcion;
//            nInfraestructura.disponibilidad= (estado==1)?true:false;

            InfraestructuraDB db = new InfraestructuraDB();
            db.InsertarInfraestructura(nInfraestructura);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Infraestructura ingresada correctamente!"));
        } else {
            //mensajito
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error!", "Datos incorrectos!"));
        }

    }

    public void actualizar() throws SNMPExceptions, SQLException {
        Infraestructura us = this.getInfra();

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

    }

    public void eliminar() throws SNMPExceptions, SQLException {
        Infraestructura us = this.getInfra();

        LinkedList<Infraestructura> lista = new InfraestructuraDB().seleccionarInfraestructuraId(us);
        if (lista == null) {

        } else {
            Infraestructura InfraestructuraDel = lista.get(0);

            InfraestructuraDB upUser = new InfraestructuraDB();
            upUser.EliminarInfraestructura(InfraestructuraDel);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Infraestructura eliminada correctamente!"));

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
