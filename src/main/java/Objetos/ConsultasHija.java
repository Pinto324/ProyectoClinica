/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.Date;

/**
 *
 * @author branp
 */
public class ConsultasHija extends Consultas {
    private String NombreEspecialidad;
    private String NombrePaciente;

    public ConsultasHija(String NombreEspecialidad, String NombrePaciente, int IdConsultas, int IdPaciente, int IdMedico, int IdEspecialidadMedico, double Porcentaje, Date FechaCreacion, Date FechaAgendada, double Precio, String InformeFinal, String Estado) {
        super(IdConsultas, IdPaciente, IdMedico, IdEspecialidadMedico, Porcentaje, FechaCreacion, FechaAgendada, Precio, InformeFinal, Estado);
        this.NombreEspecialidad = NombreEspecialidad;
        this.NombrePaciente = NombrePaciente;
    }

    public String getNombreEspecialidad() {
        return NombreEspecialidad;
    }

    public void setNombreEspecialidad(String NombreEspecialidad) {
        this.NombreEspecialidad = NombreEspecialidad;
    }

    public String getNombrePaciente() {
        return NombrePaciente;
    }

    public void setNombrePaciente(String NombrePaciente) {
        this.NombrePaciente = NombrePaciente;
    }
    
}
