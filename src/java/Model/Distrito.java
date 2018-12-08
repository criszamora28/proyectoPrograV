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
public class Distrito {
    
  public String idDistrito;
  public String idProvincia;
  public String idCanton;
  public String nombreDistrito;

    
  public Distrito()
  {
  }
  public Distrito(String iddistrito, String idprovincia, String idcanton, String nombredistrito){
   this.idDistrito=iddistrito;
   this.idProvincia=idprovincia;
   this.idCanton=idcanton;
   this.nombreDistrito=nombredistrito;
  }

    @Override
    public String toString() {
        return nombreDistrito;
    }
  
  public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(String idCanton) {
        this.idCanton = idCanton;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }
  
  
  
}
