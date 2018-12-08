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
 * @author crisz
 */
public class DireccionDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<Distrito> listaP = new LinkedList<Distrito>();

    public DireccionDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public DireccionDB() {
        super();
    }

    public void insertarDireccion(Direccion pDireccion) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Departamento

            strSQL
                    = "INSERT INTO direccion(idProvincia,idCanton,"
                    + "idDistrito,otrasSennas) VALUES "
                    + "(" + "'" + pDireccion.Provincia.getIdProvincia() + "'" + ","
                    + "'" + pDireccion.Canton.getIdCanton() + "'" + ","
                    + "'" + pDireccion.Distrito.getIdDistrito() + "'" + ","
                    + "'" + pDireccion.otrasSeñas + "'"
                    + ")";

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
    
    public int obtenerUltimoIdDireccion() throws SNMPExceptions, SQLException {
        String select = "";
        int idDireccion = 0;

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT max(idDireccion) as id from direccion";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                idDireccion = rsPA.getInt("id");
                

               
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }

        if (idDireccion != 0) {
            return idDireccion;
        }
        
        return idDireccion;
    }
}
