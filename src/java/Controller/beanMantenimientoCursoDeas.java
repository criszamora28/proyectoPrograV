/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.CursoDeas;
import Model.CursoDeasDB;
import Model.Usuario;
import Model.UsuarioDB;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ernesto PC
 */
@Named(value = "beanMantenimientoCursoDeas")
@SessionScoped
public class beanMantenimientoCursoDeas implements Serializable {

    /**
     * Creates a new instance of beanMantenimientoCursoDeas
     */
    CursoDeas cursoDeas;
    boolean visible;
    boolean disable;
    
    
    public beanMantenimientoCursoDeas() {
        disable=true;
    }

    public List<CursoDeas> getListaCursos() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<CursoDeas> lista = new LinkedList<CursoDeas>();
        CursoDeasDB fDB = new CursoDeasDB();
        CursoDeas n = new CursoDeas();
        lista = fDB.seleccionarCursosDeas();

        return lista;
    }
    
    
    public void insertarCurso() throws SNMPExceptions, SQLException
     {
     CursoDeas cu= this.cursoDeas;
     LinkedList<CursoDeas> lista=  new CursoDeasDB().seleccionarCursosDeasId(cu.idPrograma);
     if(lista.isEmpty())
     {
        CursoDeas nCurso= new CursoDeas();
        nCurso.idPrograma=cu.idPrograma;
        nCurso.descripcion=cu.descripcion;
        nCurso.nombreCurso=cu.nombreCurso;
        
        CursoDeasDB db= new CursoDeasDB();
        db.InsertarCursoDeas(nCurso);
     }
     else
     {
     //mensajito
     }
     
     }

    public void actualizar() throws SNMPExceptions, SQLException {
        CursoDeas us = this.getCursoDeas();

        LinkedList<CursoDeas> lista = new CursoDeasDB().seleccionarCursosDeasId(us.idPrograma);
        if (lista.isEmpty()) {

        } else {
            CursoDeas cursoDeasUp = lista.get(0);
            cursoDeasUp.nombreCurso = us.nombreCurso;
            cursoDeasUp.descripcion = us.descripcion;

            CursoDeasDB upUser = new CursoDeasDB();
            upUser.ActualizarCurso(cursoDeasUp);

        }

    }

    public void eliminar() throws SNMPExceptions, SQLException {
        CursoDeas us = this.getCursoDeas();

        LinkedList<CursoDeas> lista = new CursoDeasDB().seleccionarCursosDeasId(us.getIdPrograma());
        if (lista == null) {

        } else {
            CursoDeas cursoDel = lista.get(0);

            CursoDeasDB upUser = new CursoDeasDB();
            upUser.EliminarCurso(cursoDel);

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

    public CursoDeas getCursoDeas() {
        return cursoDeas;
    }

    public void setCursoDeas(CursoDeas cursoDeas) {
        this.cursoDeas = cursoDeas;
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
