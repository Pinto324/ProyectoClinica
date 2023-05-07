/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.sql.Date;

/**
 *
 * @author branp
 */
public class PorcentajeDeCobroAppConsultas {
    private int Id;
    private Integer idPorcentajeAPPConsultas;
    private int idDeEspecialidadPorcentaje;
    private double porcentaje;
    private Date fechaDeInicio;
    private Date fechaFinal;
    private boolean activa;

    public PorcentajeDeCobroAppConsultas(int Id, Integer idPorcentajeAPPConsultas, int idDeEspecialidadPorcentaje, double porcentaje, Date fechaDeInicio, Date fechaFinal, boolean activa) {
        this.Id = Id;
        this.idPorcentajeAPPConsultas = idPorcentajeAPPConsultas;
        this.idDeEspecialidadPorcentaje = idDeEspecialidadPorcentaje;
        this.porcentaje = porcentaje;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaFinal = fechaFinal;
        this.activa = activa;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Integer getIdPorcentajeAPPConsultas() {
        return idPorcentajeAPPConsultas;
    }

    public void setIdPorcentajeAPPConsultas(Integer idPorcentajeAPPConsultas) {
        this.idPorcentajeAPPConsultas = idPorcentajeAPPConsultas;
    }

    public int getIdDeEspecialidadPorcentaje() {
        return idDeEspecialidadPorcentaje;
    }

    public void setIdDeEspecialidadPorcentaje(int idDeEspecialidadPorcentaje) {
        this.idDeEspecialidadPorcentaje = idDeEspecialidadPorcentaje;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(Date fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
}
