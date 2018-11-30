/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.SSLHandshakeException;

/**
 *
 * @author Ernesto PC
 */
public class ControladorCorreo {

    private Correo c = new Correo();

    public Correo getC() {
        return c;
    }

    public void setC(Correo c) {
        this.c = c;
    }

    public boolean enviarCorreo(String email) {
        try {
            
          


            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
          //  props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
           // props.setProperty("mail.smtp.ssl.trust", "smtpserver");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.user", c.getCorreoUsuario());
            props.setProperty("mail.smtp.auth", "true");
            Session s = Session.getDefaultInstance(props, null);
            BodyPart texto = new MimeBodyPart();
            texto.setText("http://localhost:8080/PruebaCorreo/faces/pruebaInicio.html");//link que se le va a enviar al usuario
            
            
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);
//            /if (!c.getRutaArchivo().equals("")) {
//                m.addBodyPart(adjunto);
//            }/
      
            
            
            
            
            BodyPart adjunto = new MimeBodyPart();
             MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(c.getCorreoUsuario()));//aca va el correo 
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(email));//aca va el destino
            mensaje.setSubject("funciona");
            mensaje.setText("http://localhost:8080/PruebaCorreo/faces/pruebaInicio.html");
            mensaje.setContent(m);
            Transport t = s.getTransport("smtp");
            t.connect(c.getCorreoUsuario(), c.getContrasenia());
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

