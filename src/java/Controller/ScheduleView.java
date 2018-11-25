/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
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
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
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
    //private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
//        ReservacionDB oReservacionDB = new ReservacionDB();
//
//        try {
//            for (Reservacion oReservacion : oReservacionDB.selectReservacion()) {
//                if (oReservacion.estadoSolicitud == true && oReservacion.estadoRegistro == true) {
//                    eventModel.addEvent(new DefaultScheduleEvent(oReservacion.titulo, oReservacion.fechaInicio, 
//                            oReservacion.fechaFinal, oReservacion.todoElDia));
//                }
//                    
//                    
//            }
//        } catch (Exception e) {
//        }

        
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

    public int getTipoReservacion() {
        return TipoReservacion;
    }

    public void setTipoReservacion(int TipoReservacion) {
        this.TipoReservacion = TipoReservacion;
    }

    

    public LinkedList<SelectItem> getListaTipoReservacion() {
        LinkedList listaTipo = new LinkedList();
        
        try {
            ReservacionDB oReservacionDB = new ReservacionDB();
            
            
            for (TipoReservacion tipoReservacion : oReservacionDB.selectTipoReservacion()) {
                listaTipo.add(new SelectItem(tipoReservacion.getId(),tipoReservacion.getTipo()));
            }
            
        } catch (SNMPExceptions | SQLException e) {
        }
        
        return listaTipo;
        
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

    public void addEvent() {
        if (event.getId() == null) {
            
            eventModel.addEvent(event);
            try {
                ReservacionDB oReservacionDB = new ReservacionDB();
                UsuarioDB oUsuarioDB = new UsuarioDB();
                LinkedList<Usuario> listaUsuario = new LinkedList<>();
                listaUsuario = oUsuarioDB.seleccionarUsuarioId(123456789);

                Reservacion oReservacion = new Reservacion();
                oReservacion.id = event.getId();
                oReservacion.TipoReservacion = TipoReservacion;
                oReservacion.Usuario = listaUsuario.get(0);
                oReservacion.titulo = event.getTitle();
                oReservacion.fechaInicio = event.getStartDate();
                oReservacion.fechaFinal = event.getEndDate();
                oReservacion.todoElDia = event.isAllDay();
                oReservacion.editable = false;
                oReservacion.estadoSolicitud = false;
                oReservacion.idUsuarioIngresoRegistro = 1;
                oReservacion.fechaIngresoRegistro = Date.from(Instant.now());
                oReservacion.estadoRegistro = true;

                oReservacionDB.InsertarReservacion(oReservacion);

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
