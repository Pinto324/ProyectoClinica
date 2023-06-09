package Controlador;

import Objetos.Medicos.EspecialidadesMedicos;
import Servicios.EspecialidadesServicio;
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
@WebServlet(name = "Especialidades", urlPatterns = {"/Especialidades"})
public class ControladorEspecialidades extends HttpServlet {
    private final EspecialidadesServicio ServicioEsp = new EspecialidadesServicio();
    private final Gson gson = new Gson();
    
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
        }else if (accion != null && accion.equals("obtenerDoctoresYEspecialidadesPacientes")) {
            String estado = request.getParameter("Estado");          
            String jsonEspecialidades = gson.toJson(ServicioEsp.ListaEspecialidadesYDoctores(estado));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }else if (accion != null && accion.equals("SolicitudesDeEspecialidad")) {       
            String jsonEspecialidades = gson.toJson(ServicioEsp.SolicitudesDeEspecialidad());
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
        if(accion != null && accion.equals("EnviarSolicitudNuevaEspe")){
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("Descripcion");
            if(ServicioEsp.NuevaEspecialidadPendiente(nombre,descripcion)){
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
        if (accion != null && accion.equals("ActualizarEstadoAdmin")) {
            int Id = Integer.valueOf(request.getParameter("IdEL"));
            if(ServicioEsp.ActualizarEstadoAdmin(Id)){
                response.setStatus(HttpServletResponse.SC_OK);
            }else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int IdExamen = Integer.valueOf(request.getParameter("IdEL"));
    if (ServicioEsp.EliminarEspecialidadNueva(IdExamen)) {
        response.setStatus(HttpServletResponse.SC_OK);
    } else {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    }
    @Override
protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");       
}
}