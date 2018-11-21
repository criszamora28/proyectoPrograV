/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Ernesto PC
 */
public class Infraestructura
{
 public int idInfraestructura;
 public String  descripcion;
 public int disponibilidad;
 //resto consultar
 public Infraestructura()
 {
 }

    public int getIdInfraestructura() {
        return idInfraestructura;
    }

    public void setIdInfraestructura(int idInfraestructura) {
        this.idInfraestructura = idInfraestructura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
 
 
}
