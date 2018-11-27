/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Ernesto PC
 */
public class Infraestructura {

    public String idInfraestructura;
    public String descripcion;
    public boolean disponibilidad;
    public Date fechaEdicionRegistro;
    public Date fechaIngresoRegistro;
    //resto consultar

    public Infraestructura() {
    }

    public String getIdInfraestructura() {
        return idInfraestructura;
    }

    public void setIdInfraestructura(String idInfraestructura) {
        this.idInfraestructura = idInfraestructura;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

 
    public Date getFechaEdicionRegistro() {
        return fechaEdicionRegistro;
    }

    public void setFechaEdicionRegistro(Date fechaEdicionRegistro) {
        this.fechaEdicionRegistro = fechaEdicionRegistro;
    }

    public Date getFechaIngresoRegistro() {
        return fechaIngresoRegistro;
    }

    public void setFechaIngresoRegistro(Date fechaIngresoRegistro) {
        this.fechaIngresoRegistro = fechaIngresoRegistro;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    
    
    
}
