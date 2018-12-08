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
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

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
    int tipoFun;

    
    
   public LinkedList<SelectItem> getListaTipoFuncionario()throws SNMPExceptions, SQLException{
        int id=0;
        String tipo="";
        
        LinkedList<TipoFuncionario> lista = new LinkedList<TipoFuncionario>();
        TipoFuncionarioDB fDB = new TipoFuncionarioDB();
        TipoFuncionario n= new TipoFuncionario();
       lista = fDB.seleccionarTiposFuncionarios();
    
        
        LinkedList resultList = new LinkedList();
   
        for (Iterator iter= lista.iterator(); iter.hasNext();) {
        
            TipoFuncionario tipoFun = (TipoFuncionario) iter.next();
            id=tipoFun.getId();
            tipo=tipoFun.getTipoUsuario();
            resultList.add(new SelectItem(id, tipo));
         }  
         return resultList; 
        
    }
   
    public void autenticar(){
     int tipo=0;
     int identi=0;
     String contrasena="";
     tipo=this.getTipoFun();
     identi=Integer.parseInt(this.getIdenticacion());
     contrasena=this.getContrasena();
     
       try{
           LinkedList<Usuario>lista=new UsuarioDB().validarUsuario(identi,tipo,contrasena);
           Usuario usuario= lista.get(0);
           
           if (usuario != null) {
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
               FacesContext.getCurrentInstance().getExternalContext().redirect("Principal.xhtml");
               FacesContext context = FacesContext.getCurrentInstance();
               context.addMessage(null, new FacesMessage("Datos incorrectos!", "Datos ingresados correctamente!"));
           } else {
               FacesContext context = FacesContext.getCurrentInstance();
               context.addMessage(null, new FacesMessage("Datos incorrectos!", "Usuario o contraseña incorrecta!"));
           }
           
       }catch (Exception e){
       
       }
       
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

    public int getTipoFun() {
        return tipoFun;
    }

    public void setTipoFun(int tipoFun) {
        this.tipoFun = tipoFun;
    }
    public beanLogin() {
    }
    
}
