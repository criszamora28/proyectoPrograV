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
public class ProvinciaDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<Provincia> listaP = new LinkedList<Provincia>();

    public ProvinciaDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public ProvinciaDB() {
        super();
    }

    public Provincia buscarProvincia(String pIdProvincia) throws SNMPExceptions {
        String select = "";
        Provincia oProvincia = null;

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT * from provincia where idProvincia='" + pIdProvincia + "'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String idPro = rsPA.getString("idProvincia");
                
                String nombreProvincia = rsPA.getString("nombreprovincia");

                oProvincia = new Provincia(idPro, nombreProvincia);

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

        return oProvincia;
    }

    public LinkedList<Provincia> moTodo() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Provincia> listaPro = new LinkedList<Provincia>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT idProvincia,"
                    + "nombreprovincia FROM Provincia";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String idProvincia = rsPA.getString("idProvincia");
                String nombreProvincia = rsPA.getString("nombreprovincia");

                Provincia perProvincia = new Provincia(idProvincia, nombreProvincia);
                listaPro.add(perProvincia);
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

        return listaPro;
    }

}
