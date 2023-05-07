/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Laboratorios;

import BaseDeDatos.SolicitudDeLaboratorioDB;
import java.util.List;

/**
 *
 * @author branp
 */
public class SolicitudesServicio {
    private final SolicitudDeLaboratorioDB DB = new SolicitudDeLaboratorioDB();

    public SolicitudesServicio() {
    }
    public List<String> SolicitudesLabPorId(int id){return DB.ListaSolicitudesLab(id);}
    public boolean finalizarSolicitud(int Id){return DB.FinalizarSolicitud(Id);}
}
