/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Laboratorios;

import java.sql.Date;

/**
 *
 * @author branp
 */
public class SolicitudDeLaboratorio {
    private int IdSolicitud;
    private int idDelPacienteSL;
    private int idDelLaboratorioSL;
    private double porcentajeAppSL;
    private Date fechaSolicitadoSL;
    private Date fechaFinalizadoSL;
    private String estadoSL;

    public SolicitudDeLaboratorio(int IdSolicitud, int idDelPacienteSL, int idDelLaboratorioSL, double porcentajeAppSL, Date fechaSolicitadoSL, Date fechaFinalizadoSL, String estadoSL) {
        this.IdSolicitud = IdSolicitud;
        this.idDelPacienteSL = idDelPacienteSL;
        this.idDelLaboratorioSL = idDelLaboratorioSL;
        this.porcentajeAppSL = porcentajeAppSL;
        this.fechaSolicitadoSL = fechaSolicitadoSL;
        this.fechaFinalizadoSL = fechaFinalizadoSL;
        this.estadoSL = estadoSL;
    }

    public int getIdSolicitud() {
        return IdSolicitud;
    }

    public void setIdSolicitud(int IdSolicitud) {
        this.IdSolicitud = IdSolicitud;
    }



    public int getIdDelPacienteSL() {
        return idDelPacienteSL;
    }

    public void setIdDelPacienteSL(int idDelPacienteSL) {
        this.idDelPacienteSL = idDelPacienteSL;
    }

    public int getIdDelLaboratorioSL() {
        return idDelLaboratorioSL;
    }

    public void setIdDelLaboratorioSL(int idDelLaboratorioSL) {
        this.idDelLaboratorioSL = idDelLaboratorioSL;
    }

    public double getPorcentajeAppSL() {
        return porcentajeAppSL;
    }

    public void setPorcentajeAppSL(double porcentajeAppSL) {
        this.porcentajeAppSL = porcentajeAppSL;
    }

    public Date getFechaSolicitadoSL() {
        return fechaSolicitadoSL;
    }

    public void setFechaSolicitadoSL(Date fechaSolicitadoSL) {
        this.fechaSolicitadoSL = fechaSolicitadoSL;
    }

    public Date getFechaFinalizadoSL() {
        return fechaFinalizadoSL;
    }

    public void setFechaFinalizadoSL(Date fechaFinalizadoSL) {
        this.fechaFinalizadoSL = fechaFinalizadoSL;
    }

    public String getEstadoSL() {
        return estadoSL;
    }

    public void setEstadoSL(String estadoSL) {
        this.estadoSL = estadoSL;
    }
    
    
    
}
