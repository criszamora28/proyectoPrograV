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
