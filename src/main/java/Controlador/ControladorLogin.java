/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Objetos.Usuario;
import Servicios.UsuarioServicio;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author branp
 */
@WebServlet(name = "ControladorLogin", urlPatterns = {"/ControladorLogin"})
public class ControladorLogin extends HttpServlet {
    private final UsuarioServicio usuarioServicio = new UsuarioServicio();

    public ControladorLogin() {
        
    }
        // post students/2
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html;charset=UTF-8");
        boolean valid = false;
        Usuario user = null;
        if(usuarioServicio.BuscarUsuarioPorUsername(username)!=null){
            user = usuarioServicio.BuscarUsuarioPorUsername(username);
            valid = user.getPassword().equals(password);
        }
        // Configurar la respuesta HTTP para enviar la respuesta JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Crear objeto de respuesta JSON con la información del usuario autenticado
        JsonObject responseData = new JsonObject();
        responseData.addProperty("valid", valid);
        if (valid) {      
            responseData.addProperty("username", username);
            responseData.addProperty("id", user.getCodigo());
            responseData.addProperty("tipo", user.getTipo());
        }
    // Escribir respuesta JSON en la respuesta HTTP
        response.getWriter().write(responseData.toString());
    }    
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
}
}
