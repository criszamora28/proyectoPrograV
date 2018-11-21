/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Usuario;
import Model.UsuarioDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

/**
 *
 * @author crisz
 */
@Named(value = "beanMentenimientoUsuario")
@SessionScoped
public class beanMentenimientoUsuario implements Serializable {

    List<Usuario> listaUsuarios;
    Usuario Usuario;
    int id;
    String nombre;
    String apellido1;
    String apellido2;
    String corre;

//    @PostConstruct
//    public void init() {
//        Usuario = new Usuario("Cris", "Zamora", "Reyes", "Ninguna");
//        listaUsuarios = new LinkedList<>();
//        listaUsuarios.add(Usuario);
//        
//    }
    public List<Usuario> getListaAdministrativos() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<Usuario> lista = new LinkedList<Usuario>();
        UsuarioDB fDB = new UsuarioDB();
        Usuario n = new Usuario();
        lista = fDB.seleccionarUsuariosAdministrativos();

        return lista;
    }

    public void actualizar() throws SNMPExceptions, SQLException {
        Usuario us = this.getUsuario();

        LinkedList<Usuario> lista = new UsuarioDB().seleccionarUsuarioId(us.identificacion);
        if (lista == null) {

        } else {
            Usuario usuarioUp = lista.get(0);
            usuarioUp.nombre = us.nombre;
            usuarioUp.apellido1 = us.apellido1;
            usuarioUp.apellido2 = us.apellido2;
            usuarioUp.correo = us.correo;

            UsuarioDB upUser = new UsuarioDB();
            upUser.ActualizarUsuario(usuarioUp);
            getListaAdministrativos();
        }

    }

    public void eliminar() throws SNMPExceptions, SQLException 
    {
        Usuario us = this.getUsuario();

        LinkedList<Usuario> lista = new UsuarioDB().seleccionarUsuarioId(us.identificacion);
        if (lista == null) {

        } else {
            Usuario usuarioDel = lista.get(0);

            UsuarioDB upUser = new UsuarioDB();
            upUser.EliminarUsuario(usuarioDel);
            getListaAdministrativos();

        }
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

 



}
