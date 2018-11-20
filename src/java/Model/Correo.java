/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Convert;

/**
 *
 * @author Ernesto PC
 */
public class Correo {

    String error;

    public Correo() {

    }

  //  public static void enviar() {
        //        String to = "pruebaproyectos77953@gmail.com";//change accordingly  
        //        String from = "ernestoc77953@gmail.com";
        //       
        //        String host = "localhost";//or IP address  
        //
        //        //Get the session object  
        //        Properties properties = System.getProperties();
        //        properties.setProperty("mail.smtp.host", host);
        //        Session session = Session.getDefaultInstance(properties);
        //
        //        //compose the message  
        //        try {
        //            MimeMessage message = new MimeMessage(session);
        //            message.setFrom(new InternetAddress(from));
        //            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //            message.setSubject("Ping");
        //            message.setText("Hello, this is example of sending email  ");
        //
        //            // Send message  
        //            Transport.send(message);
        //            System.out.println("message sent successfully....");
        //
        //        } catch (MessagingException mex) {
        //            mex.printStackTrace();
        //        }

    public void enviarConGMail() {
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
     String remitente = "pruebaproyectos77953@gmail.com";  //Para la dirección nomcuenta@gmail.com
     String destino="ernestoc77953@gmail.com";
     String mensaje="fddsfa";
     String cuer="asdfsadf";

    Properties props = System.getProperties();

    props.put ("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
    props.put ("mail.smtp.user", remitente);
    props.put ("mail.smtp.clave", "prueba1234");    //La clave de la cuenta
    props.put ( "mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
    props.put ("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
    props.put ("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    
        try {
         Address adress= new InternetAddress(destino);
        message.setFrom(new InternetAddress(remitente));
        message.addRecipient(Message.RecipientType.TO,  adress);   //Se podrían añadir varios de la misma manera
        message.setSubject(mensaje);
        message.setText(cuer);
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", remitente, "prueba1234");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    catch (MessagingException me ) {
        me.printStackTrace();   //Si se produce un error
    }
}



   
    
    
    
}
