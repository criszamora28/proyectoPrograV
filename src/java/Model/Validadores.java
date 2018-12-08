/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author crisz
 */
public class Validadores {
    //Atributos a nivel de la clase
    private static String mensajeError;

    
    public static boolean validarEntero(String pValor) {
        boolean vResultado = false;
        int vValor = 0;

        try {
            mensajeError = "";
            vValor = Integer.parseInt(pValor);
            vResultado = true;
        } catch (Exception e) {
            mensajeError = e.toString();
        }
        return vResultado;
    }

    
    public static boolean validarDouble(String pValor) {
        boolean vResultado = false;
        double vValor = 0d;

        try {
            mensajeError = "";
            vValor = Double.parseDouble(pValor);
            vResultado = true;
        } catch (Exception e) {
            mensajeError = e.toString();
        }
        return vResultado;
    }

    
    public static boolean validarFloat(String pValor) {
        boolean vResultado = false;
        double vValor = 0f;

        try {
            mensajeError = "";
            vValor = Float.parseFloat(pValor);
            vResultado = true;
        } catch (Exception e) {
            mensajeError = e.toString();
        }
        return vResultado;
    }

    
    public static boolean validarFecha(String pValor) {
        SimpleDateFormat vFechaFormato = new SimpleDateFormat("dd/MM/yyyy");
        boolean vResultado = false;
        Date vFecha;

        try {
            mensajeError = "";
            
            //Tomar el valor del jTxtValor y asignarlo a la variable tipo fecha con la mascara indicada
            vFecha = vFechaFormato.parse( pValor );
            
            //Hacer una comparación del tipo de dato almacenado en la variable Date vFecha
            //Ya que si la misma es incorrecta va almacenar otra fecha.
            //Por ejemplo 30/02/2017 el vFechaFormato.parse(pValor) = 02/03/2017
            //Siendo una fecha invalida. 
            //Si la fecha la convierte y es correcta, al obtener el format de la fecha almacenada en vFecha
            //por medio del "parse" deberían ser iguala en la siguiente comparación
            if ( vFechaFormato.format(vFecha).equals(pValor) ){
                vResultado = true;  
            }
        } catch (Exception e) {
            mensajeError = e.toString();
        }
        return vResultado;
    }

    
    public static boolean validarFechaHora(String pValor) {
        SimpleDateFormat vFechaFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        boolean vResultado = false;
        Date vFecha;

        try {
            mensajeError = "";            
            //Tomar el valor del jTxtValor y asignarlo a la variable tipo fecha con la mascara indicada
            vFecha = vFechaFormato.parse( pValor );
            
            //Hacer una comparación del tipo de dato almacenado en la variable Date vFecha
            //Ya que si la misma es incorrecta va almacenar otra fecha.
            //Por ejemplo 30/02/2017 el vFechaFormato.parse(pValor) = 02/03/2017
            //Siendo una fecha invalida. 
            //Si la fecha la convierte y es correcta, al obtener el format de la fecha almacenada en vFecha
            //por medio del "parse" deberían ser iguala en la siguiente comparación
            if ( vFechaFormato.format(vFecha).equals(pValor) ){
                vResultado = true;  
            }
        } catch (Exception e) {
            mensajeError = e.toString();
        }
        return vResultado;
    }

    
    public static boolean validarFechaHoraSegundos(String pValor) {
        SimpleDateFormat vFechaFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        boolean vResultado = false;
        Date vFecha;

        try {
            mensajeError = "";            
            //Tomar el valor del jTxtValor y asignarlo a la variable tipo fecha con la mascara indicada
            vFecha = vFechaFormato.parse( pValor );
            
            //Hacer una comparación del tipo de dato almacenado en la variable Date vFecha
            //Ya que si la misma es incorrecta va almacenar otra fecha.
            //Por ejemplo 30/02/2017 el vFechaFormato.parse(pValor) = 02/03/2017
            //Siendo una fecha invalida. 
            //Si la fecha la convierte y es correcta, al obtener el format de la fecha almacenada en vFecha
            //por medio del "parse" deberían ser iguala en la siguiente comparación
            if ( vFechaFormato.format(vFecha).equals(pValor) ){
                vResultado = true;  
            }
        } catch (Exception e) {
            mensajeError = e.toString();
        }
        return vResultado;
    }
    
        
    public static boolean validarVacio(String pValor){
        boolean vResultado = false;
        
        if (pValor.trim().length()<=0){
            vResultado = true;
        }
                
        return vResultado;
    }
    
    public static String getMensajeError(){
        return mensajeError;
    }    
}
