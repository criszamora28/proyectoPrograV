/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Canton;
import Model.CantonDB;
import Model.Distrito;
import Model.DistritoDB;
import Model.Provincia;
import Model.ProvinciaDB;
import Model.Usuario;
import Model.UsuarioDB;
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
@Named(value = "beanRegistro")
@SessionScoped
public class beanRegistro implements Serializable {

    //Canton
    int idCanton;
    String nombrecanton;
   //provincia
    int idProvincia;
    String nombreProvincia;
   //distrito
    int idDistrito;
    String nombreDistrito;
    //Usuario
    String nombre;
    String apellido1;
    String apellido2;
   /// String  direccion;



    
    LinkedList<SelectItem> listaCanton = new LinkedList<>();
    LinkedList<SelectItem> listaProvincia= new LinkedList<>();
    LinkedList<SelectItem> listaDistrito= new LinkedList<>();
    
      
   
    /**
     * Creates a new instance of beanRegistro
     */
    //EJEMPLO DE TABLA
 public LinkedList<Canton> getListaTablaProvincia()throws SNMPExceptions, SQLException {
        
        LinkedList<Canton> lista = new LinkedList<Canton>();
        CantonDB pDB = new CantonDB();
        
        lista = pDB.moTodo();
        
        LinkedList resultLista = new LinkedList();
           
        resultLista=lista;       
        return resultLista; 

    }

   
     public beanRegistro(){
     }

     
     
   public void insertarUsuario() throws SNMPExceptions, SQLException
   {
   Usuario nuevoUsuario= new Usuario(this.getNombre(),this.getApellido1(), this.getApellido2(),"hola");
   UsuarioDB usuDB= new UsuarioDB();
   
   usuDB.InsertarUsuario(nuevoUsuario);
   }


    public LinkedList<SelectItem> getListaCanton()throws SNMPExceptions, SQLException{
    int idCanton=0;
    String nombrecanton="";
        
        LinkedList<Canton> lista = new LinkedList<Canton>();
        CantonDB cDB = new CantonDB();
        
        lista = cDB.moTodo();
        
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Canton"));
        
        for (Iterator iter= lista.iterator(); iter.hasNext();) {
        
            Canton pro = (Canton) iter.next();
            idCanton=pro.getIdCanton();
            nombrecanton=pro.getNombreCanton();
            resultList.add(new SelectItem(idCanton, nombrecanton));
         }         
         return resultList; 
        
    }
        public LinkedList<SelectItem> getListaProvincia()throws SNMPExceptions, SQLException{
        int idProvincia=0;
        String nombreProvincia="";
        
        LinkedList<Provincia> lista = new LinkedList<Provincia>();
        ProvinciaDB pDB = new ProvinciaDB();
        
        lista = pDB.moTodo();
        
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Provincia"));
        
        for (Iterator iter= lista.iterator(); iter.hasNext();) {
        
            Provincia pro = (Provincia) iter.next();
            idProvincia=pro.getIdProvincia();
            nombreProvincia=pro.getNombreprovincia();
            resultList.add(new SelectItem(idProvincia, nombreProvincia));
         }         
         return resultList; 
        
    }
     
     public LinkedList<SelectItem> getListaDistrito()throws SNMPExceptions, SQLException{
        int idDistrito=0;
        String nombreDistrito="";
        
        LinkedList<Distrito> lista = new LinkedList<Distrito>();
        DistritoDB dDB = new DistritoDB();
        
        lista = dDB.moTodo();
        
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione distrito"));
        
        for (Iterator iter= lista.iterator(); iter.hasNext();) {
        
            Distrito pro = (Distrito) iter.next();
            idDistrito=pro.getIdDistrito();
            nombreDistrito=pro.getNombreDistrito();
            resultList.add(new SelectItem(idDistrito, nombreDistrito));
         }         
         return resultList; 
        
    }    
    
    
    
    
    
    
    //setters and getters
        public int getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(int idCanton) {
        this.idCanton = idCanton;
    }

    public String getNombrecanton() {
        return nombrecanton;
    }

    public void setNombrecanton(String nombrecanton) {
        this.nombrecanton = nombrecanton;
    }

  
        public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }
    
    public void setListCanton(LinkedList<SelectItem>listcant) {
        this.listaCanton= listcant;
    }
       public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }
    
    //sets and gets usuarios 
        public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
}
