/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.TipoFuncionario;
import Model.TipoFuncionarioDB;
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

    private List<Usuario> listaUsuarios;
     
    private Usuario Usuario;
    
//    @PostConstruct
//    public void init() {
//        Usuario = new Usuario("Cris", "Zamora", "Reyes", "Ninguna");
//        listaUsuarios = new LinkedList<>();
//        listaUsuarios.add(Usuario);
//        
//    }
    public List<Usuario> getListaAdministrativos()throws SNMPExceptions, SQLException{
        int id=0;
        String tipo="";
        
        LinkedList<Usuario> lista = new LinkedList<Usuario>();
        UsuarioDB fDB = new UsuarioDB();
        Usuario n= new Usuario();
       lista = fDB.seleccionarUsuariosAdministrativos();
    
        
//        LinkedList resultList = new LinkedList();
//   
//        for (Iterator iter= lista.iterator(); iter.hasNext();) {
//        
//            Usuario tipoFun = (Usuario) iter.next();
//            id=tipoFun.getId();
//            tipo=tipoFun.getTipoUsuario();
//            resultList.add(new SelectItem(id, tipo));
//         }  
         return lista; 
        
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
