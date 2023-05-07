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
        }        
    }
        @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
}

}
