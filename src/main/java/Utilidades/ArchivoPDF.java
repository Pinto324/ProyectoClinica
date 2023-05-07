/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

/**
 *
 * @author branp
 */
public class ArchivoPDF {
    private String Nombre;
    private byte[] pdf;

    public ArchivoPDF(String Nombre, byte[] pdf) {
        this.Nombre = Nombre;
        this.pdf = pdf;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }
    
}
