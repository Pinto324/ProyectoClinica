/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Servicios.ConsultasServicio;
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
@WebServlet(name = "ControladorConsultas", urlPatterns = {"/ControladorConsultas"})
public class ControladorConsultas extends HttpServlet {
    private Gson gson = new Gson();
    private ConsultasServicio CS = new ConsultasServicio();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if (accion != null && accion.equals("obtenerConsultasPENDIENTEShoy")) {
            try{
                int id = Integer.valueOf(request.getParameter("IdMedico"));
                LocalDate today = LocalDate.now();    
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = today.format(formatter);
                String jsonEspecialidades = gson.toJson(CS.ListaDeConsultasPendientesIdMedicoFecha(id, formattedDate));
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(jsonEspecialidades);
                out.flush();
            }catch(ParseException e){response.sendError(HttpServletResponse.SC_BAD_REQUEST);}
        }else if(accion != null && accion.equals("obtenerConsultasREVISION")){
            try{
                int id = Integer.valueOf(request.getParameter("IdMedico"));
            String jsonEspecialidades = gson.toJson(CS.ListaDeConsultasRevisionIdMedico(id));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
            }catch(ParseException e){ response.sendError(HttpServletResponse.SC_BAD_REQUEST);}
        }else if(accion != null && accion.equals("obtenerConsultasPENDIENTESFecha")){
            try {
                int id = Integer.valueOf(request.getParameter("IdMedico"));
                String Fecha = request.getParameter("Fecha");
                String jsonEspecialidades = gson.toJson(CS.ListaDeConsultasPendientesIdMedicoFecha(id, Fecha));
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(jsonEspecialidades);
                out.flush();
            } catch (ParseException ex) {response.sendError(HttpServletResponse.SC_BAD_REQUEST);}
        }else if(accion != null && accion.equals("obtenerConsultasPorPaciente")){
            try {
                int IdPaciente = Integer.valueOf(request.getParameter("IdPaciente"));
                String jsonEspecialidades = gson.toJson(CS.ListaDeConsultasIdPaciente(IdPaciente));
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(jsonEspecialidades);
                out.flush();
            } catch (ParseException ex) {response.sendError(HttpServletResponse.SC_BAD_REQUEST);}
        }
    }
       // PUT /
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        int Id = Integer.valueOf(request.getParameter("IdConsulta"));
        if (accion != null && accion.equals("ActualizarConsulta")) {
            String Estado = (request.getParameter("Estado"));
            if(CS.ActualizarEstadoConsulta(Id, Estado)){
                response.setStatus(HttpServletResponse.SC_OK);
            }else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }else if(accion != null && accion.equals("FinalizarConsulta")){
            String Informe = request.getParameter("Informe");
            if(CS.FinalizarConsulta(Id, Informe)){
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
