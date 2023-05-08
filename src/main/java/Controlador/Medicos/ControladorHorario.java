/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Medicos;

import Objetos.Medicos.Horario;
import Servicios.ConsultasServicio;
import Servicios.Medicos.HorariosServicio;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        String jsonEspecialidades = "";
        if (accion != null && accion.equals("obtenerHorarioId")) {
            int IdMedico = Integer.valueOf(request.getParameter("id"));
            List<Horario> horario = servicio.ListaDeHorarioId(IdMedico);
            List<String> conversion = new ArrayList<String>();
            horario.forEach(horarioExistente -> {
                String HoraEntrada = horarioExistente.getHoraEntrada().toString();
                String Horafinal = horarioExistente.getHoraSalida().toString();
                conversion.add(HoraEntrada);
                conversion.add(Horafinal);
            });
            jsonEspecialidades = gson.toJson(conversion);
        }else if(accion != null && accion.equals("obtenerHorarioDisponibleId")){
            int Id = Integer.valueOf(request.getParameter("IdMedico"));
            String Fecha = request.getParameter("Fecha");        
            List<Horario> horario = servicio.ListaDeHorarioId(Id);
            List<String> horasIntermedias = new ArrayList<>();   
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("H:mm");  
            for (Horario horarioExistente : horario) {
                Duration duracion = Duration.between(horarioExistente.getHoraEntrada(), horarioExistente.getHoraSalida());
                long cantidadHoras = duracion.toHours(); 
                for (int i = 0; i < cantidadHoras; i++) { // Modificamos el límite inferior a 0
                    LocalTime horaIntermedia = horarioExistente.getHoraEntrada().plusHours(i);
                    String horaIntermediaStr = horaIntermedia.format(formatoHora);
                    horasIntermedias.add(horaIntermediaStr);
                }
            }
            ConsultasServicio CS = new ConsultasServicio();           
            jsonEspecialidades = gson.toJson(CS.FiltrarHoraOcupadaConsulta(horasIntermedias, Fecha, Id));
        }       
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
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
