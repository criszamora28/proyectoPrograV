/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.DetalleReservacion;
import Model.Infraestructura;
import Model.InfraestructuraDB;
import Model.Recurso;
import Model.RecursoDB;
import Model.Reservacion;
import Model.ReservacionDB;
import Model.TipoReservacion;
import Model.Usuario;
import Model.UsuarioDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author crisz
 */
@Named(value = "scheduleView")
@SessionScoped
public class ScheduleView implements Serializable {

    private ScheduleModel eventModel;

    int TipoReservacion;
    String infraestructura;
    LinkedList listaIn = new LinkedList();
    boolean habilitarCampos;
    LinkedList listaRecurso = new LinkedList();
    Usuario Usuario;
    boolean mostarMenuMantenimiento;
    boolean mostarMenuReportes;
    boolean mostrarMenuPrestamos;

    //private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();

    public ScheduleView() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map<String, Object> session = context.getSessionMap();
        final Object object = session.get("Usuario");
        try {
            if (object == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
            } else {
                Usuario = (Usuario) object;
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

    @PostConstruct
    public void init() {
        this.habilitarCampos = false;

        eventModel = new DefaultScheduleModel();
        ReservacionDB oReservacionDB = new ReservacionDB();

        try {
            for (Reservacion oReservacion : oReservacionDB.selectReservacion()) {
//                if (oReservacion.estadoSolicitud == true && oReservacion.estadoRegistro == true) {
//                    eventModel.addEvent(new DefaultScheduleEvent(oReservacion.titulo, oReservacion.fechaInicio, 
//                            oReservacion.fechaFinal, oReservacion.todoElDia));
//                    
//                }

            }
        } catch (Exception e) {
        }

//        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
//        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
//         
//        lazyEventModel = new LazyScheduleModel() {
//             
//            @Override
//            public void loadEvents(Date start, Date end) {
//                Date random = getRandomDate(start);
//                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
//                 
//                random = getRandomDate(start);
//                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
//            }   
//        };
    }

    public void addEvent() {
        if (event.getId() == null) {

            eventModel.addEvent(event);
            try {
                ReservacionDB oReservacionDB = new ReservacionDB();
                UsuarioDB oUsuarioDB = new UsuarioDB();
                InfraestructuraDB oInfraestructuraDB = new InfraestructuraDB();
                RecursoDB oRecursoDB = new RecursoDB();
                LinkedList<Usuario> listaUsuario = new LinkedList<>();
                listaUsuario = oUsuarioDB.seleccionarUsuarioId(123456789);

                Reservacion oReservacion = new Reservacion();
                oReservacion.id = event.getId();
                oReservacion.TipoReservacion = oReservacionDB.selectTipoReservacionPorId(TipoReservacion);
                oReservacion.Usuario = listaUsuario.get(0);

                oReservacion.idUsuarioIngresoRegistro = oReservacion.Usuario.identificacion;
                oReservacion.fechaIngresoRegistro = Date.from(Instant.now());
                oReservacion.estadoRegistro = true;

                oReservacionDB.InsertarReservacion(oReservacion);

                DetalleReservacion oDetalle = new DetalleReservacion();
                oDetalle.Reservacion = oReservacion;

                if (!listaIn.isEmpty()) {
                    Iterator iter = listaIn.iterator();
                    while (iter.hasNext()) {
                        String idInfra = (String) iter.next();
                        oDetalle.Infraestructura = oInfraestructuraDB.seleccionarInfraestructuraId2(idInfra);
                        oDetalle.titulo = event.getTitle();
                        oDetalle.fechaInicio = event.getStartDate();
                        oDetalle.fechaFinal = event.getEndDate();
                        oDetalle.todoElDia = event.isAllDay();
                        oDetalle.editable = false;
                        oDetalle.estadoSolicitud = false;
                        oReservacionDB.InsertarDetalleReservacionInfraestructura(oDetalle);
                    }

                }

                if (!listaRecurso.isEmpty()) {
                    Iterator iter = listaRecurso.iterator();
                    while (iter.hasNext()) {
                        String idRecurso = (String) iter.next();
                        oDetalle.Recurso = oRecursoDB.seleccionarRecursoId2(idRecurso);
                        oDetalle.titulo = event.getTitle();
                        oDetalle.fechaInicio = event.getStartDate();
                        oDetalle.fechaFinal = event.getEndDate();
                        oDetalle.todoElDia = event.isAllDay();
                        oDetalle.editable = false;
                        oDetalle.estadoSolicitud = false;
                        oReservacionDB.InsertarDetalleReservacionRecurso(oDetalle);
                    }

                }

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Exito", "Su reservacion ha sido enviada para valoracion"));

            } catch (Exception e) {
                FacesMessage mensajeError;
                mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datelle :" + e.getMessage());
                this.addMessage(mensajeError);
            }

        } else {
            eventModel.updateEvent(event);

        }
        event = new DefaultScheduleEvent();
    }

    public LinkedList<SelectItem> getListaInfraestructura() {
        LinkedList listaInfra = new LinkedList();

        try {
            InfraestructuraDB oInfraestructuraDB = new InfraestructuraDB();

            for (Infraestructura oInfraestructura : oInfraestructuraDB.seleccionarInfraestructura()) {
                listaInfra.add(new SelectItem(oInfraestructura.getIdInfraestructura(), oInfraestructura.toString()));
            }

        } catch (SNMPExceptions | SQLException e) {
        }

        return listaInfra;

    }

    public LinkedList<SelectItem> getListaTipoReservacion() {
        LinkedList listaTipo = new LinkedList();

        try {
            ReservacionDB oReservacionDB = new ReservacionDB();

            for (TipoReservacion tipoReservacion : oReservacionDB.selectTipoReservacion()) {
                listaTipo.add(new SelectItem(tipoReservacion.getId(), tipoReservacion.getTipo()));
            }

        } catch (SNMPExceptions | SQLException e) {
        }

        return listaTipo;

    }

    public LinkedList<SelectItem> getListaRecursos() {
        LinkedList listaRecursos = new LinkedList();

        try {
            RecursoDB oRecursoDB = new RecursoDB();

            for (Recurso oRecurso : oRecursoDB.seleccionarRecurso()) {
                listaRecursos.add(new SelectItem(oRecurso.getId(), oRecurso.getTipo() + " - " + oRecurso.getDescripcion()));
            }

        } catch (SNMPExceptions | SQLException e) {
        }

        return listaRecursos;

    }

    public void cerrarSession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        } catch (Exception e) {
        }

    }

    public boolean habilitarCampos() {
        return true;
    }

    public boolean deshabilitarCampos() {
        return false;
    }

    public LinkedList getListaRecurso() {
        return listaRecurso;
    }

    public void setListaRecurso(LinkedList listaRecurso) {
        this.listaRecurso = listaRecurso;
    }

    public String getInfraestructura() {
        return infraestructura;
    }

    public void setInfraestructura(String infraestructura) {
        this.infraestructura = infraestructura;
    }

    public boolean isHabilitarCampos() {
        return habilitarCampos;
    }

    public void setHabilitarCampos(boolean habilitarCampos) {
        this.habilitarCampos = habilitarCampos;
    }

    public LinkedList getListaIn() {
        return listaIn;
    }

    public void setListaIn(LinkedList listaIn) {
        this.listaIn = listaIn;
    }

    public int getTipoReservacion() {
        return TipoReservacion;
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

    public void setTipoReservacion(int TipoReservacion) {
        this.TipoReservacion = TipoReservacion;
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

//    public ScheduleModel getLazyEventModel() {
//        return lazyEventModel;
//    }
    
    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
