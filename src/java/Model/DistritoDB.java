/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Ernesto PC
 */
public class DistritoDB {
    
    
     private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;  

    private LinkedList<Distrito> listaP = new LinkedList<Distrito>();  
    
    
 public DistritoDB (Connection conn) {
        accesoDatos = new AccesoDatos();    
        accesoDatos.setDbConn(conn);
    }
    
    public DistritoDB() {
        super();
    }
    
    public  LinkedList<Distrito> moTodo() throws SNMPExceptions,SQLException {
      String select = "";
      LinkedList<Distrito> listaDis = new LinkedList<Distrito>();
          
          try {
    
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select =
                      "SELECT idProvincia,idCanton,idDistrito," +
                            "nombreDistrito  FROM Distrito"; 
                      
                      
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                int idProvincia = rsPA.getInt("idProvincia");
                int idCanton = rsPA.getInt("idCanton");
                int idDistrito=rsPA.getInt("idDistrito");
                String nombrecanton = rsPA.getString("nombreDistrito");
                
                
               Distrito perDistrito = new Distrito(idProvincia,idCanton,idDistrito,nombrecanton);
                 listaDis.add(perDistrito);
              }
              rsPA.close();
              
          } catch (SQLException e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage(), e.getErrorCode());
          }catch (Exception e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage());
          } finally {
              
          }
         
          return listaDis;
      }
    
 
    
}
