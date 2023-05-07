/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Medicos;

import BaseDeDatos.Medicos.HorariosMedicosDB;
import Objetos.Medicos.Horario;
import java.sql.Array;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author branp
 */
public class HorariosServicio {
    HorariosMedicosDB DB = new HorariosMedicosDB();

    public HorariosServicio() {
    }
    
    public List<Horario> ListaDeHorarioId(int Id){return DB.HorariosPorId(Id);}
    public void CrearHorario(Horario HorarioNuevo, int Id){DB.CrearHorario(HorarioNuevo, Id);}
}
