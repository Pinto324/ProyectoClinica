/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Laboratorios;

import Servicios.Laboratorios.ExamenesDeUnaSolicitudServicio;
import Servicios.UsuarioServicio;
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
@WebServlet(name = "ControladorExamenesSolicitudes", urlPatterns = {"/ControladorExamenesSolicitudes"})
public class ControladorExamenesSolicitudes extends HttpServlet {
    private final ExamenesDeUnaSolicitudServicio servicio = new ExamenesDeUnaSolicitudServicio();
    private final UsuarioServicio Pagos = new UsuarioServicio();
    private final Gson gson = new Gson();
    
        //GET:
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if(accion != null && accion.equals("obtenerExamenesSolicitud")) {
            int IdSolicitud = Integer.valueOf(request.getParameter("IdSolicitud"));
            String jsonEspecialidades = gson.toJson(servicio.ExamenesEnUnaSolicitud(IdSolicitud));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }
    }
    
    ///////*  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if(accion != null && accion.equals("subirExamenPdf")){
            Part archivo = request.getPart("file");
            InputStream Is = archivo.getInputStream();
            byte[] bts = Is.readAllBytes();
            String nombre = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();
            int IdESL = Integer.valueOf(request.getParameter("IdESL"));
            if(servicio.subirExamenPDF(IdESL, bts, nombre)){
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
        if (accion != null && accion.equals("subirExamenPdf")) {
            try{
            Part archivo = request.getPart("file");
            InputStream Is = archivo.getInputStream();
            byte[] bts = Is.readAllBytes();
            String nombre = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();
            int IdESL = Integer.valueOf(request.getParameter("IdESL"));
            if(servicio.subirExamenPDF(IdESL, bts, nombre)){
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
