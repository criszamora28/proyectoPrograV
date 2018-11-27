/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author crisz
 */
public class Recurso {
    public  String id;
    public  String tipo;
    public String descripcion;
    public int idUsuarioIngresoRegistro;
    public int idUsuarioEdicionRegistro;
    public Date fechaEdicionRegistro;
    public Date fechaIngresoRegistro;
    public boolean estadoRecurso;
    public boolean estadoRegistro;

    public Recurso(String id, String tipo, String descripcion, int idUsuarioIngresoRegistro, Date fechaIngresoRegistro, int idUsuarioEdicionRegistro, Date fechaEdicionRegistro, boolean estadoRecurso, boolean estadoRegistro) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.idUsuarioIngresoRegistro = idUsuarioIngresoRegistro;
        this.fechaIngresoRegistro = fechaIngresoRegistro;
        this.idUsuarioEdicionRegistro = idUsuarioEdicionRegistro;
        this.fechaEdicionRegistro = fechaEdicionRegistro;
        this.estadoRecurso = estadoRecurso;
        this.estadoRegistro = estadoRegistro;
    }

    public Recurso() {
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdUsuarioIngresoRegistro() {
        return idUsuarioIngresoRegistro;
    }

    public void setIdUsuarioIngresoRegistro(int idUsuarioIngresoRegistro) {
        this.idUsuarioIngresoRegistro = idUsuarioIngresoRegistro;
    }

    public Date getFechaIngresoRegistro() {
        return fechaIngresoRegistro;
    }

    public void setFechaIngresoRegistro(Date fechaIngresoRegistro) {
        this.fechaIngresoRegistro = fechaIngresoRegistro;
    }

    public int getIdUsuarioEdicionRegistro() {
        return idUsuarioEdicionRegistro;
    }

    public void setIdUsuarioEdicionRegistro(int idUsuarioEdicionRegistro) {
        this.idUsuarioEdicionRegistro = idUsuarioEdicionRegistro;
    }

    public Date getFechaEdicionRegistro() {
        return fechaEdicionRegistro;
    }

    public void setFechaEdicionRegistro(Date fechaEdicionRegistro) {
        this.fechaEdicionRegistro = fechaEdicionRegistro;
    }

    public boolean isEstadoRecurso() {
        return estadoRecurso;
    }

    public void setEstadoRecurso(boolean estadoRecurso) {
        this.estadoRecurso = estadoRecurso;
    }

    public boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
    
    
}
