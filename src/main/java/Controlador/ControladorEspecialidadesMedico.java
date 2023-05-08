/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Objetos.Medicos.EspecialidadesMedicos;
import Servicios.EspecialidadesServicio;
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
@WebServlet(name = "EspecialidadesMedico", urlPatterns = {"/EspecialidadesMedico"})
public class ControladorEspecialidadesMedico extends HttpServlet {
    private EspecialidadesServicio ServicioEsp = new EspecialidadesServicio();
    private Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String accion = request.getParameter("accion");
        if (accion != null && accion.equals("obtenerEspecialidades")) {
            String jsonEspecialidades = gson.toJson(ServicioEsp.ListaEspecialidades());
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if(accion != null && accion.equals("obtenerEspecialidadesAgregadas")){
            int Id = Integer.valueOf(request.getParameter("IdMedico"));
            String estado = request.getParameter("Estado");          
            String jsonEspecialidades = gson.toJson(ServicioEsp.ListaEspecialidadesPorIdyEstado(Id,estado));
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
        if (accion != null && accion.equals("ActualizarPrecio")) {
            int Id = Integer.valueOf(request.getParameter("IdEM"));
            String precioNuevo = (request.getParameter("Precio"));
            if(ServicioEsp.ActualizarPrecioPorId(Id, Double.valueOf(precioNuevo))){
                response.setStatus(HttpServletResponse.SC_OK);
            }else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
        // POST students/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String NombreEspecialidad = (request.getParameter("Nombre"));
        double Precio = Double.valueOf(request.getParameter("Precio"));
        int idMedico = Integer.valueOf(request.getParameter("IdMedico"));
        int IdEspecialidad = ServicioEsp.BuscarIdEspecialidadPorNombre(NombreEspecialidad);
            if(IdEspecialidad!=-1){
                EspecialidadesMedicos E = new EspecialidadesMedicos(idMedico, IdEspecialidad, Precio);
                if(!(ServicioEsp.EvitarRepetirEspecialidad(E))){
                    ServicioEsp.CrearEspecialidadMedico(E);
                    response.setStatus(HttpServletResponse.SC_CREATED);
                }else{ 
                    response.sendError(HttpServletResponse.SC_CONFLICT);
                }       
            }else{
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
    }
    @Override
protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
}
}
