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
public class Provincia 
{
  public String idProvincia;
  public String nombreprovincia;
  
  public Provincia()
  {
  }
  public Provincia(String idProvincia, String nombreProvincia)
  {
     this.idProvincia=idProvincia;
     this.nombreprovincia=nombreProvincia;
  }

    @Override
    public String toString() {
        return nombreprovincia ;
    }
    
        
    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombreprovincia() {
        return nombreprovincia;
    }

    public void setNombreprovincia(String nombreprovincia) {
        this.nombreprovincia = nombreprovincia;
    }
  
}
