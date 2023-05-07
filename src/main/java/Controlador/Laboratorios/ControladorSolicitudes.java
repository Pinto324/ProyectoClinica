/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Laboratorios;

import Servicios.Laboratorios.ExamenesDeUnaSolicitudServicio;
import Servicios.Laboratorios.SolicitudesServicio;
import Servicios.UsuarioServicio;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author branp
 */
@WebServlet(name = "ControladorSolicitudes", urlPatterns = {"/ControladorSolicitudes"})
public class ControladorSolicitudes extends HttpServlet {
    private final SolicitudesServicio Servicio = new SolicitudesServicio();
    private final Gson gson = new Gson();
    
        //GET:
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if(accion != null && accion.equals("obtenerSolicitudes")) {
            int IdLab = Integer.valueOf(request.getParameter("IdLab"));
            String jsonEspecialidades = gson.toJson(Servicio.SolicitudesLabPorId(IdLab));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }
    }
               // PUT /
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if(accion != null && accion.equals("FinalizarSolicitud")){
            int IdESL = Integer.valueOf(request.getParameter("IdSolicitud"));
            if(Servicio.finalizarSolicitud(IdESL)){
                ExamenesDeUnaSolicitudServicio Pago = new ExamenesDeUnaSolicitudServicio();
                Pago.pagarExamenesDeSolicitud(IdESL);
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
