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
    
  public int idDistrito;
  public int idProvincia;
  public int idCanton;
  public String nombreDistrito;

    
  public Distrito()
  {
  }
  public Distrito(int iddistrito, int idprovincia, int idcanton, String nombredistrito){
   this.idDistrito=iddistrito;
   this.idProvincia=idprovincia;
   this.idCanton=idcanton;
   this.nombreDistrito=nombredistrito;
  }
  
  public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public int getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(int idCanton) {
        this.idCanton = idCanton;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }
  
  
  
}
