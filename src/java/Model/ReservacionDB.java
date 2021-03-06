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
            int estadoResgistro = pReservacion.estadoRegistro ? 1 : 0;

            strSQL
                    = "INSERT INTO reservacion(id,idUsuario,idTipoReservacion,"
                    + "idUsuarioIngresoRegistro,fechaIngresoRegistro,estadoRegistro) VALUES"
                    + "(" + "'" + pReservacion.id + "'" + ","
                    + "'" + pReservacion.Usuario.identificacion + "'" + ","
                    + pReservacion.TipoReservacion.getId() + ","
                    + "'" + pReservacion.idUsuarioIngresoRegistro + "'" + ","
                    + "'" + pReservacion.fechaIngresoRegistro + "'" + ","
                    + estadoResgistro
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
    
    public void ModificarReservacion(Reservacion pReservacion) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
           

            strSQL
                    = "update reservacion "
                    + " set idTipoReservacion=" + pReservacion.TipoReservacion.Id + "," 
                    + " idUsuarioEdicionRegistro=" + pReservacion.Usuario.identificacion + "," 
                    + " fechaEdicionRegistro='" + pReservacion.fechaEdicionRegistro + "'" 
                    + " where id='" + pReservacion.id + "'" + " and idUsuario=" + pReservacion.Usuario.identificacion;
                    
                    
                    
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
    
    public void EliminarDetallesReservacion(Reservacion pReservacion) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
           

            strSQL
                    = "delete from detalleRservacion where idReservacion='" + pReservacion.id + "'";
                    
                    
                    
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
    
    public void InsertarDetalleReservacionRecurso(DetalleReservacion pDetalle) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            int todoElDia = pDetalle.todoElDia ? 1 : 0;
            int editable = pDetalle.editable ? 1 : 0;
            int estadoReservacion = pDetalle.estadoSolicitud ? 1 : 0;
          

            strSQL
                    = "INSERT INTO detalleRservacion(idReservacion,idRecurso"
                    + ",titule,startDate,endDate,allDay,editable,estadoSolicitud,estadoRegistro) VALUES"
                    + "(" + "'" + pDetalle.Reservacion.id + "'" + ","
                    + "'" + pDetalle.Recurso.id + "'" + ","
                    + "'" + pDetalle.titulo + "'" + ","
                    + "'" + pDetalle.fechaInicio + "'" + ","
                    + "'" + pDetalle.fechaFinal + "'" + ","
                    + todoElDia + ","
                    + editable + ","
                    + estadoReservacion + ","
                    + 1
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
    
    public void InsertarDetalleReservacionInfraestructura(DetalleReservacion pDetalle) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            
            int todoElDia = pDetalle.todoElDia ? 1 : 0;
            int editable = pDetalle.editable ? 1 : 0;
            int estadoReservacion = pDetalle.estadoSolicitud ? 1 : 0;

            
            strSQL
                    = "INSERT INTO detalleRservacion(idReservacion,idInfraestructura"
                    + ",titule,startDate,endDate,allDay,editable,estadoSolicitud,estadoRegistro) VALUES"
                    + "(" + "'" + pDetalle.Reservacion.id + "'" + ","
                    + "'" + pDetalle.Infraestructura.idInfraestructura + "'" + ","
                    + "'" + pDetalle.titulo + "'" + ","
                    + "'" + pDetalle.fechaInicio + "'" + ","
                    + "'" + pDetalle.fechaFinal + "'" + ","
                    + todoElDia + ","
                    + editable + ","
                    + estadoReservacion + ","
                    + 1
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
                    = "SELECT id,idUsuario,titule,startDate,endDate,allDay,editable,estadoSolicitud,"
                    + "estadoRegistro FROM reservacion";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            UsuarioDB oUsuarioDB = new UsuarioDB();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "EEE MMM dd HH:mm:ss zz yyyy",Locale.getDefault());
            //Se llena el arryaList con las reservaciones 
            while (rsPA.next()) {
                
                int todoElDia = Integer.parseInt(rsPA.getString("allDay"));
                int editable = Integer.parseInt(rsPA.getString("editable"));
                int estadoReservacion = Integer.parseInt(rsPA.getString("estadoSolicitud"));
                int estadoResgistro = Integer.parseInt(rsPA.getString("estadoRegistro"));
                Date fechaFinal = dateFormat.parse(rsPA.getString("endDate"));
                Date fechaInicio = dateFormat.parse(rsPA.getString("startDate"));
                LinkedList<Usuario> listaUsuario = new LinkedList<Usuario>();
                listaUsuario = oUsuarioDB.seleccionarUsuarioId(Integer.parseInt(rsPA.getString("idUsuario")));
                
                Reservacion oReservacion = new Reservacion();
                oReservacion.id = rsPA.getString("id");
                oReservacion.Usuario = listaUsuario.get(0);
//                oReservacion.titulo = rsPA.getString("titule");
//                oReservacion.fechaInicio = fechaInicio;
//                oReservacion.fechaFinal = fechaFinal;
//                oReservacion.todoElDia = todoElDia == 1;
//                oReservacion.editable = editable == 1;
//                oReservacion.estadoSolicitud = estadoReservacion == 1;
                oReservacion.estadoRegistro = estadoResgistro == 1;

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
    
    public LinkedList<DetalleReservacion> selectDetallesReservacion(Reservacion pReservacion) throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<DetalleReservacion> listaDetallesReservacion = new LinkedList<DetalleReservacion>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT idRecurso,idInfraestructura,titule,startDate,endDate,allDay,estadoSolicitud"
                    + " from detalleRservacion where idReservacion='" + pReservacion.id + "'" ;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            
            
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "EEE MMM dd HH:mm:ss zz yyyy",Locale.getDefault());
            InfraestructuraDB oInfraestructuraDB = new InfraestructuraDB();
            //Se llena el arryaList con las reservaciones 
            while (rsPA.next()) {
                
                int todoElDia = Integer.parseInt(rsPA.getString("allDay"));
                int estadoSolicitud = Integer.parseInt(rsPA.getString("estadoSolicitud"));
                Date fechaFinal = dateFormat.parse(rsPA.getString("endDate"));
                Date fechaInicio = dateFormat.parse(rsPA.getString("startDate"));
                String idInfraestrcutura = rsPA.getString("idRecurso");
                
                DetalleReservacion oDetalle = new DetalleReservacion();
                oDetalle.Reservacion = pReservacion;
                oDetalle.estadoSolicitud = estadoSolicitud == 1;
                oDetalle.fechaInicio = fechaInicio;
                oDetalle.fechaFinal = fechaFinal;
                oDetalle.todoElDia = todoElDia == 1;
                
                if (idInfraestrcutura != null) {
                    oDetalle.Infraestructura = oInfraestructuraDB.seleccionarInfraestructuraId2(idInfraestrcutura);
                }
                
                listaDetallesReservacion.add(oDetalle);
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

        return listaDetallesReservacion;
    }
    
    public LinkedList<TipoReservacion> selectTipoReservacion() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<TipoReservacion> listaTipoReservacion = new LinkedList<TipoReservacion>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT * FROM tipoReservacion";
                    

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            
            while (rsPA.next()) {
                
                int id = Integer.parseInt(rsPA.getString("id"));
                String tipo = rsPA.getString("tipo");
                String descripcion = rsPA.getString("descripcion");
                
                TipoReservacion oTipoReservacion = new TipoReservacion();
                oTipoReservacion.Id = id;
                oTipoReservacion.tipo = tipo;
                oTipoReservacion.descripcion = descripcion;
                
                

                listaTipoReservacion.add(oTipoReservacion);
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

        return listaTipoReservacion;
    }
    
    public TipoReservacion selectTipoReservacionPorId(int pIdTipoReservacion) throws SNMPExceptions, SQLException {
        String select = "";
        TipoReservacion oTipoReservacion = null;

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT * FROM tipoReservacion where id=" + pIdTipoReservacion;
                    

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            
            while (rsPA.next()) {
                
                int id = Integer.parseInt(rsPA.getString("id"));
                String tipo = rsPA.getString("tipo");
                String descripcion = rsPA.getString("descripcion");
                
                oTipoReservacion = new TipoReservacion();
                oTipoReservacion.Id = id;
                oTipoReservacion.tipo = tipo;
                oTipoReservacion.descripcion = descripcion;
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
        
        return oTipoReservacion;
        
    }
}
