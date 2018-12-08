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
import Model.Validadores;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

        ProvinciaDB oProvinciaDB = new ProvinciaDB();
        CantonDB oCantonDB = new CantonDB();
        DistritoDB oDistritoDB = new DistritoDB();
        DireccionDB oDireccionDB = new DireccionDB();
        UsuarioDB usDB = new UsuarioDB();
        
       
        if (!usDB.seleccionarUsuarioId(Integer.parseInt(this.indentificacion)).isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya existe un usuario con esa identificacion"));
            this.indentificacion = "";
            return;
        }
        
        if (Validadores.validarVacio(apellido1)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Los apellidos no pueden estar vacios"));
            return;
        }

        if (Validadores.validarVacio(apellido2)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Los apellidos no pueden estar vacios"));
            return;
        }
        
        if (Validadores.validarVacio(nombre)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El nombre no puede estar vacio"));
            return;
        }

        if (Validadores.validarVacio(correo)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El correo no puede estar vacio"));
            return;
        }

        if (Validadores.validarVacio(fechaNacimiento)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Fecha de nacimiento no puede estar vacia"));
            return;
        }

        if (Validadores.validarVacio(indentificacion)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La identificacion no puede estar vacia"));
            return;
        }

        if (!Validadores.validarFecha(fechaNacimiento)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar una fecha correcta dd/MM/yyyy"));
            return;
        }

        if (Validadores.validarVacio(otrasSeñas)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe especificar las señas"));
            return;
        }

        if (Validadores.validarVacio(telefono)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El telefono no puede estar vacio"));
            return;
        }

        if (this.idProvincia == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe selecionar una provincia"));
            return;
        }

        if (this.idCanton == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "debe selecionar un canton"));
            return;
        }

        if (this.idDistrito == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe selecionar un distrito"));
            return;
        }

        if (this.tipoFuncionario == 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe selecionar un tipo de funcionario"));
            return;
        }

        if (this.tipoIdentificacion == 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe selecionar un tipo de identificacion"));
            return;
        }

        if (this.tipoTelefono == 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe selecionar un tipo de telefono"));
            return;
        }
        
        if (Validadores.validarVacio(idprogramaDeas)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe selecionar un programa DEAS"));
            return;
        }

        try {

            LinkedList<TipoFuncionario> listaTipoFuncionario = new LinkedList<TipoFuncionario>();
            listaTipoFuncionario = oTipoFuncionarioDB.seleccionarTiposFuncionariosid(this.tipoFuncionario);
            oTipoFuncionario = listaTipoFuncionario.get(0);
            
            LinkedList<ProgramaDeas> listaPrograma = new LinkedList<ProgramaDeas>();
            listaPrograma = oProgramaDeasDB.seleccionarProgramaDeasId(this.idprogramaDeas);
            oPrograma = listaPrograma.get(0);
        
            LinkedList<TipoTelefono> listTipo = new LinkedList<TipoTelefono>();
            listTipo = oTipoTelDB.seleccionarTipoTelefonoPorId(this.tipoTelefono);
            oTipoTel = listTipo.get(0);

            LinkedList<TipoIdentificacion> listTipo2 = new LinkedList<TipoIdentificacion>();
            listTipo2 = oTipoDB.seleccionarId(this.tipoIdentificacion);
            oTipoIden = listTipo2.get(0);

            Provincia oProvincia = oProvinciaDB.buscarProvincia(idProvincia);
            Canton oCanton = oCantonDB.buscarcanton(this.idCanton, idProvincia);
            Distrito oDistrito = oDistritoDB.buscarDistrito(idDistrito, idCanton, idProvincia);

            Direccion oDireccion = new Direccion();
            oDireccion.setIdDireccion(oDireccionDB.obtenerUltimoIdDireccion());
            oDireccion.setCanton(oCanton);
            oDireccion.setDistrito(oDistrito);
            oDireccion.setProvincia(oProvincia);
            oDireccion.setOtrasSeñas(this.otrasSeñas);
            oDireccion.setEstadoRegistro(true);

            oDireccionDB.insertarDireccion(oDireccion);

            

            Usuario oUsuarioNuevo = new Usuario();
            oUsuarioNuevo.nombre = this.getNombre();
            oUsuarioNuevo.ProgramaDeas = oPrograma;
            oUsuarioNuevo.apellido1 = this.getApellido1();
            oUsuarioNuevo.apellido2 = this.getApellido2();
            oUsuarioNuevo.telefono = this.getTelefono();
            oUsuarioNuevo.fechaNacimiento = this.getFechaNacimiento();
            oUsuarioNuevo.identificacion = Integer.parseInt(this.getIndentificacion());
            oUsuarioNuevo.correo = this.getCorreo();
            oUsuarioNuevo.tipotelefono = oTipoTel;
            oUsuarioNuevo.tipoIdentificacion = oTipoIden;
            oUsuarioNuevo.tipoFuncionario = oTipoFuncionario;
            oUsuarioNuevo.direccion = oDireccion;
            oUsuarioNuevo.cuentaCompleta = false;
            oUsuarioNuevo.estado = true;
            oUsuarioNuevo.estadoSolicitud = false;

            usDB.InsertarUsuario(oUsuarioNuevo);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Mensaje", "Solicitud de cuenta creada correctamente"
                    + "/nEn los proximos dias se le informara acerca del estado de la solicitud");
            
            
            
        } catch (Exception e) {
            FacesMessage mensajeError;
            mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Detalle del error :" + e.toString());
            this.addMessage(mensajeError);
        }

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

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
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
