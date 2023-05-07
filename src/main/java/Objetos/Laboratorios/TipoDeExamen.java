/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Laboratorios;

/**
 *
 * @author branp
 */
public class TipoDeExamen {
    private int idTipoDeExamenes;
    private String nombreExamen;
    private String descripcionExamen;

    public TipoDeExamen(int idTipoDeExamenes, String nombreExamen, String descripcionExamen) {
        this.idTipoDeExamenes = idTipoDeExamenes;
        this.nombreExamen = nombreExamen;
        this.descripcionExamen = descripcionExamen;
    }
    public int getIdTipoDeExamenes() {
        return idTipoDeExamenes;
    }

    public void setIdTipoDeExamenes(int idTipoDeExamenes) {
        this.idTipoDeExamenes = idTipoDeExamenes;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    public String getDescripcionExamen() {
        return descripcionExamen;
    }

    public void setDescripcionExamen(String descripcionExamen) {
        this.descripcionExamen = descripcionExamen;
    }
    
    
}
