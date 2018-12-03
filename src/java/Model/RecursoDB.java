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
public class RecursoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public RecursoDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public RecursoDB() {

    }

    public LinkedList<Recurso> seleccionarRecurso() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Recurso> listaRecurso = new LinkedList<Recurso>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT id,tipo,  "
                    + "descripcion,estadoRegistro FROM recurso where estadoRecurso=1";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String id = rsPA.getString("id");
                String tipo = rsPA.getString("tipo");
            //    boolean estado = rsPA.getBoolean("estadoRecurso");
                String descripcion = rsPA.getString("descripcion");

                Recurso perRecurso = new Recurso();
                perRecurso.id = id;
                perRecurso.tipo = tipo;
                perRecurso.descripcion = descripcion;
              //  perRecurso.estadoRecurso=

                listaRecurso.add(perRecurso);
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

        return listaRecurso;
    }

    public LinkedList<Recurso> seleccionarRecursoId(String deas) throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Recurso> listaRecurso = new LinkedList<Recurso>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT id,tipo, "
                    + "descripcion, estadoRecurso FROM recurso where id=" + "'" + deas + "'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String id = rsPA.getString("id");
                String tipo = rsPA.getString("tipo");
                // int idInfraestructura = rsPA.getInt("idInfraestructura");
                String descripcion = rsPA.getString("descripcion");

                Recurso perRecurso = new Recurso();
                perRecurso.id = id;
                perRecurso.tipo = tipo;
                perRecurso.descripcion = descripcion;

                listaRecurso.add(perRecurso);
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

        return listaRecurso;
    }
    
    public Recurso seleccionarRecursoId2(String pIdRecurso) throws SNMPExceptions, SQLException {
        String select = "";
        Recurso perRecurso = null;

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT id,tipo, "
                    + "descripcion, estadoRecurso FROM recurso where id=" + "'" + pIdRecurso + "'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String id = rsPA.getString("id");
                String tipo = rsPA.getString("tipo");
                String descripcion = rsPA.getString("descripcion");

                perRecurso = new Recurso();
                perRecurso.id = id;
                perRecurso.tipo = tipo;
                perRecurso.descripcion = descripcion;

               
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

        return perRecurso;
    }
    
    

    public void InsertarRecurso(Recurso infra) throws SNMPExceptions, SQLException {
        String strSQL = "";
        
        try {
            //Se obtienen los valores del objeto Departamento
            Recurso deas = new Recurso();
            deas = infra;

            strSQL
                    = "INSERT INTO recurso (id,tipo, estadoRecurso,"
                    + "descripcion) VALUES ("
                    + "'" + deas.id + "'" + ","
                    + "'" + deas.tipo + "'" + ","
                    + 1 + ","
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

    public void ActualizarRecurso(Recurso infra) throws SNMPExceptions, SQLException {
        String strSQL = "";
        
        try {

            Recurso deas = new Recurso();
            deas = infra;

            strSQL
                    = "update recurso"
                    + "  set tipo=" + "'" + deas.tipo + "'" + ","
                    + "descripcion=" + "'" + deas.descripcion + "'"
                    + "  where id= " + "'"+deas.id+"'";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public void EliminarRecurso(Recurso infra) throws SNMPExceptions, SQLException {
        String strSQL = "";
        
        try {
            //Se obtienen los valores del objeto Departamento
            Recurso deas = new Recurso();
            deas = infra;

            strSQL
                    = "update recurso "
                    + "set estadoRecurso=" + 0
                    + " where id= " +"'" +deas.id+"'";

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
