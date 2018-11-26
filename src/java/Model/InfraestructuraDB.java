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
        super();
    }

    
    
     public LinkedList<Infraestructura> seleccionarInfraestructurasDisponibles() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Infraestructura> listaInfraestructura = new LinkedList<Infraestructura>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT idInfraestructura,descripcion,disponibilidad,"
                    + "estadoRegistro FROM infraestructura where disponibilidad = 1 and estadoRegistro = 1";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String idInfraestructura = rsPA.getString("idInfraestructura");
                String descripcion = rsPA.getString("descripcion");
                int disponibilidad = Integer.parseInt(rsPA.getString("disponibilidad"));
                int estadoRegistro = Integer.parseInt(rsPA.getString("estadoRegistro"));
               
                Infraestructura oInfraestructua = new Infraestructura();
                oInfraestructua.setIdInfraestructura(idInfraestructura);
                oInfraestructua.setDescripcion(descripcion);
                oInfraestructua.setEstadoRegistro(disponibilidad == 1);
                oInfraestructua.setEstadoRegistro(estadoRegistro == 1);
               
                listaInfraestructura.add(oInfraestructua);
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
                    = "SELECT idInfraestructura, "
                    + "descripcion FROM Infraestructura where idInfraestructura="+deas.getIdInfraestructura();

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String idInfraestructura = rsPA.getString("idInfraestructura");
                String descripcion = rsPA.getString("descripcion");
                int disponibilidad = Integer.parseInt(rsPA.getString("disponibilidad"));
                int estadoRegistro = Integer.parseInt(rsPA.getString("estadoRegistro"));
               
                Infraestructura oInfraestructura = new Infraestructura();
                oInfraestructura.setIdInfraestructura(idInfraestructura);
                oInfraestructura.setDescripcion(descripcion);
                oInfraestructura.setEstadoRegistro(disponibilidad == 1);
                oInfraestructura.setEstadoRegistro(estadoRegistro == 1);

                listaInfraestructura.add(oInfraestructura);
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

        try {
            //Se obtienen los valores del objeto Departamento
            Infraestructura deas = new Infraestructura();
            deas = infra;

//            strSQL
//                    = "INSERT INTO Infraestructura (idInfraestructura,"
//                    + "descripcion) VALUES ("
//                    + deas.idInfraestructura + ","
//                    + "'" + deas.descripcion + "'"
//                    + ")";

            
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

        try {
            //Se obtienen los valores del objeto Departamento
            Infraestructura deas = new Infraestructura();
            deas = infra;

//            strSQL
//                    = "update Infraestructura"
//                    + "  set descripcion=" + "'" + deas.descripcion + "'" + ","
//                    + "  where idInfraestructura= " + deas.idInfraestructura;

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

        try {
            //Se obtienen los valores del objeto Departamento
            Infraestructura deas = new Infraestructura();
            deas = infra;

//            strSQL
//                    = "delete from Infraestructura"
//                    + "  where idInfraestructura= " + deas.idInfraestructura;

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
