/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author branp
 */
public class EspecialidadHijaMedicos extends Especialidades {
    private int IdEspecialidad;
    private int IdMedico;
    private double Precio;
    private String Estado;

    public EspecialidadHijaMedicos(int IdEspecialidad, int IdMedico, double Precio, String Estado, int IdEspecialidades, String NombreEspecialidad, String DescripcionEspecialidad) {
        super(IdEspecialidades, NombreEspecialidad, DescripcionEspecialidad);
        this.IdEspecialidad = IdEspecialidad;
        this.IdMedico = IdMedico;
        this.Precio = Precio;
        this.Estado = Estado;
    }

    public int getIdEspecialidad() {
        return IdEspecialidad;
    }

    public void setIdEspecialidad(int IdEspecialidad) {
        this.IdEspecialidad = IdEspecialidad;
    }

    public int getIdMedico() {
        return IdMedico;
    }

    public void setIdMedico(int IdMedico) {
        this.IdMedico = IdMedico;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
}
