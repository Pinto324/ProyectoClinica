/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Medicos;

import Servicios.ExamenesConsultasServicio;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author branp
 */
@MultipartConfig
@WebServlet(name = "ControladorExamenesDeConsultas", urlPatterns = {"/ControladorExamenesDeConsultas"})
public class ControladorExamenesDeConsultas extends HttpServlet {
    private final Gson gson = new Gson();
    private final ExamenesConsultasServicio Servicio = new ExamenesConsultasServicio();

            //GET:
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if(accion != null && accion.equals("obtenerExamenesConsulta")) {
            int IdSolicitud = Integer.valueOf(request.getParameter("IdConsulta"));
            String jsonEspecialidades = gson.toJson(Servicio.ListaDeExamenes(IdSolicitud));
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
        if (accion != null && accion.equals("subirExamenPdf")) {
            try{
            Part archivo = request.getPart("file");
            InputStream Is = archivo.getInputStream();
            byte[] bts = Is.readAllBytes();
            String nombre = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();
            int IdESL = Integer.valueOf(request.getParameter("IdESL"));
            if(Servicio.SubirPdf(IdESL, bts, nombre)){
                response.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                response.sendError(HttpServletResponse.SC_CONFLICT);
            }
            }catch(ServletException e){
                System.out.println(e);
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
