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
public class ProgramaDeasDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public ProgramaDeasDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public ProgramaDeasDB() {
      
    }

    public LinkedList<ProgramaDeas> seleccionarProgramaDeas() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<ProgramaDeas> listaProgramaDeas = new LinkedList<ProgramaDeas>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT id, nombrePrograma, "
                    + "descripcion FROM programaDeas";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String id = rsPA.getString("id");
                String nombrePrograma = rsPA.getString("nombrePrograma");
                String descripcion = rsPA.getString("descripcion");

                ProgramaDeas perProgramaDeas = new ProgramaDeas();
                perProgramaDeas.id = id;
                perProgramaDeas.nombrePrograma = nombrePrograma;
                perProgramaDeas.descripcion = descripcion;

                listaProgramaDeas.add(perProgramaDeas);
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

        return listaProgramaDeas;
    }

    public LinkedList<ProgramaDeas> seleccionarProgramaDeasId(ProgramaDeas deas) throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<ProgramaDeas> listaProgramaDeas = new LinkedList<ProgramaDeas>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT id,nombrePrograma, "
                    + "descripcion FROM programaDeas where id=" + deas.id;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                String id = rsPA.getString("id");
                String nombrePrograma = rsPA.getString("nombrePrograma");
                String descripcion = rsPA.getString("descripcion");

                ProgramaDeas perProgramaDeas = new ProgramaDeas();
                perProgramaDeas.id = id;
                perProgramaDeas.nombrePrograma = nombrePrograma;
                perProgramaDeas.descripcion = descripcion;

                listaProgramaDeas.add(perProgramaDeas);
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

        return listaProgramaDeas;
    }

    public void InsertarProgramaDeas(ProgramaDeas infra) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Departamento
            ProgramaDeas deas = new ProgramaDeas();
            deas = infra;

            strSQL
                    = "INSERT INTO programaDeas (id, nombrePrograma,"
                    + "descripcion) VALUES ("
                    +"'"+ deas.id + "'"+","
                    + "'" + deas.nombrePrograma + "'"+","
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

    public void ActualizarProgramaDeas(ProgramaDeas infra) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Departamento
            ProgramaDeas deas = new ProgramaDeas();
            deas = infra;

            strSQL
                    = "update programaDeas"
                    + "  set descripcion=" + "'" + deas.descripcion + "'" + ","
                    + "nombrePrograma="+"'"+deas.nombrePrograma+"'"
                    + "  where id= "+"'" + deas.id+"'";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
    
        public void EliminarProgramaDeas(ProgramaDeas infra) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Departamento
            ProgramaDeas deas = new ProgramaDeas();
            deas = infra;

            strSQL
                    = "delete from programaDeas"
                    + "  where id= " + deas.id;

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
