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
public class PorcentajeDeCobroAppExamenes {
    private int Id;
    private Integer idPorcentajeDeCobroAPPExamenes;
    private int idDelExamenPCE;
    private double porcentajePCE;
    private Date fechaDeInicioPCE;
    private Date fechaFinalPCE;
    private boolean activaPCE;

    public PorcentajeDeCobroAppExamenes(int Id, Integer idPorcentajeDeCobroAPPExamenes, int idDelExamenPCE, double porcentajePCE, Date fechaDeInicioPCE, Date fechaFinalPCE, boolean activaPCE) {
        this.Id = Id;
        this.idPorcentajeDeCobroAPPExamenes = idPorcentajeDeCobroAPPExamenes;
        this.idDelExamenPCE = idDelExamenPCE;
        this.porcentajePCE = porcentajePCE;
        this.fechaDeInicioPCE = fechaDeInicioPCE;
        this.fechaFinalPCE = fechaFinalPCE;
        this.activaPCE = activaPCE;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Integer getIdPorcentajeDeCobroAPPExamenes() {
        return idPorcentajeDeCobroAPPExamenes;
    }

    public void setIdPorcentajeDeCobroAPPExamenes(Integer idPorcentajeDeCobroAPPExamenes) {
        this.idPorcentajeDeCobroAPPExamenes = idPorcentajeDeCobroAPPExamenes;
    }

    public int getIdDelExamenPCE() {
        return idDelExamenPCE;
    }

    public void setIdDelExamenPCE(int idDelExamenPCE) {
        this.idDelExamenPCE = idDelExamenPCE;
    }

    public double getPorcentajePCE() {
        return porcentajePCE;
    }

    public void setPorcentajePCE(double porcentajePCE) {
        this.porcentajePCE = porcentajePCE;
    }

    public Date getFechaDeInicioPCE() {
        return fechaDeInicioPCE;
    }

    public void setFechaDeInicioPCE(Date fechaDeInicioPCE) {
        this.fechaDeInicioPCE = fechaDeInicioPCE;
    }

    public Date getFechaFinalPCE() {
        return fechaFinalPCE;
    }

    public void setFechaFinalPCE(Date fechaFinalPCE) {
        this.fechaFinalPCE = fechaFinalPCE;
    }

    public boolean isActivaPCE() {
        return activaPCE;
    }

    public void setActivaPCE(boolean activaPCE) {
        this.activaPCE = activaPCE;
    }
    
}
