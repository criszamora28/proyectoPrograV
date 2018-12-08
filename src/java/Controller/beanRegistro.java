/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Canton;
import Model.CantonDB;
import Model.Direccion;
import Model.DireccionDB;
import Model.Distrito;
import Model.DistritoDB;
import Model.ProgramaDeas;
import Model.ProgramaDeasDB;
import Model.Provincia;
import Model.ProvinciaDB;
import Model.TipoFuncionario;
import Model.TipoFuncionarioDB;
import Model.TipoIdentificacion;
import Model.TipoIdentificacionDB;
import Model.TipoTelefono;
import Model.TipoTelefonoDB;
import Model.Usuario;
import Model.UsuarioDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Ernesto PC
 */
@Named(value = "beanRegistro")
@SessionScoped
public class beanRegistro implements Serializable {

    //Canton
    String idCanton;
    String nombrecanton;
    //provincia
    String idProvincia;
    String nombreProvincia;
    //distrito
    String idDistrito;
    String nombreDistrito;
    //Usuario
    String nombre;
    String apellido1;
    String apellido2;
    String direccion;
    String telefono;
    String fechaNacimiento;
    String indentificacion;
    String correo;
    int tipoIdentificacion;
    int tipoTelefono;
    int tipoFuncionario;
    String idprogramaDeas;
    String otrasSeñas;

    LinkedList<SelectItem> listaCanton1 = new LinkedList<>();
    LinkedList<SelectItem> listaProvincia1 = new LinkedList<>();
    LinkedList<SelectItem> listaDistrito1 = new LinkedList<>();

    public void insertarUsuario() throws SNMPExceptions, SQLException {

        TipoIdentificacion oTipoIden = new TipoIdentificacion();
        TipoIdentificacionDB oTipoDB = new TipoIdentificacionDB();
        
        TipoFuncionario oTipoFuncionario = new TipoFuncionario();
        TipoFuncionarioDB oTipoFuncionarioDB = new TipoFuncionarioDB();
        
        TipoTelefono oTipoTel = new TipoTelefono();
        TipoTelefonoDB oTipoTelDB = new TipoTelefonoDB();
        
        ProgramaDeas oPrograma = new ProgramaDeas();
        ProgramaDeasDB oProgramaDeasDB = new ProgramaDeasDB();
        
        ProvinciaDB oProvinciaDB = new  ProvinciaDB();
        CantonDB oCantonDB = new CantonDB();
        DistritoDB oDistritoDB = new DistritoDB();
        
        if (this.tipoFuncionario != 0) {
            LinkedList<TipoFuncionario> listaTipoFuncionario = new LinkedList<TipoFuncionario>();
            listaTipoFuncionario = oTipoFuncionarioDB.seleccionarTiposFuncionariosid(this.tipoFuncionario);
            oTipoFuncionario = listaTipoFuncionario.get(0);
        } else {
            return;
        }
        
//        if (this.idprogramaDeas == null) {
//            LinkedList<ProgramaDeas> listaPrograma = new LinkedList<ProgramaDeas>();
//            listaPrograma = oProgramaDeasDB.seleccionarProgramaDeasId(this.idprogramaDeas);
//            oPrograma = listaPrograma.get(0);
//        } else {
//            return;
//        }
        
        if (this.tipoTelefono != 0) {
            LinkedList<TipoTelefono> listTipo = new LinkedList<TipoTelefono>();
            listTipo = oTipoTelDB.seleccionarTipoTelefonoPorId(this.tipoTelefono);
            oTipoTel = listTipo.get(0);
        } else {
            return;
        }
        
        if (this.tipoIdentificacion != 0) {
            LinkedList<TipoIdentificacion> listTipo = new LinkedList<TipoIdentificacion>();
            listTipo = oTipoDB.seleccionarId(this.tipoIdentificacion);
            oTipoIden = listTipo.get(0);
        } else {
            return;
        }
        Provincia oProvincia = oProvinciaDB.buscarProvincia(idProvincia);
        Canton oCanton = oCantonDB.buscarcanton(this.idCanton,idProvincia);
        Distrito oDistrito = oDistritoDB.buscarDistrito(idDistrito,idCanton,idProvincia);
        
        
        Direccion oDireccion = new Direccion();
        oDireccion.setCanton(oCanton);
        oDireccion.setDistrito(oDistrito);
        oDireccion.setProvincia(oProvincia);
        oDireccion.setOtrasSeñas(this.otrasSeñas);
        
        DireccionDB oDireccionDB = new DireccionDB();
        oDireccionDB.insertarDireccion(oDireccion);
        
        
        UsuarioDB usDB = new UsuarioDB();

        Usuario oUsuarioNuevo = new Usuario();
        oUsuarioNuevo.nombre = this.getNombre();
        oUsuarioNuevo.apellido1 = this.getApellido1();
        oUsuarioNuevo.apellido2 = this.getApellido2();
        oUsuarioNuevo.telefono = this.getTelefono();
        oUsuarioNuevo.fechaNacimiento = this.getFechaNacimiento();
        oUsuarioNuevo.identificacion = Integer.parseInt(this.getIndentificacion());
        oUsuarioNuevo.correo = this.getCorreo();
        oUsuarioNuevo.tipotelefono = oTipoTel;
        oUsuarioNuevo.tipoIdentificacion = oTipoIden;
        oUsuarioNuevo.tipoFuncionario = oTipoFuncionario;
        oUsuarioNuevo.cuentaCompleta = false;
        oUsuarioNuevo.estado = true;
        
        usDB.InsertarUsuario(oUsuarioNuevo);
    }

