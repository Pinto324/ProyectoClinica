/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Objetos.Medicos.EspecialidadesMedicos;
import Objetos.ExamenesSolicitadosConsultas;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Servicios.ExamenesConsultasServicio;
import Servicios.Laboratorios.ExamenesServicio;
import Utilidades.ArchivoPDF;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 *
 * @author branp
 */
@WebServlet(name = "ControladorExamenesConsultas", urlPatterns = {"/ControladorExamenesConsultas"})
public class ControladorExamenesConsultas extends HttpServlet {
    private Gson gson = new Gson();
    private ExamenesConsultasServicio Servicio = new ExamenesConsultasServicio();
    private ExamenesServicio ServExamenes = new ExamenesServicio();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        int Id = Integer.valueOf(request.getParameter("id"));
        if (accion != null && accion.equals("obtenerExamenesAgregados")){
            String jsonEspecialidades = gson.toJson(Servicio.ExamenesConsulta(Id));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("DescargarExamenes")){
            String zipFileName = "Examenes.zip";
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + zipFileName + "\"");
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            List<ArchivoPDF> pdfFiles = Servicio.ListaDePdf(Id);
            for (ArchivoPDF pdfFile : pdfFiles) {
                String Nombre = pdfFile.getNombre();
                byte[] Cuerpo = pdfFile.getPdf();
                ZipEntry zipEntry = new ZipEntry(Nombre);
                zos.putNextEntry(zipEntry);
                zos.write(Cuerpo, 0, Cuerpo.length);
                zos.closeEntry();
            }
            zos.close();
            response.getOutputStream().close();
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
     // POST /
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        int IdExamen = ServExamenes.BuscarIdPorNombre(request.getParameter("NombreExamen"));
        int IdConsulta = Integer.valueOf(request.getParameter("IdConsulta"));
            if(IdExamen!=-1){
                ExamenesSolicitadosConsultas E = new ExamenesSolicitadosConsultas(IdConsulta, IdExamen, null);
                if(!(Servicio.EvitarRepetir(E))){
                    Servicio.CrearExamenSolicitado(E);
                    response.setStatus(HttpServletResponse.SC_CREATED);
                }else{ 
                    response.sendError(HttpServletResponse.SC_CONFLICT);
                }       
            }else{
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int IdExamen = ServExamenes.BuscarIdPorNombre(request.getParameter("Nombre"));
    int IdConsulta = Integer.valueOf(request.getParameter("IdConsulta"));
    ExamenesSolicitadosConsultas E = new ExamenesSolicitadosConsultas(IdConsulta, IdExamen, null);
    if (Servicio.EliminarSolicitud(E)) {
        response.setStatus(HttpServletResponse.SC_OK);
    } else {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
    @Override
protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
}
}
