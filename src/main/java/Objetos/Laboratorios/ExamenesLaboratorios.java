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
public class ExamenesLaboratorios {
    private int IdDelLab;
    private int IdExamen;
    private double precio;
    private String Estado;

    public ExamenesLaboratorios( int IdDelLab, int IdExamen, double precio, String Estado) {
        this.IdDelLab = IdDelLab;
        this.IdExamen = IdExamen;
        this.precio = precio;
        this.Estado = Estado;
    }

    public int getIdDelLab() {
        return IdDelLab;
    }

    public void setIdDelLab(int IdDelLab) {
        this.IdDelLab = IdDelLab;
    }

    public int getIdExamen() {
        return IdExamen;
    }

    public void setIdExamen(int IdExamen) {
        this.IdExamen = IdExamen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
}
