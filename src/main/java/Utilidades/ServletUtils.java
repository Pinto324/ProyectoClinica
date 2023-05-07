/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Servicios.UsuarioServicio;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author branp
 */
public class ServletUtils {
    private UsuarioServicio User = new UsuarioServicio();
    public ServletUtils() {
    }
    
    public int confirmarSiExisteUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        if(User.ComprobarSiExisteUsuario(Integer.parseInt(UsuarioId))) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return -1;
        }
        return Integer.parseInt(UsuarioId);
    }
}
