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

    private String idInfraestructura;
    private String descripcion;
    private int idUsuarioIngresoRegistro;
    private Date fechaIngresoRegistro;
    private int idUsuarioEdicionRegistro;
    private Date fechaEdicionRegistro;
    private boolean eatdoInfraestructura;
    private boolean estadoRegistro;

    public Infraestructura() {
    }

    @Override
    public String toString(){
        return this.idInfraestructura + " " + this.getDescripcion();
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

    public boolean isEatdoInfraestructura() {
        return eatdoInfraestructura;
    }

    public void setEatdoInfraestructura(boolean eatdoInfraestructura) {
        this.eatdoInfraestructura = eatdoInfraestructura;
    }

    public boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    

}
