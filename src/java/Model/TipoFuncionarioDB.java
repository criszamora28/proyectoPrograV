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
public class TipoFuncionarioDB {
     private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;  

    private LinkedList<TipoFuncionario> listaP = new LinkedList<TipoFuncionario>();  
    
    
 public TipoFuncionarioDB (Connection conn) {
        accesoDatos = new AccesoDatos();    
        accesoDatos.setDbConn(conn);
    }
    
    public TipoFuncionarioDB() {
        super();
    }
        
    public LinkedList<TipoFuncionario> seleccionarTiposFuncionarios() throws SNMPExceptions,SQLException {
      String select = "";
      LinkedList<TipoFuncionario> listaFun = new LinkedList<TipoFuncionario>();
          //TipoFuncionario n=  new  TipoFuncionario();
          try {
    
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select =
                      "SELECT id,tipo," +
                            "descripcion FROM tipoFuncionario"; 
                      
                      
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                int id = rsPA.getInt("id");
                String tipo = rsPA.getString("tipo");
                
                
               TipoFuncionario tipoU = new TipoFuncionario();
               tipoU.Id=id;
               tipoU.TipoUsuario=tipo;
              
                 listaFun.add(tipoU);
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
         
          return listaFun;
      }
       
}
