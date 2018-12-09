/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.SNMPExceptions;
import DAO.AccesoDatos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.smartcardio.ResponseAPDU;

/**
 *
 * @author Ernesto PC
 */
public class UsuarioDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<Canton> listaP = new LinkedList<Canton>();

    public UsuarioDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public UsuarioDB() {
        super();
    }

    public void InsertarUsuario(Usuario usu) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Departamento
            Usuario usuario = new Usuario();
            usuario = usu;
            int estadoSolicitud = usu.estadoSolicitud ? 1 : 0;
            int estadoRegistro = usu.estadoRegistro ? 1 : 0;

            strSQL
                    = "INSERT INTO usuario(nombre,apellido1,apellido2,correo,fechaNacimiento,identificacion,"
                    + "idTipoIdentificacion,idTipoFuncionario,idTipoTelefonoUsuario,idDireccion,telefono,idProgramaDeas"
                    + ",estadoSolicitud,estadoRegistro) VALUES"
                    + "(" + "'" + usuario.getNombre() + "'" + ","
                    + "'" + usuario.getApellido1() + "'" + ","
                    + "'" + usuario.getApellido2() + "'" + ","
                    + "'" + usuario.getCorreo() + "'" + ","
                    + "'" + usuario.getFechaNacimiento() + "'" + ","
                    + usuario.getIdentificacion() + ","
                    + usuario.getTipoIdentificacion().getId() + ","
                    + usuario.getTipoFuncionario().getId() + ","
                    + usuario.getTipotelefono().getId() + ","
                    + usuario.direccion.getIdDireccion() + ","
                    + "'" + usuario.getTelefono() + "'" + ","
                    + "'" + usuario.ProgramaDeas.getId() + "'" + ","
                    + estadoSolicitud + ","
                    + estadoRegistro
                    + ")";

            //+ "'"+ usuario.getDireccion()+"'" + ")";
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage() + "Erro al insertar usuario", e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage() + "Erro al insertar usuario");
        } finally {

        }
    }

    public LinkedList<Usuario> seleccionarListaUsuarios() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();
            TipoFuncionarioDB oTipoFuncionarioDB = new TipoFuncionarioDB();
            TipoTelefonoDB oTipoTelefonoDB = new TipoTelefonoDB();
            TipoIdentificacionDB oTipoIdentificacionDB = new TipoIdentificacionDB();
            DireccionDB oDireccionDB = new DireccionDB();
            ProgramaDeasDB oProgramaDeasDB = new ProgramaDeasDB();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT identificacion,idTipoIdentificacion,idTipoFuncionario,idProgramaDeas,"
                    + "correo,nombre,apellido1,apellido2,fechaNacimiento,idTipoTelefonoUsuario,"
                    + "telefono,idDireccion,cuentaCompleta,estadoSolicitud"
                    + " FROM usuario where estadoRegistro = 1";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int identificacion = rsPA.getInt("identificacion");
                int tipoFuncionario = rsPA.getInt("idTipoFuncionario");
                String idProgramaDEAS = rsPA.getString("idProgramaDeas");
                String nombre = rsPA.getString("nombre");
                String apellido1 = rsPA.getString("apellido1");
                String apellido2 = rsPA.getString("apellido2");
                String correo = rsPA.getString("correo");
                int idTipoIdentificacion = rsPA.getInt("idTipoIdentificacion");
                String fechaNacimiento =  rsPA.getString("fechaNacimiento");
                int idTipoTelefonoUsuario = rsPA.getInt("idTipoTelefonoUsuario");
                String telefono = rsPA.getString("telefono");
                int idDireccion = rsPA.getInt("idDireccion");
                int cuentaCompleta = rsPA.getInt("cuentaCompleta");
                int estadoSolicitud = rsPA.getInt("estadoSolicitud");
                
                List<TipoIdentificacion> listaTipo = oTipoIdentificacionDB.seleccionarId(idTipoIdentificacion);
                List<TipoFuncionario> listaTipoFun = oTipoFuncionarioDB.seleccionarTiposFuncionariosid(tipoFuncionario);
                List<TipoTelefono> listaTipoTel = oTipoTelefonoDB.seleccionarTipoTelefonoPorId(idTipoTelefonoUsuario);
                List<ProgramaDeas> listaPrograma = oProgramaDeasDB.seleccionarProgramaDeasId(idProgramaDEAS);
                List<Direccion> listaDireccion = oDireccionDB.seleccionarDireccionPorId(idDireccion,identificacion);
                
                
                
                Usuario perUsuarios = new Usuario();
                perUsuarios.identificacion = identificacion;
                perUsuarios.nombre = nombre;
                perUsuarios.apellido1 = apellido1;
                perUsuarios.apellido2 = apellido2;
                perUsuarios.correo = correo;
                perUsuarios.tipoIdentificacion = listaTipo.get(0);
                perUsuarios.tipoFuncionario = listaTipoFun.get(0);
                perUsuarios.tipotelefono = listaTipoTel.get(0);
                perUsuarios.fechaNacimiento = fechaNacimiento;
                perUsuarios.telefono = telefono;
                perUsuarios.estadoSolicitud = estadoSolicitud == 1;
                perUsuarios.cuentaCompleta = cuentaCompleta == 1;
                perUsuarios.ProgramaDeas = listaPrograma.get(0);
                perUsuarios.direccion = listaDireccion.get(0);
                
                listaUsuarios.add(perUsuarios);
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

        return listaUsuarios;
    }
    
    public LinkedList<Usuario> seleccionarListaUsuariosPorId(int pIdUsuario) throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();
            TipoFuncionarioDB oTipoFuncionarioDB = new TipoFuncionarioDB();
            TipoTelefonoDB oTipoTelefonoDB = new TipoTelefonoDB();
            TipoIdentificacionDB oTipoIdentificacionDB = new TipoIdentificacionDB();
            DireccionDB oDireccionDB = new DireccionDB();
            ProgramaDeasDB oProgramaDeasDB = new ProgramaDeasDB();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT identificacion,idTipoIdentificacion,idTipoFuncionario,idProgramaDeas,"
                    + "correo,nombre,apellido1,apellido2,fechaNacimiento,idTipoTelefonoUsuario,"
                    + "telefono,idDireccion,cuentaCompleta,estadoSolicitud"
                    + " FROM usuario where estadoRegistro = 1 and identificacion=" + pIdUsuario;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int identificacion = rsPA.getInt("identificacion");
                int tipoFuncionario = rsPA.getInt("idTipoFuncionario");
                String idProgramaDEAS = rsPA.getString("idProgramaDeas");
                String nombre = rsPA.getString("nombre");
                String apellido1 = rsPA.getString("apellido1");
                String apellido2 = rsPA.getString("apellido2");
                String correo = rsPA.getString("correo");
                int idTipoIdentificacion = rsPA.getInt("idTipoIdentificacion");
                String fechaNacimiento =  rsPA.getString("fechaNacimiento");
                int idTipoTelefonoUsuario = rsPA.getInt("idTipoTelefonoUsuario");
                String telefono = rsPA.getString("telefono");
                int idDireccion = rsPA.getInt("idDireccion");
                int cuentaCompleta = rsPA.getInt("cuentaCompleta");
                int estadoSolicitud = rsPA.getInt("estadoSolicitud");
                
                List<TipoIdentificacion> listaTipo = oTipoIdentificacionDB.seleccionarId(idTipoIdentificacion);
                List<TipoFuncionario> listaTipoFun = oTipoFuncionarioDB.seleccionarTiposFuncionariosid(tipoFuncionario);
                List<TipoTelefono> listaTipoTel = oTipoTelefonoDB.seleccionarTipoTelefonoPorId(idTipoTelefonoUsuario);
                List<ProgramaDeas> listaPrograma = oProgramaDeasDB.seleccionarProgramaDeasId(idProgramaDEAS);
                List<Direccion> listaDireccion = oDireccionDB.seleccionarDireccionPorId(idDireccion,identificacion);
                
                
                
                Usuario perUsuarios = new Usuario();
                perUsuarios.identificacion = identificacion;
                perUsuarios.nombre = nombre;
                perUsuarios.apellido1 = apellido1;
                perUsuarios.apellido2 = apellido2;
                perUsuarios.correo = correo;
                perUsuarios.tipoIdentificacion = listaTipo.get(0);
                perUsuarios.tipoFuncionario = listaTipoFun.get(0);
                perUsuarios.tipotelefono = listaTipoTel.get(0);
                perUsuarios.fechaNacimiento = fechaNacimiento;
                perUsuarios.telefono = telefono;
                perUsuarios.estadoSolicitud = estadoSolicitud == 1;
                perUsuarios.cuentaCompleta = cuentaCompleta == 1;
                perUsuarios.ProgramaDeas = listaPrograma.get(0);
                perUsuarios.direccion = listaDireccion.get(0);
                
                listaUsuarios.add(perUsuarios);
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

        return listaUsuarios;
    }

    public LinkedList<Usuario> seleccionarSolicitudes() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT identificacion, nombre, apellido1, apellido2, "
                    + "correo FROM usuario where estadoRegistro=1";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int identificacion = rsPA.getInt("identificacion");
                String nombre = rsPA.getString("nombre");
                String apellido1 = rsPA.getString("apellido1");
                String apellido2 = rsPA.getString("apellido2");
                String correo = rsPA.getString("correo");

                Usuario perUsuarios = new Usuario();
                perUsuarios.identificacion = identificacion;
                perUsuarios.nombre = nombre;
                perUsuarios.apellido1 = apellido1;
                perUsuarios.apellido2 = apellido2;
                perUsuarios.correo = correo;
                listaUsuarios.add(perUsuarios);
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

        return listaUsuarios;
    }

    public LinkedList<Usuario> validarUsuario(int id, int tipo, String contra) throws SNMPExceptions, SQLException {

        String select = "";
        LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();
            TipoFuncionarioDB oTipoFuncionarioDB = new TipoFuncionarioDB();
            TipoTelefonoDB oTipoTelefonoDB = new TipoTelefonoDB();
            TipoIdentificacionDB oTipoIdentificacionDB = new TipoIdentificacionDB();
            DireccionDB oDireccionDB = new DireccionDB();
            ProgramaDeasDB oProgramaDeasDB = new ProgramaDeasDB();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT identificacion,idTipoIdentificacion,idTipoFuncionario,idProgramaDeas,"
                    + "correo,nombre,apellido1,apellido2,fechaNacimiento,idTipoTelefonoUsuario,"
                    + "telefono,idDireccion,cuentaCompleta,estadoSolicitud,codigoverificacion"
                    + " FROM usuario where estadoRegistro = 1 and "
                    + "identificacion=" + id + "and idTipoFuncionario=" + tipo + " and contra=" + "'" + contra + "'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int identificacion = rsPA.getInt("identificacion");
                int tipoFuncionario = rsPA.getInt("idTipoFuncionario");
                String idProgramaDEAS = rsPA.getString("idProgramaDeas");
                String nombre = rsPA.getString("nombre");
                String apellido1 = rsPA.getString("apellido1");
                String apellido2 = rsPA.getString("apellido2");
                String correo = rsPA.getString("correo");
                int idTipoIdentificacion = rsPA.getInt("idTipoIdentificacion");
                String fechaNacimiento =  rsPA.getString("fechaNacimiento");
                int idTipoTelefonoUsuario = rsPA.getInt("idTipoTelefonoUsuario");
                String telefono = rsPA.getString("telefono");
                int idDireccion = rsPA.getInt("idDireccion");
                int cuentaCompleta = rsPA.getInt("cuentaCompleta");
                int estadoSolicitud = rsPA.getInt("estadoSolicitud");
                String codigo = rsPA.getString("codigoverificacion");
                
                List<TipoIdentificacion> listaTipo = oTipoIdentificacionDB.seleccionarId(idTipoIdentificacion);
                List<TipoFuncionario> listaTipoFun = oTipoFuncionarioDB.seleccionarTiposFuncionariosid(tipoFuncionario);
                List<TipoTelefono> listaTipoTel = oTipoTelefonoDB.seleccionarTipoTelefonoPorId(idTipoTelefonoUsuario);
                List<ProgramaDeas> listaPrograma = oProgramaDeasDB.seleccionarProgramaDeasId(idProgramaDEAS);
                List<Direccion> listaDireccion = oDireccionDB.seleccionarDireccionPorId(idDireccion,identificacion);
                
                
                
                Usuario perUsuarios = new Usuario();
                perUsuarios.identificacion = identificacion;
                perUsuarios.nombre = nombre;
                perUsuarios.apellido1 = apellido1;
                perUsuarios.apellido2 = apellido2;
                perUsuarios.correo = correo;
                perUsuarios.tipoIdentificacion = listaTipo.get(0);
                perUsuarios.tipoFuncionario = listaTipoFun.get(0);
                perUsuarios.tipotelefono = listaTipoTel.get(0);
                perUsuarios.fechaNacimiento = fechaNacimiento;
                perUsuarios.telefono = telefono;
                perUsuarios.estadoSolicitud = estadoSolicitud == 1;
                perUsuarios.cuentaCompleta = cuentaCompleta == 1;
                perUsuarios.ProgramaDeas = listaPrograma.get(0);
                perUsuarios.direccion = listaDireccion.get(0);
                perUsuarios.codigoVerificacion = codigo;
                
                listaUsuarios.add(perUsuarios);
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

        return listaUsuarios;
        
    }

    public LinkedList<Usuario> seleccionarUsuarioId(int id) throws SNMPExceptions, SQLException {

        LinkedList<Usuario> listaUsu = new LinkedList<Usuario>();

        String select = "";
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT identificacion,nombre, apellido1,"
                    + "apellido2, correo FROM usuario WHERE identificacion=" + id;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                int identificacion = rsPA.getInt("identificacion");
                String nombre = rsPA.getString("nombre");
                String apellido1 = rsPA.getString("apellido1");
                String apellido2 = rsPA.getString("apellido2");
                String correo = rsPA.getString("correo");

                Usuario usuObetenido = new Usuario();
                usuObetenido.identificacion = identificacion;
                usuObetenido.nombre = nombre;
                usuObetenido.apellido1 = apellido1;
                usuObetenido.apellido2 = apellido2;
                usuObetenido.correo = correo;
                listaUsu.add(usuObetenido);

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

        return listaUsu;
    }

    public void ActualizarUsuario(Usuario usu) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Departamento
            Usuario usuario = new Usuario();
            usuario = usu;

            strSQL
                    = "update usuario"
                    + "  set nombre=" + "'" + usu.nombre + "'" + ","
                    + "  apellido1=" + "'" + usu.apellido1 + "'" + ","
                    + "  apellido2=" + "'" + usu.apellido2 + "'" + ","
                    + "  correo=" + "'" + usu.correo + "'"
                    + "  where identificacion= " + usu.identificacion;

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
    
    public void ActualizarUsuarioContraseña(Usuario usu) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            strSQL
                    = "update usuario"
                    + "  set contra=" + "'" + usu.contrasena + "'"
                    + "  where identificacion= " + usu.identificacion;

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
    
        public void ActualizarUsuarioCuentaCompleta(Usuario usu) throws SNMPExceptions, SQLException {
        String strSQL = "";
        
         int cuentacompleta = usu.cuentaCompleta ? 1:0;
         
        try {
            strSQL
                    = "update usuario"
                    + "  set cuentaCompleta=" + cuentacompleta
                    + "  where identificacion= " + usu.identificacion;

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

    
    public void ActualizarUsuarioSilicitudAceptada(Usuario usu) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Departamento
            int estadoSolicitud = usu.estadoSolicitud ? 1:0;

            strSQL
                    = "update usuario"
                    + "  set estadoSolicitud=" + estadoSolicitud + ","
                    + "  codigoverificacion=" + "'" + usu.codigoVerificacion + "'" + ","
                    + "  contra=" + "'" + usu.contrasena + "'" 
                    + "  where identificacion= " + usu.identificacion;

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

    public void EliminarUsuario(Usuario usu) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            //Se obtienen los valores del objeto Departamento
            Usuario usuario = new Usuario();
            usuario = usu;

            strSQL
                    = "delete from usuario"
                    + "  where identificacion= " + usu.identificacion;

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
