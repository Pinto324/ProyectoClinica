/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Objetos.Usuario;
import Servicios.UsuarioServicio;
import Utilidades.GsonUtils;
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
@WebServlet(name = "Usuario", urlPatterns = {"/Usuario/*"})
public class ControladorUsuario extends HttpServlet {
    private final GsonUtils<Usuario> gsonUsuario;
    private final UsuarioServicio usuarioServicio;
    private final Gson gson = new Gson();
    
    public ControladorUsuario() {
        this.gsonUsuario = new GsonUtils<>();
        this.usuarioServicio = new UsuarioServicio();
    }
    

    // POST students/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            var user = gsonUsuario.readFromJson((jakarta.servlet.http.HttpServletRequest) request, Usuario.class);
            usuarioServicio.CrearUsuario(user);
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonUsuario.sendAsJson((jakarta.servlet.http.HttpServletResponse) response, user);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if (accion != null && accion.equals("obtenerPacientes")) {
            String jsonEspecialidades = gson.toJson(usuarioServicio.obtenerPacientes());
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("obtenerSaldo")) {
            int id = Integer.valueOf(request.getParameter("IdMedico"));
            String jsonEspecialidades = gson.toJson(usuarioServicio.ObtenerSaldo(id));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }
    }
    // Updates a student in DB
    // PUT students/1
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        //accion para ingresar dinero a la cuenta del doc y sistema
        if (accion != null && accion.equals("DebitarConsulta")) {
            int Id = Integer.valueOf(request.getParameter("IdMedico"));
            double precio = Double.valueOf(request.getParameter("precio"));
            double porcentaje = Double.valueOf(request.getParameter("Porcentaje"));
            int IdConsulta = Integer.valueOf(request.getParameter("IdConsulta"));
            if(usuarioServicio.PagarAlDoyAdmin(Id,precio,porcentaje,IdConsulta)){
                response.setStatus(HttpServletResponse.SC_OK);
            }else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
    private int processPath(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        String httpMethod = request.getMethod();

        if(httpMethod.equals("PUT") || httpMethod.equals("DELETE")) {
            if(pathInfo == null || pathInfo.equals("/")){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return -1;
            }
        }
        String[] splits = pathInfo.split("/");
        if(splits.length != 2) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return -1;
        }

        String UsuarioId = splits[1];

        try {
            Integer.parseInt(UsuarioId);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return -1;
        }

        if(usuarioServicio.ComprobarSiExisteUsuario(Integer.parseInt(UsuarioId))) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return -1;
        }

        return Integer.parseInt(UsuarioId);
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
}
}
