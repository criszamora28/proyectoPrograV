/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.CursoDeas;
import Model.CursoDeasDB;
import Model.ProgramaDeas;
import Model.ProgramaDeasDB;
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
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Ernesto PC
 */
@Named(value = "beanMantenimientoCursoDeas")
@SessionScoped
public class beanMantenimientoCursoDeas implements Serializable {

    /**
     * Creates a new instance of beanMantenimientoCursoDeas
     */
    CursoDeas cursoDeas;
    boolean visible;
    boolean disable;
    String idU;
    String idProgra;

    Usuario Usuario;
    boolean mostarMenuMantenimiento;
    boolean mostarMenuReportes;
    boolean mostrarMenuPrestamos;
    
    @PostConstruct
    private void CargaPagina()
    {
        disable = true;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	final Map<String, Object> session = context.getSessionMap();
	final Object object = session.get("Usuario");
        try {
            if (object == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
            }else{
                Usuario = (Usuario)object;
                if (Usuario.tipoFuncionario.TipoUsuario.equalsIgnoreCase("Administrativo")) {
                    mostarMenuMantenimiento = true;
                    mostarMenuReportes = true;
                    
                } else {
                    if (Usuario.tipoFuncionario.TipoUsuario.equalsIgnoreCase("Docente")) {
                        mostarMenuMantenimiento = false;
                        mostarMenuReportes = false;
                        mostrarMenuPrestamos = true;
                    } else {
                        if (Usuario.tipoFuncionario.TipoUsuario.equalsIgnoreCase("Administrativo")) {
                            mostarMenuMantenimiento = true;
                            mostarMenuReportes = true;
                            mostrarMenuPrestamos = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        
    }
    
    public void cerrarSession(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        } catch (Exception e) {
        }
        
    }

    public List<CursoDeas> getListaCursos() throws SNMPExceptions, SQLException {
        int id = 0;
        String tipo = "";

        LinkedList<CursoDeas> lista = new LinkedList<CursoDeas>();
        CursoDeasDB fDB = new CursoDeasDB();
        CursoDeas n = new CursoDeas();
        lista = fDB.seleccionarCursosDeas();

        return lista;
    }

    public LinkedList<SelectItem> getListaProgramas() throws SNMPExceptions, SQLException {
        String id = "";
        String nombre = "";

        LinkedList<ProgramaDeas> lista = new LinkedList<ProgramaDeas>();
        ProgramaDeasDB fDB = new ProgramaDeasDB();
        ProgramaDeas n = new ProgramaDeas();
        lista = fDB.seleccionarProgramaDeas();

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator(); iter.hasNext();) {

            ProgramaDeas tipoFun = (ProgramaDeas) iter.next();
            id = tipoFun.getId();
            nombre = tipoFun.getNombrePrograma();
            resultList.add(new SelectItem(id, nombre));
        }
        return resultList;

    }

    public LinkedList<SelectItem> getListaEstado() throws SNMPExceptions, SQLException {

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Activo"));
        resultList.add(new SelectItem(1, "Inactivo"));

        return resultList;

    }

    public void insertarCurso() throws SNMPExceptions, SQLException {
        CursoDeas cu = this.cursoDeas;
        LinkedList<ProgramaDeas> lista1 = new ProgramaDeasDB().seleccionarProgramaDeasId(this.getIdProgra());
        LinkedList<CursoDeas> lista = new CursoDeasDB().seleccionarCursosDeasId(cu.id);
        if (lista.isEmpty()) {
            CursoDeas nCurso = new CursoDeas();
            nCurso.id = cu.id;
            nCurso.idPrograma = lista1.get(0);
            nCurso.descripcion = cu.descripcion;
            nCurso.nombreCurso = cu.nombreCurso;

            CursoDeasDB db = new CursoDeasDB();
            db.InsertarCursoDeas(nCurso);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Curso ingresado correctamente!"));
        } else {
            //mensajito
        }

    }

    public void actualizar() throws SNMPExceptions, SQLException {
        CursoDeas us = this.getCursoDeas();

        LinkedList<CursoDeas> lista = new CursoDeasDB().seleccionarCursosDeasId(us.id);
        LinkedList<ProgramaDeas> lista1 = new ProgramaDeasDB().seleccionarProgramaDeasId(this.getIdProgra());
        
        if (lista.isEmpty()) {

        } else {
            CursoDeas cursoDeasUp = lista.get(0);
            cursoDeasUp.id = us.id;
            cursoDeasUp.nombreCurso = us.nombreCurso;
            cursoDeasUp.descripcion = us.descripcion;
            cursoDeasUp.idPrograma = lista1.get(0);
            
            CursoDeasDB upUser = new CursoDeasDB();
            upUser.ActualizarCurso(cursoDeasUp);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Curso editado correctamente!"));

        }

    }

    public void eliminar() throws SNMPExceptions, SQLException {
        CursoDeas us = this.getCursoDeas();

        LinkedList<CursoDeas> lista = new CursoDeasDB().seleccionarCursosDeasId(us.id);
        if (lista == null) {

        } else {
            CursoDeas cursoDel = lista.get(0);

            CursoDeasDB upUser = new CursoDeasDB();
            upUser.EliminarCurso(cursoDel);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito!", "Curso eliminado correctamente!"));

        }
    }

    public void limpiaCampos() {
        visible = true;
        disable = false;

    }

    public void ocultar() {
        visible = false;
        disable = true;
    }
    
    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
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
    
    public String getIdProgra() {
        return idProgra;
    }

    public void setIdProgra(String idProgra) {
        this.idProgra = idProgra;
    }

    public CursoDeas getCursoDeas() {
        return cursoDeas;
    }

    public void setCursoDeas(CursoDeas cursoDeas) {
        this.cursoDeas = cursoDeas;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getIdU() {
        return idU;
    }

    public void setIdU(String idU) {
        this.idU = idU;
    }

}
