/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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

    public boolean enviarCorreo(Usuario pUsuario) {
        
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
                message.setText("Estimado usuario(a) por este medio le informamos que su solicitud de cuenta de usuario"
                        + " ha sido aceptada. \n Adjunto encontrara la contraseña que debera ser cambiada y el codigo de"
                        + " verificacion de la cuenta. \n "
                        + " Contraseña: " + pUsuario.contrasena + "\n" 
                        + " Codigo de verificacion: " + pUsuario.codigoVerificacion  + "\n"
                        + " Link de ingreso: " + "http://localhost:8080/proyectoPrograV/faces/Login.xhtml");
                // Envia el mensaje
                Transport.send(message);
                return true;
            } catch (Exception e) {
                return false;
            }
//            Properties props = new Properties();
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//            props.setProperty("mail.smtp.ssl.trust", "smtpserver");
//            props.setProperty("mail.smtp.starttls.enable", "true");
//            props.setProperty("mail.smtp.port", "465");
//            props.setProperty("mail.smtp.user", c.getCorreoUsuario());
//            props.setProperty("mail.smtp.auth", "true");
//            Session s = Session.getDefaultInstance(props, null);
//            BodyPart texto = new MimeBodyPart();
//            texto.setText("http://localhost:8080/proyectoPrograV/faces/Login.xhtml");//link que se le va a enviar al usuario
//            
//            
//            MimeMultipart m = new MimeMultipart();
//            m.addBodyPart(texto);
////            /if (!c.getRutaArchivo().equals("")) {
////                m.addBodyPart(adjunto);
////            }/
//
//            BodyPart adjunto = new MimeBodyPart();
//            MimeMessage mensaje = new MimeMessage(s);
//            mensaje.setFrom(new InternetAddress(c.getCorreoUsuario()));//aca va el correo 
//            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(pUsuario.correo));//aca va el destino
//            mensaje.setSubject("funciona");
//            mensaje.setText("http://localhost:8080/proyectoPrograV/faces/Login.xhtml");
//            mensaje.setContent(m);
//            Transport t = s.getTransport("smtp");
//            t.connect(c.getCorreoUsuario(), c.getContrasenia());
//            t.sendMessage(mensaje, mensaje.getAllRecipients());
//            t.close();
            
        
    }

}
