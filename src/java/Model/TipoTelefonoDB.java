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
public class TipoTelefonoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<TipoIdentificacion> listaP = new LinkedList<TipoIdentificacion>();

    public TipoTelefonoDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public TipoTelefonoDB() {
        super();
    }

    public LinkedList<TipoTelefono> selectTipoTelefono() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<TipoTelefono> listaPro = new LinkedList<TipoTelefono>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT * from tipoTelefonoUsuario";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int id = rsPA.getInt("id");
                String tipo = rsPA.getString("tipo");
                 String desc = rsPA.getString("descripcion");

                TipoTelefono oTipoTelefono = new TipoTelefono();
                oTipoTelefono.id = id;
                oTipoTelefono.tipo = tipo;
                oTipoTelefono.descripcion = desc;

                listaPro.add(oTipoTelefono);
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

    public LinkedList<TipoTelefono> seleccionarTipoTelefonoPorId(int idTipo) throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<TipoTelefono> listaPro = new LinkedList<TipoTelefono>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "select * from tipoTelefonoUsuario";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int id = rsPA.getInt("id");
                String tipo = rsPA.getString("tipo");
                String desc = rsPA.getString("descripcion");

                TipoTelefono oTipoTelefono = new TipoTelefono();
                oTipoTelefono.id = id;
                oTipoTelefono.tipo = tipo;
                oTipoTelefono.descripcion = desc;

                listaPro.add(oTipoTelefono);
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
