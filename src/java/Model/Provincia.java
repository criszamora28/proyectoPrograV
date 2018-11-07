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
  public int idProvincia;
  public String nombreprovincia;
  
  public Provincia()
  {
  }
  public Provincia(int idProvincia, String nombreProvincia)
  {
     this.idProvincia=idProvincia;
     this.nombreprovincia=nombreProvincia;
  }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombreprovincia() {
        return nombreprovincia;
    }

    public void setNombreprovincia(String nombreprovincia) {
        this.nombreprovincia = nombreprovincia;
    }
  
}