    public LinkedList<SelectItem> getListaCantonPorProvincia() throws SNMPExceptions, SQLException {
        String idCan = "";
        String nombreCan = "";

        LinkedList<Canton> lista = new LinkedList<Canton>();
        CantonDB cDB = new CantonDB();

        if (this.idProvincia == null) {
            return null;
        }

        lista = cDB.seleccionarCantonesPorProvincia(idProvincia);

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione un Canton"));

        for (Canton pro : lista) {
            idCan = pro.getIdCanton();
            nombreCan = pro.getNombreCanton();
            resultList.add(new SelectItem(idCan, nombreCan));
        }
        return resultList;

    }

    public LinkedList<SelectItem> getListaProvincia() throws SNMPExceptions, SQLException {
        String idProvincia = "";
        String nombreProvincia = "";

        LinkedList<Provincia> lista = new LinkedList<Provincia>();
        ProvinciaDB pDB = new ProvinciaDB();

        lista = pDB.moTodo();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione una Provincia"));

        for (Provincia pro : lista) {
            idProvincia = pro.getIdProvincia();
            nombreProvincia = pro.getNombreprovincia();
            resultList.add(new SelectItem(idProvincia, nombreProvincia));
        }
        return resultList;

    }

    public LinkedList<SelectItem> getListaDistritoPorCanton() throws SNMPExceptions, SQLException {
        String idDistrito = "";
        String nombreDistrito = "";

        LinkedList<Distrito> lista = new LinkedList<Distrito>();
        DistritoDB dDB = new DistritoDB();

        if (this.idProvincia == null) {
            return null;
        }

        if (this.idCanton == null) {
            return null;
        }
        lista = dDB.seleccionarDistritoPorCanton(this.idProvincia, this.idCanton);

        LinkedList resultList = new LinkedList();

        for (Distrito pro : lista) {
            idDistrito = pro.getIdDistrito();
            nombreDistrito = pro.getNombreDistrito();
            resultList.add(new SelectItem(idDistrito, nombreDistrito));
        }
        return resultList;
    }

    public LinkedList<SelectItem> getListaTipoIdentificacion() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<TipoIdentificacion> lista = new LinkedList<TipoIdentificacion>();
        TipoIdentificacionDB fDB = new TipoIdentificacionDB();
        TipoIdentificacion n = new TipoIdentificacion();
        lista = fDB.moTodo();

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator(); iter.hasNext();) {

            TipoIdentificacion tipoFun = (TipoIdentificacion) iter.next();
            id = tipoFun.getId();
            tipo = tipoFun.getTipo();
            resultList.add(new SelectItem(id, tipo));
        }
        return resultList;

    }

    public LinkedList<SelectItem> getListaTipoTelefono() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<TipoTelefono> lista = new LinkedList<TipoTelefono>();
        TipoTelefonoDB oTipoTelDB = new TipoTelefonoDB();
        LinkedList resultList = new LinkedList();
        
        try {
            
            lista = oTipoTelDB.selectTipoTelefono();
            
            for (TipoTelefono tipoTel : lista) {
                id = tipoTel.getId();
                tipo = tipoTel.getTipo();
                resultList.add(new SelectItem(id, tipo));
            }
        } catch (Exception e) {
        }

        return resultList;

    }
    
    public LinkedList<SelectItem> getListaTipoFuncionario() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<TipoFuncionario> lista = new LinkedList<TipoFuncionario>();
        TipoFuncionarioDB oTipoFuncionarioDB = new TipoFuncionarioDB();
        LinkedList resultList = new LinkedList();
        
        try {
            
            lista = oTipoFuncionarioDB.seleccionarTiposFuncionarios();
            
            for (TipoFuncionario oTipoFun : lista) {
                id = oTipoFun.getId();
                tipo = oTipoFun.getTipoUsuario();
                resultList.add(new SelectItem(id, tipo));
            }
        } catch (Exception e) {
        }

        return resultList;

    }
    
    public LinkedList<SelectItem> getListaProgramas() throws SNMPExceptions, SQLException {
        String id = "";
        String tipo = "";

        LinkedList<ProgramaDeas> lista = new LinkedList<ProgramaDeas>();
        ProgramaDeasDB oProgramaDeas = new ProgramaDeasDB();
        LinkedList resultList = new LinkedList();
        
        try {
            
            lista = oProgramaDeas.seleccionarProgramaDeas();
            
            for (ProgramaDeas oProgrma : lista) {
                id = oProgrma.getId();
                tipo = oProgrma.getNombrePrograma();
                resultList.add(new SelectItem(id, tipo));
            }
        } catch (Exception e) {
        }

        return resultList;

    }

    //setters and getters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIndentificacion() {
        return indentificacion;
    }

    public int getTipoTelefono() {
        return tipoTelefono;
    }

    public int getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(int tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public String getOtrasSeñas() {
        return otrasSeñas;
    }

    public void setOtrasSeñas(String otrasSeñas) {
        this.otrasSeñas = otrasSeñas;
    }

    public void setTipoTelefono(int tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public void setIndentificacion(String indentificacion) {
        this.indentificacion = indentificacion;
    }

    public String getIdCanton() {
        return idCanton;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setIdCanton(String idCanton) {
        this.idCanton = idCanton;
    }

    public String getNombrecanton() {
        return nombrecanton;
    }

    public void setNombrecanton(String nombrecanton) {
        this.nombrecanton = nombrecanton;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    //sets and gets usuarios 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getIdprogramaDeas() {
        return idprogramaDeas;
    }

    public void setIdprogramaDeas(String idprogramaDeas) {
        this.idprogramaDeas = idprogramaDeas;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(int tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

}
