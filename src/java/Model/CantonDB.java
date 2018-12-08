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
public class CantonDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<Canton> listaP = new LinkedList<Canton>();

    public CantonDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public CantonDB() {
        super();
    }
    
    public Canton buscarcanton(String pidCanton,String pidProvincia) throws SNMPExceptions{
        String select = "";
        Canton perCanton = null;

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT * from canton where idCanton='" + pidCanton + "' and idProvincia='" + pidProvincia + "'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String idPro = rsPA.getString("idProvincia");
                String idCanton = rsPA.getString("idCanton");
                String nombrecanton = rsPA.getString("nombrecanton");

                perCanton = new Canton(idPro, idCanton, nombrecanton);
                
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

        return perCanton;
    }

    public LinkedList<Canton> seleccionarCantonesPorProvincia(String idProvincia) throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Canton> listaCan = new LinkedList<Canton>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT idProvincia,idCanton,"
                    + "nombrecanton  FROM Canton where idProvincia='" + idProvincia + "'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String idPro = rsPA.getString("idProvincia");
                String idCanton = rsPA.getString("idCanton");
                String nombrecanton = rsPA.getString("nombrecanton");

                Canton perCanton = new Canton(idPro, idCanton, nombrecanton);
                listaCan.add(perCanton);
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

        return listaCan;
    }

}
