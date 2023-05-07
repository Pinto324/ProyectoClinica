/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Objetos.Medicos.Horario;
import Servicios.Medicos.HorariosServicio;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.time.LocalTime;
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
@WebServlet(name = "ControladorHorario", urlPatterns = {"/ControladorHorario"})
public class ControladorHorario extends HttpServlet {
    private HorariosServicio servicio = new HorariosServicio();
    private Gson gson = new Gson();
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if (accion != null && accion.equals("obtenerHorarioId")) {
            int IdMedico = Integer.valueOf(request.getParameter("id"));
            List<Horario> horario = servicio.ListaDeHorarioId(IdMedico);
            List<String> conversion = new ArrayList<String>();
            for (Horario horarioExistente : horario) {
                String HoraEntrada = horarioExistente.getHoraEntrada().toString();
                String Horafinal = horarioExistente.getHoraSalida().toString();
                conversion.add(HoraEntrada);
                conversion.add(Horafinal);
            }
            String jsonEspecialidades = gson.toJson(conversion);
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
            boolean llave = true;
            LocalTime HoraInicial = LocalTime.parse(request.getParameter("HEntrada"));
            LocalTime HoraFinal = LocalTime.parse(request.getParameter("HSalida"));
            int idMedico = Integer.valueOf(request.getParameter("IdMedico"));
            List<Horario> horarios = servicio.ListaDeHorarioId(idMedico);
             for (Horario horarioExistente : horarios) {
                 LocalTime HoraInicialExistente = horarioExistente.getHoraEntrada();
                 LocalTime HoraFinalExistente = horarioExistente.getHoraSalida();
                 if (HoraInicial.isBefore(HoraFinalExistente) && HoraFinal.isAfter(HoraInicialExistente)) {
                    response.sendError(HttpServletResponse.SC_CONFLICT);
                    llave = false;
                }
             }
             if(llave){
                Horario Hn = new Horario(HoraInicial, HoraFinal);
                servicio.CrearHorario(Hn, idMedico);
                response.setStatus(HttpServletResponse.SC_CREATED);  
             }      
    }
@Override
protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }
}
