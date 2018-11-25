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
public class Reservacion {
    public String id;
    public Usuario Usuario;
    public int TipoReservacion;
    public String titulo;
    public Date fechaInicio;
    public Date fechaFinal;
    public boolean todoElDia;
    public boolean editable;
    public boolean estadoSolicitud;
    public int idUsuarioIngresoRegistro;
    public Date fechaIngresoRegistro;
    public int idUsuarioEdicionRegistro;
    public Date fechaEdicionRegistro;
    public boolean estadoRegistro;

    public Reservacion() {
    }

    public int getTipoReservacion() {
        return TipoReservacion;
    }

    public void setTipoReservacion(int TipoReservacion) {
        this.TipoReservacion = TipoReservacion;
    }

    public boolean isEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(boolean estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean isTodoElDia() {
        return todoElDia;
    }

    public void setTodoElDia(boolean todoElDia) {
        this.todoElDia = todoElDia;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEstadoReservacion() {
        return estadoSolicitud;
    }

    public void setEstadoReservacion(boolean estadoReservacion) {
        this.estadoSolicitud = estadoReservacion;
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

    public boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
}
