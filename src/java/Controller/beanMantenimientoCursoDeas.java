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
    public beanMantenimientoCursoDeas() 
    {
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

//        public void actualizar() throws SNMPExceptions, SQLException {
//        CursoDeas us = this.getCursoDeas();
//
//        LinkedList<Usuario> lista = new UsuarioDB().seleccionarUsuarioId(us.identificacion);
//       if (lista == null) {
//
//        } else {
//            Usuario usuarioUp = lista.get(0);
//            usuarioUp.nombre = us.nombre;
//            usuarioUp.apellido1 = us.apellido1;
//            usuarioUp.apellido2 = us.apellido2;
//            usuarioUp.correo = us.correo;
//
//            UsuarioDB upUser = new UsuarioDB();
//            upUser.ActualizarUsuario(usuarioUp);
//            getListaAdministrativos();
 //       }

 //   }

    public void eliminar() throws SNMPExceptions, SQLException 
    {
//        Usuario us = this.getUsuario();
//
//        LinkedList<Usuario> lista = new UsuarioDB().seleccionarUsuarioId(us.identificacion);
//        if (lista == null) {
//
//        } else {
//            Usuario usuarioDel = lista.get(0);
//
//            UsuarioDB upUser = new UsuarioDB();
//            upUser.EliminarUsuario(usuarioDel);
//            getListaAdministrativos();

   //     }
    }  
      
      
    
      
      
    public CursoDeas getCursoDeas() {
        return cursoDeas;
    }

    public void setCursoDeas(CursoDeas cursoDeas) {
        this.cursoDeas = cursoDeas;
    }
      
      
}
