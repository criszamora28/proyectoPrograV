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
public class CursoDeasDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public CursoDeasDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public CursoDeasDB(){}
    
    
    
     public LinkedList<CursoDeas> seleccionarCursosDeasId(CursoDeas deas) throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<CursoDeas> listaCursoDeas = new LinkedList<CursoDeas>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT idPrograma, nombreCurso, "
                    + "descripcion FROM cursoDeas where idPrograma="+deas.idPrograma;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int idPrograma = rsPA.getInt("idPrograma");
                String nombreCurso = rsPA.getString("nombreCurso");
                String descripcion = rsPA.getString("descripcion");
              

                CursoDeas perCursoDeas = new CursoDeas();
                perCursoDeas.idPrograma = idPrograma;
                perCursoDeas.nombreCurso = nombreCurso;
                perCursoDeas.descripcion = descripcion;
               
                listaCursoDeas.add(perCursoDeas);
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

        return listaCursoDeas;
    }
    
    
    public LinkedList<CursoDeas> seleccionarCursosDeas() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<CursoDeas> listaCursoDeas = new LinkedList<CursoDeas>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT idPrograma, nombreCurso, "
                    + "descripcion FROM cursoDeas";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int idPrograma = rsPA.getInt("idPrograma");
                String nombreCurso = rsPA.getString("nombreCurso");
                String descripcion = rsPA.getString("descripcion");
              

                CursoDeas perCursoDeas = new CursoDeas();
                perCursoDeas.idPrograma = idPrograma;
                perCursoDeas.nombreCurso = nombreCurso;
                perCursoDeas.descripcion = descripcion;
               
                listaCursoDeas.add(perCursoDeas);
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

        return listaCursoDeas;
    }

    public void InsertarCursoDeas(CursoDeas curso) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Departamento
            CursoDeas deas = new CursoDeas();
            deas = curso;

            strSQL
                    = "INSERT INTO usuario (idPrograma,nombrecurso,"
                    + "descripcion) VALUES ("
                    + deas.idPrograma + ","
                    + "'" + deas.nombreCurso + "'"
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

    public void ActualizarCurso(CursoDeas curso) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Departamento
            CursoDeas deas = new CursoDeas();
            deas = curso;

            strSQL
                    = "update CursoDeas"
                    + "  set nombreCurso=" + "'" + deas.nombreCurso + "'" + ","
                    + "  descripcion=" + "'" + deas.descripcion + "'" + ","
                    + "  where idPrograma= " + deas.idPrograma;

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public void EliminarCurso(CursoDeas curso) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Departamento
            CursoDeas deas = new CursoDeas();
            deas = curso;

            strSQL
                    = "delete from cursoDeas"
                    + "  where idPrograma= " + deas.idPrograma;

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
