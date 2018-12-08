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
public class TipoIdentificacionDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<TipoIdentificacion> listaP = new LinkedList<TipoIdentificacion>();

    public TipoIdentificacionDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public TipoIdentificacionDB() {
        super();
    }

    public LinkedList<TipoIdentificacion> moTodo() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<TipoIdentificacion> listaPro = new LinkedList<TipoIdentificacion>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT id,"
                    + "tipo FROM tipoIdentificacionUsuario";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int id = rsPA.getInt("id");
                String tipo = rsPA.getString("tipo");

                TipoIdentificacion perTipo = new TipoIdentificacion();
                perTipo.id = id;
                perTipo.tipo = tipo;

                listaPro.add(perTipo);
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

    public LinkedList<TipoIdentificacion> seleccionarId(int idt) throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<TipoIdentificacion> listaPro = new LinkedList<TipoIdentificacion>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT id,"
                    + "tipo FROM tipoIdentificacionUsuario where id=" + idt;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int id = rsPA.getInt("id");
                String tipo = rsPA.getString("tipo");

                TipoIdentificacion perTipo = new TipoIdentificacion();
                perTipo.id = id;
                perTipo.tipo = tipo;

                listaPro.add(perTipo);
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
