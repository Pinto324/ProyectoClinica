/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Medicos;

import BaseDeDatos.Medicos.PorcentajeConsultasDB;

/**
 *
 * @author branp
 */
public class PorcentajeConsultasServicio {
    PorcentajeConsultasDB DB = new PorcentajeConsultasDB();

    public PorcentajeConsultasServicio() {
    }
    
    public double BuscarPorcentajeComision(int idEspecialidad){return DB.BuscarPorcentajeComision(idEspecialidad);}
}
