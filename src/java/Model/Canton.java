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
public class Canton
{
   public String idProvincia;
   public String idCanton;
   public String nombreCanton;
   
   public Canton(String idprovincia, String idcanton, String nombrecanton)
   {
    this.idProvincia=idprovincia;
    this.idCanton=idcanton;
    this.nombreCanton=nombrecanton;
   }

    public Canton() {
    }

    @Override
    public String toString() {
        return nombreCanton;
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

    public String getNombreCanton() {
        return nombreCanton;
    }

    public void setNombreCanton(String nombreCanton) {
        this.nombreCanton = nombreCanton;
    }
   
}
