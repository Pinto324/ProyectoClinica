/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Reportes;

import BaseDeDatos.Reportes.GananciaGeneradaMedicosDB;
import java.util.List;

/**
 *
 * @author branp
 */
public class GananciaGeneradaMedicosServicio {
    private GananciaGeneradaMedicosDB DB = new GananciaGeneradaMedicosDB();

    public GananciaGeneradaMedicosServicio() {
    }
    
    public void IngresarGanancia(int IdConsulta, double GananciaApp, double GananciaMedico){DB.SubirDatosParaReporte(IdConsulta, GananciaApp, GananciaMedico);}
    public List<String> ReporteTop5Pacientes(int IdMedico, String FI, String FF){return DB.ReporteTop5PacienteMedico(IdMedico,FI,FF);}
    public List <String> ReporteTop5Especialidades(int IdMedico, String FI, String FF){return DB.ReporteTop5EspecialidadesMedico(IdMedico,FI,FF);}
     public List <String> HistorialDePacienteConsulta(int IdPaciente, String FI, String FF){return DB.HistorialPacienteConsulta(IdPaciente,FI,FF);}
    public List<String> HistorialDeRecargasPaciente(int IdPaciente){return DB.HistorialPacienteRecargas(IdPaciente);}
    public List<String> ConsultasPorEspecialidad(int IdPaciente, int IdEspe,String FI, String FF){return DB.HistorialConsultasEspecialidad(IdPaciente, IdEspe,FI,FF);}
    public List<String> ExamenPorTipo(int IdPaciente, int IdEspe,String FI, String FF){return DB.HistorialExamenesPorTipo(IdPaciente, IdEspe,FI,FF);}

}
