/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Provincia;
import Model.ProvinciaDB;
import Model.TipoFuncionario;
import Model.TipoFuncionarioDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Ernesto PC
 */
@Named(value = "beanLogin")
@SessionScoped
public class beanLogin implements Serializable {

    /**
     * Creates a new instance of beanLogin
     */
    String identicacion;
    String contrasena;
    TipoFuncionario tipoFun;

    
    
      public LinkedList<SelectItem> getListaTipoFuncionario()throws SNMPExceptions, SQLException{
        int id=0;
        String tipo="";
        
        LinkedList<TipoFuncionario> lista = new LinkedList<TipoFuncionario>();
        TipoFuncionarioDB fDB = new TipoFuncionarioDB();
        
        lista = fDB.seleccionarTiposFuncionarios();
        
        LinkedList resultList = new LinkedList();
   //     resultList.add(new SelectItem(0, "Seleccione Provincia"));
        
        for (Iterator iter= lista.iterator(); iter.hasNext();) {
        
            TipoFuncionario tipoFun = (TipoFuncionario) iter.next();
            id=tipoFun.getId();
            tipo=tipoFun.getTipoUsuario();
            resultList.add(new SelectItem(id, tipo));
         }  
         return resultList; 
        
    }
    
    public String getIdenticacion() {
        return identicacion;
    }

    public void setIdenticacion(String identicacion) {
        this.identicacion = identicacion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public TipoFuncionario getTipoFun() {
        return tipoFun;
    }

    public void setTipoFun(TipoFuncionario tipoFun) {
        this.tipoFun = tipoFun;
    }
    public beanLogin() {
    }
    
}
