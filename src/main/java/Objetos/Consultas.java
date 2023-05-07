/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author branp
 */
public class Consultas {
    private int IdConsultas;
    private int IdPaciente;
    private int IdMedico;
    private int IdEspecialidadMedico;
    private double Porcentaje;
    private Date FechaCreacion;
    private Date FechaAgendada;
    private double Precio;
    private String InformeFinal;
    private String Estado;

    public Consultas(int IdConsultas, int IdPaciente, int IdMedico, int IdEspecialidadMedico, double Porcentaje, Date FechaCreacion, Date FechaAgendada, double Precio, String InformeFinal, String Estado) {
        this.IdConsultas = IdConsultas;
        this.IdPaciente = IdPaciente;
        this.IdMedico = IdMedico;
        this.IdEspecialidadMedico = IdEspecialidadMedico;
        this.Porcentaje = Porcentaje;
        this.FechaCreacion = FechaCreacion;
        this.FechaAgendada = FechaAgendada;
        this.Precio = Precio;
        this.InformeFinal = InformeFinal;
        this.Estado = Estado;
    }

    public int getIdConsultas() {
        return IdConsultas;
    }

    public void setIdConsultas(int IdConsultas) {
        this.IdConsultas = IdConsultas;
    }

    public int getIdPaciente() {
        return IdPaciente;
    }

    public void setIdPaciente(int IdPaciente) {
        this.IdPaciente = IdPaciente;
    }

    public int getIdMedico() {
        return IdMedico;
    }

    public void setIdMedico(int IdMedico) {
        this.IdMedico = IdMedico;
    }

    public int getIdEspecialidadMedico() {
        return IdEspecialidadMedico;
    }

    public void setIdEspecialidadMedico(int IdEspecialidadMedico) {
        this.IdEspecialidadMedico = IdEspecialidadMedico;
    }

    public double getPorcentaje() {
        return Porcentaje;
    }

    public void setPorcentaje(double Porcentaje) {
        this.Porcentaje = Porcentaje;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public Date getFechaAgendada() {
        return FechaAgendada;
    }

    public void setFechaAgendada(Date FechaAgendada) {
        this.FechaAgendada = FechaAgendada;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getInformeFinal() {
        return InformeFinal;
    }

    public void setInformeFinal(String InformeFinal) {
        this.InformeFinal = InformeFinal;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
}
