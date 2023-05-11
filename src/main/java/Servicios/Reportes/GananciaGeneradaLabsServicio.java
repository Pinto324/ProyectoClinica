/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Reportes;

import BaseDeDatos.Reportes.GananciaGeneradaLabsDB;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author branp
 */
public class GananciaGeneradaLabsServicio {
    private final GananciaGeneradaLabsDB DB = new GananciaGeneradaLabsDB();

    public GananciaGeneradaLabsServicio() {
    }
    //metodo que subeGanancia por lab
    public void IngresarGanancia(int IdConsulta, double GananciaLab, double GananciaApp, int IdExamen){DB.SubirDatosParaReporte(IdConsulta, GananciaLab, GananciaApp, IdExamen);}
    public void SubirDatoCargaDeDatos(int IdConsulta, double GananciaLab, double GananciaApp, int IdExamen, Date Fecha){DB.SubirDatoCargaDeDatos(IdConsulta, GananciaLab, GananciaApp, IdExamen, Fecha);}
    public List<String> ReporteTop5Pacientes(int IdMedico, String FI, String FF){return DB.ReporteTop5PacienteLab(IdMedico,FI,FF);}
    public List <String> ReporteTop5Examenes(int IdMedico, String FI, String FF){return DB.ReporteTop5ExamenesLab(IdMedico,FI,FF);}
    public List<String> HistorialPacienteSolicitud(int IdPaciente, String FI, String FF){return DB.HistorialPacienteSolicitud(IdPaciente, FI, FF);}
}
