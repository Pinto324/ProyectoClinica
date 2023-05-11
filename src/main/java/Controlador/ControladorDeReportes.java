/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Servicios.Reportes.GananciaGeneradaLabsServicio;
import Servicios.Reportes.GananciaGeneradaMedicosServicio;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author branp
 */
@WebServlet(name = "ControladorDeReportes", urlPatterns = {"/ControladorDeReportes"})
public class ControladorDeReportes extends HttpServlet {
    private final GananciaGeneradaMedicosServicio Servicio = new GananciaGeneradaMedicosServicio();
    private final GananciaGeneradaLabsServicio ServicioLabs = new GananciaGeneradaLabsServicio();
    private final Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
            String FechaInicio = request.getParameter("FInicio");
            String FechaFinal = request.getParameter("FechaFinal");
        if (accion != null && accion.equals("topPacientesMedicos")) {
            int Id = Integer.valueOf(request.getParameter("IdMedico"));
            String jsonEspecialidades = gson.toJson(Servicio.ReporteTop5Pacientes(Id,FechaInicio,FechaFinal));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("topEspecialidadesMedicos")){
            int Id = Integer.valueOf(request.getParameter("IdMedico"));
            String jsonEspecialidades = gson.toJson(Servicio.ReporteTop5Especialidades(Id,FechaInicio,FechaFinal));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("topExamenesLabs")){
            int Id = Integer.valueOf(request.getParameter("IdLab"));
            String jsonEspecialidades = gson.toJson(ServicioLabs.ReporteTop5Examenes(Id,FechaInicio,FechaFinal));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("topPacientesLabs")){
            int Id = Integer.valueOf(request.getParameter("IdLab"));
            String jsonEspecialidades = gson.toJson(ServicioLabs.ReporteTop5Pacientes(Id,FechaInicio,FechaFinal));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("HistorialDePaciente")){
            int Id = Integer.valueOf(request.getParameter("IdPaciente"));
            List<String> Datos = new ArrayList<>();
            Datos.addAll(Servicio.HistorialDePacienteConsulta(Id, FechaInicio, FechaFinal));
            Datos.addAll(ServicioLabs.HistorialPacienteSolicitud(Id, FechaInicio, FechaFinal));
            String jsonEspecialidades = gson.toJson(Datos);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("HistorialDeRecargas")){
            int Id = Integer.valueOf(request.getParameter("IdPaciente"));
            String jsonEspecialidades = gson.toJson(Servicio.HistorialDeRecargasPaciente(Id));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("ConsultasPorEspecialidad")){
            int Id = Integer.valueOf(request.getParameter("IdPaciente"));
            int IdEspe = Integer.valueOf(request.getParameter("Espe"));
            String jsonEspecialidades = gson.toJson(Servicio.ConsultasPorEspecialidad(Id,IdEspe,FechaInicio,FechaFinal));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("SolicitudesPorExamenes")){
            int Id = Integer.valueOf(request.getParameter("IdPaciente"));
            int IdEspe = Integer.valueOf(request.getParameter("examen"));
            String jsonEspecialidades = gson.toJson(Servicio.ExamenPorTipo(Id,IdEspe,FechaInicio,FechaFinal));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("Top5Medicos")){
            String jsonEspecialidades = gson.toJson(Servicio.ReporteTop5Medicos());
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("Top5Labs")){
            String jsonEspecialidades = gson.toJson(Servicio.ReporteTop5Labs());
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("HistorialPorcentajes")){
              List<String> Datos = new ArrayList<>();
            Datos.addAll(Servicio.HistorialPorcentajeEspe());
            Datos.addAll(Servicio.HistorialPorcentajeExamenes());
            String jsonEspecialidades = gson.toJson(Datos);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("TotalIngresos")){
              List<String> Datos = new ArrayList<>();
            Datos.addAll(Servicio.TotalDeIngresosConsulta(FechaInicio, FechaFinal));
            Datos.addAll(Servicio.TotalDeIngresosExamen(FechaInicio, FechaFinal));
            String jsonEspecialidades = gson.toJson(Datos);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }          
    }
        @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
}

}
