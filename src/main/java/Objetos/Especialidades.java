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
public class Especialidades {
    private int IdEspecialidades;
    private String NombreEspecialidad;
    private String DescripcionEspecialidad;

    public Especialidades(int IdEspecialidades, String NombreEspecialidad, String DescripcionEspecialidad) {
        this.IdEspecialidades = IdEspecialidades;
        this.NombreEspecialidad = NombreEspecialidad;
        this.DescripcionEspecialidad = DescripcionEspecialidad;
    }

    public int getIdEspecialidades() {
        return IdEspecialidades;
    }

    public void setIdEspecialidades(int IdEspecialidades) {
        this.IdEspecialidades = IdEspecialidades;
    }

    public String getNombreEspecialidad() {
        return NombreEspecialidad;
    }

    public void setNombreEspecialidad(String NombreEspecialidad) {
        this.NombreEspecialidad = NombreEspecialidad;
    }

    public String getDescripcionEspecialidad() {
        return DescripcionEspecialidad;
    }

    public void setDescripcionEspecialidad(String DescripcionEspecialidad) {
        this.DescripcionEspecialidad = DescripcionEspecialidad;
    }
    
}
