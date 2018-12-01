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
public class DetalleReservacion {
    public int id;
    public String Reservacion;
    public String Recurso;
    public String Infraestructura;
    public int idUsuarioIngresaRegistro;
    public int idUsuarioIngresoRegistro;
    public Date fechaIngresoRegistro;
    public int idUsuarioEdicionRegistro;
    public Date fechaEdicionRegistro;
    public boolean estadoRegistro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReservacion() {
        return Reservacion;
    }

    public void setReservacion(String Reservacion) {
        this.Reservacion = Reservacion;
    }

    public String getRecurso() {
        return Recurso;
    }

    public void setRecurso(String Recurso) {
        this.Recurso = Recurso;
    }

    public String getInfraestructura() {
        return Infraestructura;
    }

    public void setInfraestructura(String Infraestructura) {
        this.Infraestructura = Infraestructura;
    }

    
    public int getIdUsuarioIngresaRegistro() {
        return idUsuarioIngresaRegistro;
    }

    public void setIdUsuarioIngresaRegistro(int idUsuarioIngresaRegistro) {
        this.idUsuarioIngresaRegistro = idUsuarioIngresaRegistro;
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
