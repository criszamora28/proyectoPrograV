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
public class InfraestructuraDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public InfraestructuraDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public InfraestructuraDB() {
    }

    public LinkedList<Infraestructura> seleccionarInfraestructura() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Infraestructura> listaInfraestructura = new LinkedList<Infraestructura>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "select idInfraestructura, disponibilidad, "
                    + "descripcion from Infraestructura where disponibilidad =1";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String idInfraestructura = rsPA.getString("idInfraestructura");
                String descripcion = rsPA.getString("descripcion");

                Infraestructura perInfraestructura = new Infraestructura();
                perInfraestructura.idInfraestructura = idInfraestructura;
                perInfraestructura.descripcion = descripcion;

                listaInfraestructura.add(perInfraestructura);
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

        return listaInfraestructura;
    }

    public LinkedList<Infraestructura> seleccionarInfraestructuraId(Infraestructura deas) throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Infraestructura> listaInfraestructura = new LinkedList<Infraestructura>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT idInfraestructura,disponibilidad, "
                    + "descripcion FROM Infraestructura where idInfraestructura="+"'"+ deas.idInfraestructura+"'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String idInfraestructura = rsPA.getString("idInfraestructura");
                String descripcion = rsPA.getString("descripcion");

                Infraestructura perInfraestructura = new Infraestructura();
                perInfraestructura.idInfraestructura = idInfraestructura;
                perInfraestructura.descripcion = descripcion;

                listaInfraestructura.add(perInfraestructura);
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

        return listaInfraestructura;
    }

    public void InsertarInfraestructura(Infraestructura infra) throws SNMPExceptions, SQLException {
        String strSQL = "";
        int valor = (infra.disponibilidad == true) ? 1 : 0;
        try {
            //Se obtienen los valores del objeto Departamento
            Infraestructura deas = new Infraestructura();
            deas = infra;

            strSQL
                    = "INSERT INTO Infraestructura (idInfraestructura,disponibilidad,"
                    + "descripcion) VALUES ("
                    +"'"+ deas.idInfraestructura+"'" + ","
                    + valor + ","
                    + "'" + deas.descripcion + "'"
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

    public void ActualizarInfraestructura(Infraestructura infra) throws SNMPExceptions, SQLException {
        String strSQL = "";
        int valor = (infra.disponibilidad == true) ? 1 : 0;
        try {
            //Se obtienen los valores del objeto Departamento
            Infraestructura deas = new Infraestructura();
            deas = infra;

            strSQL
                    = "update Infraestructura"
                    + "  set descripcion=" + "'" + deas.descripcion + "'" + ","
                    + "disponibilidad=" + valor
                    + "  where idInfraestructura= " + "'"+deas.idInfraestructura+"'";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public void EliminarInfraestructura(Infraestructura infra) throws SNMPExceptions, SQLException {
        String strSQL = "";
        int valor = (infra.disponibilidad == true) ? 1 : 0;
        try {
            //Se obtienen los valores del objeto Departamento
            Infraestructura deas = new Infraestructura();
            deas = infra;

            strSQL
                    = "update Infraestructura"
                    + " set disponibilidad=" + 0
                    + "  where idInfraestructura= " +"'"+ deas.idInfraestructura+"'";

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

}
