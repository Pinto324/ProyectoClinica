/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;


import BaseDeDatos.ExamenesConsultas;
import Objetos.ExamenesSolicitadosConsultas;
import Utilidades.ArchivoPDF;
import java.util.List;

/**
 *
 * @author branp
 */
public class ExamenesConsultasServicio {
    private ExamenesConsultas DB = new ExamenesConsultas();

    public ExamenesConsultasServicio() {
    }
    public List<String> ExamenesConsulta(int id){return DB.ListaDeExamenesPorID(id);}
    public boolean EvitarRepetir(ExamenesSolicitadosConsultas e){return DB.EvitarRepetirEspecialidad(e);}
    public boolean EliminarSolicitud(ExamenesSolicitadosConsultas e){return DB.EliminarUsuario(e);}
    public void CrearExamenSolicitado(ExamenesSolicitadosConsultas e){DB.CrearExamenSolicitudConsulta(e);}
    public List<ArchivoPDF> ListaDePdf(int id){return DB.ListaDePdfs(id);}
}
