/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ernesto PC
 */
public class ControladorCorreo {

    private Correo c;

    public ControladorCorreo() {
        c = new Correo();
    }

    public Correo getC() {
        return c;
    }

    public void setC(Correo c) {
        this.c = c;
    }

    public boolean enviarCorreoAceptado(Usuario pUsuario) {
        
            final String username = "pruebaproyectos77953@gmail.com";
            final String password = "prueba1234";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {

                // Define message
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setSubject("Solicitud de Cuenta");
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(pUsuario.correo));
                message.setText("Por este medio le informamos que si solicitud de cuenta ha sido aceptada.\n"
                        + " Adjunto encontrara la nueva contraseña y el codigo de verificacion que debera utilizar\n"
                        + " para ingresar al sistema DEAS. \n"
                        + " Constraseña: " + pUsuario.contrasena + " \n"
                                + "Codigo Verificacion: " + pUsuario.codigoVerificacion + " \n"
                                        + "Link de ingreso: " + "http://localhost:8080/proyectoPrograV/faces/Login.xhtml");
                // Envia el mensaje
                Transport.send(message);
                return true;
            } catch (Exception e) {
                return false;
            }
            
        
    }
    
    public boolean enviarCorreoRechazado(Usuario pUsuario) {
        
            final String username = "pruebaproyectos77953@gmail.com";
            final String password = "prueba1234";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {

                // Define message
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setSubject("Solicitud de Cuenta");
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(pUsuario.correo));
                message.setText("Por este medio le informamos que si solicitud de cuenta ha sido rechazada.\n"
                        + " Para mas informacion contacte al administrativo DEAS");
                Transport.send(message);
                return true;
            } catch (Exception e) {
                return false;
            }
            
        
    }

}
