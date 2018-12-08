/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author crisz
 */
public class TipoTelefono {
    int id;
    String tipo;
    String descripcion;
    int estadoRegistro;

    public TipoTelefono(int id, String tipo, String descripcion, int estadoRegistro) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estadoRegistro = estadoRegistro;
    }

    public TipoTelefono() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
}
