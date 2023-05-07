/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Laboratorios;

import Objetos.Laboratorios.ExamenesLaboratorios;
import Objetos.Medicos.EspecialidadesMedicos;
import Servicios.Laboratorios.ExamenesServicio;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author branp
 */
@WebServlet(name = "ControladorExamenesLaboratorios", urlPatterns = {"/ControladorExamenesLaboratorios"})
public class ControladorExamenesLaboratorios extends HttpServlet {
    private Gson gson = new Gson();
    private ExamenesServicio Servicio = new ExamenesServicio();
    //GET:
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if(accion != null && accion.equals("obtenerExamenesAsignados")) {
            int IdLab = Integer.valueOf(request.getParameter("IdLab"));
            String jsonEspecialidades = gson.toJson(Servicio.ListaDeExamenesPorId(IdLab));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }
    }
            // POST students/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if (accion != null && accion.equals("CrearSolicitud")) {
            int IdExamen = Integer.valueOf(request.getParameter("IdExamen"));
            double Precio = Double.valueOf(request.getParameter("Precio"));
            int idMedico = Integer.valueOf(request.getParameter("IdMedico"));
            ExamenesLaboratorios E = new ExamenesLaboratorios(idMedico, IdExamen, Precio,"Pendiente");
            if(!(Servicio.EvitarRepetirEspecialidad(E))){
                Servicio.CrearSolicitudDeExamen(E);
                    response.setStatus(HttpServletResponse.SC_CREATED);
                }else{ 
                    response.sendError(HttpServletResponse.SC_CONFLICT);
                }       
        }
    }
       // PUT /
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if (accion != null && accion.equals("ActualizarPrecio")) {
            int Id = Integer.valueOf(request.getParameter("IdExamen"));
            String precioNuevo = (request.getParameter("Precio"));
            if(Servicio.ModificarPrecioExamen(Id, Double.valueOf(precioNuevo))){
                response.setStatus(HttpServletResponse.SC_OK);
            }else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
    
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }
}
