/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.ControladorCorreo;
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
@Named(value = "beanSolicitudes")
@SessionScoped
public class beanSolicitudes implements Serializable {

     boolean disable;
    Usuario usuario;

    public beanSolicitudes() {
        disable = true;
    }

    public List<Usuario> getListaPendientes() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<Usuario> lista = new LinkedList<Usuario>();
        UsuarioDB fDB = new UsuarioDB();
        Usuario n = new Usuario();
        lista = fDB.seleccionarSolicitudes();

        return lista;
    }

    public void aceptarSolicitud() throws SNMPExceptions, SQLException {
       Usuario us = this.getUsuario();
        LinkedList<Usuario> lista = new UsuarioDB().seleccionarUsuarioId(us.identificacion);
        Usuario seleccionado=lista.get(0);
        
        //ACA llama a la clase enviaCorreo, agarra el correo del usuario y le adjunta la clave generada. Tambien se actualiza el usarioDB
        ControladorCorreo controlador= new ControladorCorreo();
        controlador.enviarCorreo(seleccionado.correo);//cambiar  metodo para que reciba como parametros el correo que esta el lista.get(0);
    }

    public void rechazarSolicitud() throws SNMPExceptions, SQLException {
        Usuario us = this.getUsuario();
        LinkedList<Usuario> lista = new UsuarioDB().seleccionarUsuarioId(us.identificacion);
        
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
