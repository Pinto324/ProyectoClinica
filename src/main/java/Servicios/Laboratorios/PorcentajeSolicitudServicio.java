/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Laboratorios;

import BaseDeDatos.Laboratorios.PorcentajeExamenesDB;

/**
 *
 * @author branp
 */
public class PorcentajeSolicitudServicio {
    private final PorcentajeExamenesDB DB = new PorcentajeExamenesDB();

    public PorcentajeSolicitudServicio() {
    }
    public double buscarPorcentajeDeExamen(int IdExamen){return DB.BuscarPorcentajeComision(IdExamen);}
}
