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
    public int identificacion;
    public ProgramaDeas ProgramaDeas;
    public TipoFuncionario tipoFuncionario;
    public String contrasena;
    public String fechaNacimiento;
    public String nombre;
    public String apellido1;
    public String apellido2;
    public Direccion direccion;
    public String telefono;
    public String correo;
    public boolean estado;
    public boolean cuentaCompleta;
    public TipoIdentificacion tipoIdentificacion;
    public TipoTelefono tipotelefono;
    

    public Usuario() {
    }

    public Usuario(int identificacion, ProgramaDeas ProgramaDeas, TipoFuncionario tipoFuncionario, String contrasena, String fechaNacimiento, String nombre, String apellido1, String apellido2, Direccion direccion, String telefono, String correo, boolean estado, boolean cuentaCompleta, TipoIdentificacion tipoIdentificacion) {
        this.identificacion = identificacion;
        this.ProgramaDeas = ProgramaDeas;
        this.tipoFuncionario = tipoFuncionario;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
        this.cuentaCompleta = cuentaCompleta;
        this.tipoIdentificacion = tipoIdentificacion;
    }

    

    

    public TipoTelefono getTipotelefono() {
        return tipotelefono;
    }

    public ProgramaDeas getProgramaDeas() {
        return ProgramaDeas;
    }

    public void setProgramaDeas(ProgramaDeas ProgramaDeas) {
        this.ProgramaDeas = ProgramaDeas;
    }

    public void setTipotelefono(TipoTelefono tipotelefono) {
        this.tipotelefono = tipotelefono;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isCuentaCompleta() {
        return cuentaCompleta;
    }

    public void setCuentaCompleta(boolean cuentaCompleta) {
        this.cuentaCompleta = cuentaCompleta;
    }
    
    
}
