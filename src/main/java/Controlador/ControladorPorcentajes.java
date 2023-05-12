/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Servicios.PorcentajeServicio;
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
@WebServlet(name = "ControladorPorcentajes", urlPatterns = {"/ControladorPorcentajes"})
public class ControladorPorcentajes extends HttpServlet {
    private final PorcentajeServicio servicio = new PorcentajeServicio();
    private final Gson gson = new Gson();
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if (accion != null && accion.equals("PorcentajeExamenes")) {
            String jsonEspecialidades = gson.toJson(servicio.PorcentajeExamenes());
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if (accion != null && accion.equals("PorcentajeEspecialidades")) {      
            String jsonEspecialidades = gson.toJson(servicio.PorcentajeEspecialidades());
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
        if (accion != null && accion.equals("NuevoPorcentajeExamen")) {
            int id = Integer.valueOf(request.getParameter("IdEM"));
            double Porcentaje = Double.valueOf(request.getParameter("Porcentaje")); 
            if(servicio.ModificarPorcentaje(id, Porcentaje)){
                response.setStatus(HttpServletResponse.SC_CREATED);
            }else{response.sendError(HttpServletResponse.SC_CONFLICT);}
        }else if (accion != null && accion.equals("NuevoPorcentajeEspecialidad")) {
            int id = Integer.valueOf(request.getParameter("IdEM"));
            double Porcentaje = Double.valueOf(request.getParameter("Porcentaje")); 
            if(servicio.ModificarPorcentajeEspecialidad(id, Porcentaje)){
                response.setStatus(HttpServletResponse.SC_CREATED);
            }else{response.sendError(HttpServletResponse.SC_CONFLICT);}
        }
    }
    
            @Override
protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");       
}
}
