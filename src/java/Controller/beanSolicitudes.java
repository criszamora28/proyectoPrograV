/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.ControladorCorreo;
import Model.Usuario;
import Model.UsuarioDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ernesto PC
 */
@Named(value = "beanSolicitudes")
@SessionScoped
public class beanSolicitudes implements Serializable {

    boolean disable;
    Usuario usuario;

    Usuario Usuario2;
    boolean mostarMenuMantenimiento;
    boolean mostarMenuReportes;
    boolean mostrarMenuPrestamos;
    boolean ocularBotones;

    public beanSolicitudes() {
//         disable = true;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map<String, Object> session = context.getSessionMap();
        final Object object = session.get("Usuario");
//        try {
//            if (object == null) {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
//            }else{
//                Usuario2 = (Usuario)object;
//                this.mostrarMensaje("Ingreso hecho correctamente", "Bienvenido " + Usuario2.nombre);
//                if (Usuario2.tipoFuncionario.TipoUsuario.equalsIgnoreCase("Administrativo")) {
//                    mostarMenuMantenimiento = true;
//                    mostarMenuReportes = true;
//                    mostarMenuMantenimiento = true;
//                    mostarMenuReportes = true;
//                    mostrarMenuPrestamos = true;
//
//                } else {
//                    if (Usuario2.tipoFuncionario.TipoUsuario.equalsIgnoreCase("Docente")) {
//                        mostarMenuMantenimiento = false;
//                        mostarMenuReportes = false;
//                        mostrarMenuPrestamos = true;
//                    } 
//                }
//            }
//        } catch (Exception e) {
//        }
    }

    private void mostrarMensaje(String encMensaje, String detMensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(encMensaje, detMensaje));
    }

    public void cerrarSession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        } catch (Exception e) {
        }

    }

    public List<Usuario> getListaPendientes() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<Usuario> lista = new LinkedList<Usuario>();
        UsuarioDB fDB = new UsuarioDB();
        Usuario n = new Usuario();
        lista = fDB.seleccionarListaUsuarios();

        return lista;
    }

    public void aceptarSolicitud() throws SNMPExceptions, SQLException {
        try {

            UsuarioDB oUsuarioDB = new UsuarioDB();
            Usuario us = this.getUsuario();
            
            if (us.estadoSolicitud == true) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion!", "Esta solicitud ya ha sido aceptada!"));
                return;

            }
            us.estadoSolicitud = true;
            us.codigoVerificacion = this.generarCodigoVerificacion();
            us.contrasena = generarContraseña();

            oUsuarioDB.ActualizarUsuarioSilicitudAceptada(us);
            //ACA llama a la clase enviaCorreo, agarra el correo del usuario y le adjunta la clave generada. Tambien se actualiza el usarioDB
            ControladorCorreo controlador = new ControladorCorreo();
            controlador.enviarCorreoAceptado(us);

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Solicitud aprovada correctamente \n\n Correo enviado!"));

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!", e.toString()));

        }

    }

    public String generarCodigoVerificacion() {
        String codigo = "";
        codigo += "abc123";
        return codigo;
    }

    public String generarContraseña() {
        return "Xdwe37cdsn";
    }

    public void rechazarSolicitud() throws SNMPExceptions, SQLException {
        Usuario us = this.getUsuario();
        if (us.estadoSolicitud == true) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion!", "Esta solicitud ya ha sido aceptada!"));
                return;

            }
        try {
            ControladorCorreo controlador = new ControladorCorreo();
            controlador.enviarCorreoRechazado(us);

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Correo enviado!"));
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.toString()));
        }
        

    }

    public String convertirEstadoCuenta(Usuario pUsuario) {
        String estado = "";
        if (pUsuario.estadoSolicitud == true) {
            estado = "Aceptado";
            this.ocularBotones = false;
        } else {
            estado = "Rechazado";
        }
        return estado;
    }

    public boolean isOcularBotones() {
        return ocularBotones;
    }

    public void setOcularBotones(boolean ocularBotones) {
        this.ocularBotones = ocularBotones;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public Usuario getUsuario() {
        return usuario;
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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
