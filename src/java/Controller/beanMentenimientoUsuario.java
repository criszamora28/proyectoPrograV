/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author crisz
 */
@Named(value = "beanMentenimientoUsuario")
@SessionScoped
public class beanMentenimientoUsuario implements Serializable {

    private List<Usuario> listaUsuarios;
     
    private Usuario Usuario;
    
    @PostConstruct
    public void init() {
        Usuario = new Usuario("Cris", "Zamora", "Reyes", "Ninguna");
        listaUsuarios = new LinkedList<>();
        listaUsuarios.add(Usuario);
        
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
