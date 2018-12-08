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
public class Direccion {
    int idDireccion;
    Provincia Provincia;
    Canton Canton;
    Distrito Distrito;
    String otrasSeñas;
    boolean estadoRegistro;

    public Direccion() {
    }

    public Direccion(int idDireccion, Provincia Provincia, Canton Canton, Distrito Distrito, String otrasSeñas, boolean estadoRegistro) {
        this.idDireccion = idDireccion;
        this.Provincia = Provincia;
        this.Canton = Canton;
        this.Distrito = Distrito;
        this.otrasSeñas = otrasSeñas;
        this.estadoRegistro = estadoRegistro;
    }

    
    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Provincia getProvincia() {
        return Provincia;
    }

    public void setProvincia(Provincia Provincia) {
        this.Provincia = Provincia;
    }

    public Canton getCanton() {
        return Canton;
    }

    public void setCanton(Canton Canton) {
        this.Canton = Canton;
    }

    public Distrito getDistrito() {
        return Distrito;
    }

    public void setDistrito(Distrito Distrito) {
        this.Distrito = Distrito;
    }

    public String getOtrasSeñas() {
        return otrasSeñas;
    }

    public void setOtrasSeñas(String otrasSeñas) {
        this.otrasSeñas = otrasSeñas;
    }

    public boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
            
}
