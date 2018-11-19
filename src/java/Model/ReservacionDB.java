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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

/**
 *
 * @author crisz
 */
public class ReservacionDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public ReservacionDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public ReservacionDB() {
        super();
    }

    public void InsertarReservacion(Reservacion pReservacion) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Reservacion
            Reservacion oReservacion = new Reservacion();
            oReservacion = pReservacion;
            int todoElDia = pReservacion.todoElDia ? 1 : 0;
            int editable = pReservacion.editable ? 1 : 0;

            strSQL
                    = "INSERT INTO reservacion(id,"
                    + "titule,astartDate, endDate, allDay,editable) VALUES "
                    + "(" + "'" + pReservacion.id + "'" + ","
                    + "'" + pReservacion.titulo + "'" + ","
                    + "'" + pReservacion.fechaInicio + "'" + ","
                    + "'" + pReservacion.fechaFinal + "'" + ","
                    + todoElDia + ","
                    + editable
                    + ")";
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public LinkedList<Reservacion> selectReservacion() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Reservacion> listaReservaciones = new LinkedList<Reservacion>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT id,titule,startDate,endDate,allDay FROM reservacion";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "EEE MMM dd HH:mm:ss zz yyyy");
            
            
            Date fechaFinal = dateFormat.parse(rsPA.getString("endDate"));
            Date fechaInicio = dateFormat.parse(rsPA.getString("startDate"));
            
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                Reservacion oReservacion = new Reservacion();
                oReservacion.id = rsPA.getString("id");
                oReservacion.titulo = rsPA.getString("titule");
                oReservacion.fechaInicio = fechaInicio;
                oReservacion.fechaFinal = fechaFinal;
                oReservacion.todoElDia = Boolean.parseBoolean(rsPA.getString("allDay"));

                listaReservaciones.add(oReservacion);
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

        return listaReservaciones;
    }

}
