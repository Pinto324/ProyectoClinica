/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Laboratorios;

import BaseDeDatos.Laboratorios.ExamenesSolicitudesDB;
import Utilidades.ArchivoPDF;
import java.util.List;

/**
 *
 * @author branp
 */
public class ExamenesDeUnaSolicitudServicio {
    private final ExamenesSolicitudesDB DB = new ExamenesSolicitudesDB();

    public ExamenesDeUnaSolicitudServicio() {
    }
    public List<String> ExamenesEnUnaSolicitud(int IdSoli){return DB.ListaDeExamenesPorIDSolicitud(IdSoli);}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
    public boolean subirExamenPDF(int Id,byte[] bts ,String NombreArchivo){return DB.SubirArchivoPDF(Id, bts, NombreArchivo);}
    public boolean pagarExamenesDeSolicitud(int Id){return DB.PagarExamenesDeSolicitud(Id);}
    public void CrearExamenSolicitud(int IdSolicitud, int IdExamen){DB.CrearExamenSolicitudConsulta(IdSolicitud, IdExamen);}
    public boolean EvitarRepetirExamen(int IdSolicitud, int IdExamen){return DB.EvitarRepetirExamen(IdSolicitud, IdExamen);}
    public List<ArchivoPDF> ListaDePdf(int id){return DB.ListaDePdfs(id);}
}
