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
   public int idProvincia;
   public int idCanton;
   public String nombreCanton;
   
   public Canton(int idprovincia, int  idcanton, String nombrecanton)
   {
    this.idProvincia=idprovincia;
    this.idCanton=idcanton;
    this.nombreCanton=nombrecanton;
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

    public String getNombreCanton() {
        return nombreCanton;
    }

    public void setNombreCanton(String nombreCanton) {
        this.nombreCanton = nombreCanton;
    }
   
}
