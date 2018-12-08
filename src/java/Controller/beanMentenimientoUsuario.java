/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.TipoFuncionario;
import Model.TipoFuncionarioDB;
import Model.TipoIdentificacion;
import Model.TipoIdentificacionDB;
import Model.Usuario;
import Model.UsuarioDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author crisz
 */
@Named(value = "beanMentenimientoUsuario")
@SessionScoped
public class beanMentenimientoUsuario implements Serializable {

    List<Usuario> listaUsuarios;
    Usuario Usuario;
    int id;
    String nombre;
    String apellido1;
    String apellido2;
    String correo;
    int tipoFun;
    int tipoIdentificacion;
    String fechaNacimiento;
    boolean visible;
    boolean disable;
    
    Usuario Usuario2;
    boolean mostarMenuMantenimiento;
    boolean mostarMenuReportes;
    boolean mostrarMenuPrestamos;

    public beanMentenimientoUsuario() {
        disable = true;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map<String, Object> session = context.getSessionMap();
        final Object object = session.get("Usuario");
        try {
            if (object == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
            } else {
                Usuario2 = (Usuario) object;
                if (Usuario.tipoFuncionario.TipoUsuario.equalsIgnoreCase("Administrativo")) {
                    mostarMenuMantenimiento = true;
                    mostarMenuReportes = true;

                } else {
                    if (Usuario.tipoFuncionario.TipoUsuario.equalsIgnoreCase("Docente")) {
                        mostarMenuMantenimiento = false;
                        mostarMenuReportes = false;
                        mostrarMenuPrestamos = true;
                    } else {

                        mostarMenuMantenimiento = true;
                        mostarMenuReportes = true;
                        mostrarMenuPrestamos = true;

                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void cerrarSession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        } catch (Exception e) {
        }

    }

    

    public List<Usuario> getListaAdministrativos() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<Usuario> lista = new LinkedList<Usuario>();
        UsuarioDB fDB = new UsuarioDB();
        Usuario n = new Usuario();
        lista = fDB.seleccionarUsuariosAdministrativos();

        return lista;
    }

    public void insertarUsuario() throws SNMPExceptions, SQLException {
       Usuario us = this.getUsuario();
       
       LinkedList<TipoFuncionario> listat= new TipoFuncionarioDB().seleccionarTiposFuncionariosid(this.getTipoFun());
       TipoFuncionario t=listat.get(0);
       
       LinkedList<TipoIdentificacion>listai=new TipoIdentificacionDB().seleccionarId(this.getTipoIdentificacion());
       TipoIdentificacion i= listai.get(0);
       
        
       
        LinkedList<Usuario> lista = new UsuarioDB().seleccionarUsuarioId(us.identificacion);
        if (lista.isEmpty()) {
            Usuario usuarioUp= new Usuario();
            usuarioUp.identificacion=us.identificacion;
            usuarioUp.nombre = us.nombre;
            usuarioUp.apellido1 = us.apellido1;
            usuarioUp.apellido2 = us.apellido2;
            usuarioUp.correo = us.correo;
            usuarioUp.telefono=us.telefono;
            usuarioUp.tipoFuncionario=t;
            usuarioUp.tipoIdentificacion=i;

            UsuarioDB upUser = new UsuarioDB();
            upUser.InsertarUsuario(usuarioUp);
        } else {
           //mensajes
        }
    }

    public void actualizar() throws SNMPExceptions, SQLException {
        Usuario us = this.getUsuario();

        LinkedList<Usuario> lista = new UsuarioDB().seleccionarUsuarioId(us.identificacion);
        if (lista == null) {

        } else {
            Usuario usuarioUp = lista.get(0);
            usuarioUp.nombre = us.nombre;
            usuarioUp.apellido1 = us.apellido1;
            usuarioUp.apellido2 = us.apellido2;
            usuarioUp.correo = us.correo;

            UsuarioDB upUser = new UsuarioDB();
            upUser.ActualizarUsuario(usuarioUp);
            getListaAdministrativos();
        }

    }

    public void eliminar() throws SNMPExceptions, SQLException {

        Usuario us = this.getUsuario();

        LinkedList<Usuario> lista = new UsuarioDB().seleccionarUsuarioId(us.identificacion);
        if (lista == null) {

        } else {
            Usuario usuarioDel = lista.get(0);

            UsuarioDB upUser = new UsuarioDB();
            upUser.EliminarUsuario(usuarioDel);
            getListaAdministrativos();

        }
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

    public LinkedList<SelectItem> getListaTipoFuncionario() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<TipoFuncionario> lista = new LinkedList<TipoFuncionario>();
        TipoFuncionarioDB fDB = new TipoFuncionarioDB();
        TipoFuncionario n = new TipoFuncionario();
        lista = fDB.seleccionarTiposFuncionarios();

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator(); iter.hasNext();) {

            TipoFuncionario tipoFun = (TipoFuncionario) iter.next();
            id = tipoFun.getId();
            tipo = tipoFun.getTipoUsuario();
            resultList.add(new SelectItem(id, tipo));
        }
        return resultList;

    }

    public void limpiaCampos() {
        visible = true;
        disable = false;
        this.Usuario.nombre="";
        this.Usuario.apellido1="";
        this.Usuario.apellido2="";
        this.Usuario.correo="";
        this.Usuario.telefono="";
        this.Usuario.identificacion=0;
   
    }

    public void ocultar() {
        visible = false;
        disable = true;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario2() {
        return Usuario2;
    }

    public void setUsuario2(Usuario Usuario2) {
        this.Usuario2 = Usuario2;
    }

    public boolean isMostarMenuMantenimiento() {
        return mostarMenuMantenimiento;
    }

    public void setMostarMenuMantenimiento(boolean mostarMenuMantenimiento) {
        this.mostarMenuMantenimiento = mostarMenuMantenimiento;
    }

    public boolean isMostarMenuReportes() {
        return mostarMenuReportes;
    }

    public void setMostarMenuReportes(boolean mostarMenuReportes) {
        this.mostarMenuReportes = mostarMenuReportes;
    }

    public boolean isMostrarMenuPrestamos() {
        return mostrarMenuPrestamos;
    }

    public void setMostrarMenuPrestamos(boolean mostrarMenuPrestamos) {
        this.mostrarMenuPrestamos = mostrarMenuPrestamos;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public int getTipoFun() {
        return tipoFun;
    }

    public void setTipoFun(int tipoFun) {
        this.tipoFun = tipoFun;
    }

    public int getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(int tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
