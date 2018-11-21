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

    public beanMantenimientoCursoDeas() {
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

    public void actualizar() throws SNMPExceptions, SQLException {
        CursoDeas us = this.getCursoDeas();

        LinkedList<CursoDeas> lista = new CursoDeasDB().seleccionarCursosDeasId(us.idPrograma);
        if (lista == null) {

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

    public CursoDeas getCursoDeas() {
        return cursoDeas;
    }

    public void setCursoDeas(CursoDeas cursoDeas) {
        this.cursoDeas = cursoDeas;
    }

}
