/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Ernesto PC
 */
public class Usuario {
    
 //actualizar datos del usuario
    public String nombre;
    public String apellido1;
    public String apellido2;
    public String direccion;


    
    public Usuario()
    {}
    public Usuario(String nombre,String apellido1,String apellido2,String direccion)
    {
    this.nombre=nombre;
    this.apellido1=apellido1;
    this.apellido2=apellido2;
    this.direccion=direccion;
    }

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

      public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
