package Controlador;

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
            String jsonEspecialidades = gson.toJson(ServicioEsp.ListaEspecialidadesYDoctores());
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonEspecialidades);
            out.flush();
        }
    }

}