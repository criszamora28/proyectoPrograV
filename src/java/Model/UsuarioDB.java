/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.SNMPExceptions;
import DAO.AccesoDatos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Ernesto PC
 */
public class UsuarioDB 
{
     private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;  

    private LinkedList<Canton> listaP = new LinkedList<Canton>();  
    
    
 public UsuarioDB (Connection conn) {
        accesoDatos = new AccesoDatos();    
        accesoDatos.setDbConn(conn);
    }
    
    public UsuarioDB() {
        super();
    }
    
    
    
    
    public void InsertarUsuario(Usuario usu) throws SNMPExceptions, SQLException {
        String strSQL = "";

         
         try {
         //Se obtienen los valores del objeto Departamento
        Usuario usuario = new Usuario();
        usuario=usu;
        
            strSQL = 
            "INSERT INTO usuario1(nombre,apellido1,"
                    + "apellido2) VALUES "
         + "(" + "'" + usuario.getNombre()+ "'" + "," 
               + "'"+ usuario.getApellido1()+"'"+","
               + "'"+ usuario.getApellido2()+"'"+ ")";
               //+ "'"+ usuario.getDireccion()+"'" + ")";
                            
   
                     //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);


        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
    
    
      public  LinkedList<Usuario> moTodo() throws SNMPExceptions,SQLException {
      String select = "";
      LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();
          
          try {
    
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select =
                      "SELECT idProvincia,idCanton," +
                            "nombrecanton  FROM usuario1"; 
                      
                      
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                String nombre = rsPA.getString("nombre");
                String apellido1 = rsPA.getString("apellido1");
                String apellido2 = rsPA.getString("apellido2");
                String direccion=rsPA.getString("direccion");
                
                
               Usuario perUsuarios = new Usuario(nombre,apellido1, apellido2,direccion);
                 listaUsuarios.add(perUsuarios);
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
         
          return listaUsuarios;
      }
    
      
        public  LinkedList<Usuario> validarUsuario(int id, int tipo, String contra) throws SNMPExceptions,SQLException {
  
      LinkedList<Usuario> listaUsu = new LinkedList<Usuario>();
    
          String select = ""; 
          try {
    
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select =
                      "SELECT identificacion,nombre, apellido1," +
                            "apellido2 FROM usuario WHERE identificacion="+id+"and idTipoFuncionario="+tipo+" and contra="+"'"+contra+"'";
                      
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                int identificacion = rsPA.getInt("identificacion");
                String nombre = rsPA.getString("nombre");
                String apellido1 = rsPA.getString("apellido1");
                String apellido2= rsPA.getString("apellido2");
                
                
               Usuario usuObetenido = new Usuario();
               usuObetenido.identificacion=identificacion;
               usuObetenido.nombre=nombre;
               usuObetenido.apellido1=apellido1;
               usuObetenido.apellido2=apellido2;
                listaUsu.add(usuObetenido);
                
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
         
          return listaUsu;
      }
    
}
