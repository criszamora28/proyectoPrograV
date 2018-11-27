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
public class CursoDeas {

    public String id;
    public ProgramaDeas idPrograma;
    public String nombreCurso;
    public String descripcion;
    //fechas

    public CursoDeas() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProgramaDeas getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(ProgramaDeas idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
