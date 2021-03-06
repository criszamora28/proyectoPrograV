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

    public DistritoDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public DistritoDB() {
        super();
    }

    public Distrito buscarDistrito(String pIdDistrito,String pIdCanton,String pIdProvincia) throws SNMPExceptions {
        String select = "";
        Distrito oDistrito = null;

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT * from distrito where idDistrito='" + pIdDistrito + "' and "
                    + "idCanton='" + pIdCanton + "' and idProvincia='" + pIdProvincia + "'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String idPro = rsPA.getString("idProvincia");
                String idCanton = rsPA.getString("idCanton");
                String idDistrito = rsPA.getString("idDistrito");
                String nombreDistrito = rsPA.getString("nombreDistrito");

                oDistrito = new Distrito(idDistrito, idPro, idCanton, nombreDistrito);

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

        return oDistrito;
    }

    public LinkedList<Distrito> seleccionarDistritoPorCanton(String idProvincia, String idCanton) throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Distrito> listaDis = new LinkedList<Distrito>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT idProvincia,idCanton,idDistrito,"
                    + "nombreDistrito  FROM distrito where idProvincia='" + idProvincia + "' and "
                    + " idCanton='" + idCanton + "'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String idPro = rsPA.getString("idProvincia");
                String idCan = rsPA.getString("idCanton");
                String idDistrito = rsPA.getString("idDistrito");
                String nombreDistrito = rsPA.getString("nombreDistrito");

                Distrito oDistrito = new Distrito(idDistrito, idPro, idCan, nombreDistrito);

                listaDis.add(oDistrito);
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

        return listaDis;
    }

}
