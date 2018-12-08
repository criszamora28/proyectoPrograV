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

    Usuario Usuario;
    boolean mostarMenuMantenimiento;
    boolean mostarMenuReportes;
    boolean mostrarMenuPrestamos;

    public beanMantenimientRecurso() {
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

    public void cerrarSession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        } catch (Exception e) {
        }

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
