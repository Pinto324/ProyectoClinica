/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Reportes;

import BaseDeDatos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author branp
 */
public class GananciaGeneradaMedicosDB {
        private Conexion Con;
    private Connection Conn;

    public GananciaGeneradaMedicosDB() {
    }
    public void SubirDatosParaReporte(int IdConsulta, double CantidadGeneradaAppGGM, double CantidadGeneradaMedicoGGM){
    Con = new Conexion();
        Con.IniciarConexion();
        LocalDate fechaActual = LocalDate.now();
        Date fechaSQL = Date.valueOf(fechaActual);
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into gananciageneradamedicos (IdConsultaGGM, CantidadGeneradaAppGGM, CantidadGeneradaMedicoGGM, FechaDeMov) values (?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, IdConsulta);
            ps.setDouble(2, CantidadGeneradaAppGGM);
            ps.setDouble(3, CantidadGeneradaMedicoGGM);
            ps.setDate(4, fechaSQL);
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    ///metodo para subir reportes de carga:
    public void SubirDatosCarga(int IdConsulta, double CantidadGeneradaAppGGM, double CantidadGeneradaMedicoGGM, Date Fecha){
    Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into gananciageneradamedicos (IdConsultaGGM, CantidadGeneradaAppGGM, CantidadGeneradaMedicoGGM, FechaDeMov) values (?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, IdConsulta);
            ps.setDouble(2, CantidadGeneradaAppGGM);
            ps.setDouble(3, CantidadGeneradaMedicoGGM);
            ps.setDate(4, Fecha);
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public List<String> ReporteTop5PacienteMedico(int id,String FechaInicio, String FechaFinal){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT u.NombreUsuario, COUNT(*) AS total_consultas, SUM(g.CantidadGeneradaMedicoGGM) AS total_ganancia FROM usuariosmedic u INNER JOIN consultas c ON u.IdUsuario = c.IdDelPacienteConsultas INNER JOIN gananciageneradamedicos g ON c.IdConsultas = g.IdConsultaGGM WHERE c.IdDelMedicoConsultas = '"+id+"' AND g.FechaDeMov BETWEEN '"+FechaInicio+"' AND '"+FechaFinal+"'GROUP BY u.NombreUsuario ORDER BY total_ganancia desc;");
            while(U.next()){  
                String dato;
                dato = U.getString(1);
                Info.add(dato);
                dato = String.valueOf(U.getInt(2));
                Info.add(dato);
                dato = String.valueOf(U.getDouble(3));
                Info.add(dato); 
            }
                U.close();
                Con.CerrarConexiones();
                return Info;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
        public List<String> ReporteTop5EspecialidadesMedico(int id,String FechaInicio, String FechaFinal){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT e.NombreEspecialidad, COUNT(*) AS total_consultas, SUM(g.CantidadGeneradaMedicoGGM) AS total_ganancia FROM especialidades e INNER JOIN consultas c ON e.IdEspecialidades = c.IdDeEspecialidadDelMedico INNER JOIN gananciageneradamedicos g ON c.IdConsultas = g.IdConsultaGGM WHERE c.IdDelMedicoConsultas = '"+id+"' AND g.FechaDeMov BETWEEN '"+FechaInicio+"' AND '"+FechaFinal+"' GROUP BY e.NombreEspecialidad ORDER BY total_ganancia desc;");
            while(U.next()){  
                String dato;
                dato = U.getString(1);
                Info.add(dato);
                dato = String.valueOf(U.getInt(2));
                Info.add(dato);
                dato = String.valueOf(U.getDouble(3));
                Info.add(dato); 
            }
                U.close();
                Con.CerrarConexiones();
                return Info;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
        //conseguir el historial de consultas de un paciente en una fecha:
    public List<String> HistorialPacienteConsulta(int id,String FechaInicio, String FechaFinal){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM Consultas INNER JOIN Especialidades ON Especialidades.IdEspecialidades = IdDeEspecialidadDelMedico INNER JOIN UsuariosMedic ON UsuariosMedic.IdUsuario = IdDelMedicoConsultas WHERE EstadoConsulta='FINALIZADA' AND IdDelPacienteConsultas ='"+id+"'AND FechaAgendada BETWEEN '"+FechaInicio+"' AND '"+FechaFinal+"'ORDER BY FechaAgendada desc;");
            while(U.next()){  
                String dato;
                dato = U.getString(U.findColumn("NombreUsuario"));
                Info.add(dato);
                dato = U.getString(U.findColumn("NombreEspecialidad"));
                Info.add(dato);
                dato = String.valueOf(U.getTimestamp(U.findColumn("FechaAgendada"))).substring(0, 10);
                Info.add(dato);
                dato = U.getString(U.findColumn("Telefono"));
                Info.add(dato);
                dato = U.getString(U.findColumn("Email"));
                Info.add(dato);
                dato = U.getString(U.findColumn("InformeFinal"));
                Info.add(dato);
                dato = "Consulta";
                Info.add(dato);
            }
                U.close();
                Con.CerrarConexiones();
                return Info;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
            //conseguir el HistorialPacienteRecargas:
    public List<String> HistorialPacienteRecargas(int id){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM recargaspacientes WHERE IdPaciente ='"+id+"'ORDER BY HoraFecha desc;");
            while(U.next()){  
                String dato;
                dato = U.getString(U.findColumn("Monto"));
                Info.add(dato);
                dato = String.valueOf(U.getTimestamp(U.findColumn("HoraFecha")));
                Info.add(dato);
            }
                U.close();
                Con.CerrarConexiones();
                return Info;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
                //conseguir el consultas por especialidad:
    public List<String> HistorialConsultasEspecialidad(int id, int IdEspe, String FI, String FF){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM consultas INNER JOIN Especialidades ON Especialidades.IdEspecialidades = IdDeEspecialidadDelMedico INNER JOIN UsuariosMedic ON UsuariosMedic.IdUsuario = IdDelMedicoConsultas WHERE IdDelPacienteConsultas ='"+id+"'AND EstadoConsulta='FINALIZADA' AND IdDeEspecialidadDelMedico='"+IdEspe +"'AND FechaAgendada BETWEEN '"+FI+"' AND '"+FF+"'ORDER BY FechaAgendada desc;");
            while(U.next()){  
                String dato;
                dato = U.getString(U.findColumn("NombreUsuario"));
                Info.add(dato);
                dato = U.getString(U.findColumn("NombreEspecialidad"));
                Info.add(dato);
                dato = String.valueOf(U.getTimestamp(U.findColumn("FechaAgendada"))).substring(0, 10);
                Info.add(dato);
                dato = U.getString(U.findColumn("Telefono"));
                Info.add(dato);
                dato = U.getString(U.findColumn("Email"));
                Info.add(dato);
                dato = U.getString(U.findColumn("InformeFinal"));
                Info.add(dato);
            }
                U.close();
                Con.CerrarConexiones();
                return Info;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    //conseguir hitorial examenes
    public List<String> HistorialExamenesPorTipo(int id, int IdEspe, String FI, String FF){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM solicitudlaboratorio INNER JOIN examenessolicitadosenlaboratorio ON examenessolicitadosenlaboratorio.IdSolicitudLaboratorioESL = IdSolicitudLaboratorioESL INNER JOIN tipodeexamenes ON tipodeexamenes.IdTipoDeExamenes = examenessolicitadosenlaboratorio.IdExamenESL INNER JOIN UsuariosMedic ON UsuariosMedic.IdUsuario = IdDelLaboratorioSL WHERE IdDelPacienteSL ='"+id+"'AND EstadoSL='FINALIZADA' AND IdExamenESL='"+IdEspe +"'AND FechaFinalizadoSL BETWEEN '"+FI+"' AND '"+FF+"'ORDER BY FechaFinalizadoSL desc;");
            while(U.next()){  
                String dato;
                dato = U.getString(U.findColumn("NombreUsuario"));
                Info.add(dato);
                dato = U.getString(U.findColumn("NombreExamen"));
                Info.add(dato);
                dato = String.valueOf(U.getDate(U.findColumn("FechaFinalizadoSL")));
                Info.add(dato);
                dato = U.getString(U.findColumn("Telefono"));
                Info.add(dato);
                dato = U.getString(U.findColumn("Email"));
                Info.add(dato);
                dato = U.getString(U.findColumn("NombreDeArchivoESL"));
                Info.add(dato);
            }
                U.close();
                Con.CerrarConexiones();
                return Info;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
