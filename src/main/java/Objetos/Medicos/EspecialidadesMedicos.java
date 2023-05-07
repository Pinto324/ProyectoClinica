/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Medicos;

/**
 *
 * @author branp
 */
public class EspecialidadesMedicos {
    private int IdMedicoEM;
    private int IdEspecialidadEM;
    private double Precio;

    public EspecialidadesMedicos(int IdMedicoEM, int IdEspecialidadEM, double Precio) {
        this.IdMedicoEM = IdMedicoEM;
        this.IdEspecialidadEM = IdEspecialidadEM;
        this.Precio = Precio;
    }

    public int getIdMedicoEM() {
        return IdMedicoEM;
    }

    public void setIdMedicoEM(int IdMedicoEM) {
        this.IdMedicoEM = IdMedicoEM;
    }

    public int getIdEspecialidadEM() {
        return IdEspecialidadEM;
    }

    public void setIdEspecialidadEM(int IdEspecialidadEM) {
        this.IdEspecialidadEM = IdEspecialidadEM;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }
    
}
