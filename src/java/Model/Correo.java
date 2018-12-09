/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Ernesto PC
 */

/**
 *
 * @author Ernesto PC
 */
public class Correo {

    private final String correoUsuario = "pruebaproyectos77953@gmail.com";
    private final String contrasenia = "prueba1234";
    private String destino;
    private String asunto;
    private String mensaje;
    private String rutaArchivo = "";
    private String nombreArchivo = "";

    /* @return the correoUsuario*/
    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}
