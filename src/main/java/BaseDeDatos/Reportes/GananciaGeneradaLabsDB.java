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
public class GananciaGeneradaLabsDB {
    private Conexion Con;
    private Connection Conn;

    public GananciaGeneradaLabsDB() {
    }
    //metodo para subir los datos ParaReporte
    public void SubirDatosParaReporte(int IdDelLab, double CantidadGeneradalabGGM, double CantidadGeneradaAppGGM, int IdExamen){
        Con = new Conexion();
        Con.IniciarConexion();
        LocalDate fechaActual = LocalDate.now();
        Date fechaSQL = Date.valueOf(fechaActual);
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into gananciageneradalabs (IdDeSolicitud, CantidadGeneradaLabAPP, CantidadPagadaAPP, IdDelExamen, FechaDeMovLab) values (?,?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, IdDelLab);
            ps.setDouble(2, CantidadGeneradalabGGM);
            ps.setDouble(3, CantidadGeneradaAppGGM);
            ps.setInt(4, IdExamen);
            ps.setDate(5, fechaSQL);
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        //metodo para subir los datos SubirDatoCargaDeDatos
    public void SubirDatoCargaDeDatos(int IdDelLab, double CantidadGeneradalabGGM, double CantidadGeneradaAppGGM, int IdExamen, Date Fecha){
        Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into gananciageneradalabs (IdDeSolicitud, CantidadGeneradaLabAPP, CantidadPagadaAPP, IdDelExamen, FechaDeMovLab) values (?,?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, IdDelLab);
            ps.setDouble(2, CantidadGeneradalabGGM);
            ps.setDouble(3, CantidadGeneradaAppGGM);
            ps.setInt(4, IdExamen);
            ps.setDate(5, Fecha);
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    //Metodo para encontrar el Top 5 Pacientes Lab
    public List<String> ReporteTop5PacienteLab(int id,String FechaInicio, String FechaFinal){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT u.NombreUsuario, COUNT(*) AS total_Examens, SUM(g.CantidadGeneradaLabAPP) AS total_ganancia FROM usuariosmedic u INNER JOIN solicitudlaboratorio c ON u.IdUsuario = c.IdDelPacienteSL INNER JOIN gananciageneradalabs g ON c.IdSolicitudLaboratorio = g.IdDeSolicitud WHERE c.IdDelLaboratorioSL = '"+id+"' AND g.FechaDeMovLab BETWEEN '"+FechaInicio+"' AND '"+FechaFinal+"'GROUP BY u.NombreUsuario ORDER BY total_ganancia desc;");
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
    //metodo para encontrar los examenes que más generan a la app
    public List<String> ReporteTop5ExamenesLab(int id,String FechaInicio, String FechaFinal){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT e.NombreExamen, COUNT(*) AS total_consultas, SUM(g.CantidadGeneradaLabAPP) AS total_ganancia FROM tipodeexamenes e INNER JOIN gananciageneradalabs g ON e.IdTipoDeExamenes = g.IdDelExamen INNER JOIN solicitudlaboratorio S ON g.IdDeSolicitud = s.IdSolicitudLaboratorio WHERE s.IdDelLaboratorioSL = '"+id+"' AND g.FechaDeMovLab BETWEEN '"+FechaInicio+"' AND '"+FechaFinal+"' GROUP BY e.NombreExamen ORDER BY total_ganancia desc;");
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
           //conseguir el historial de Solicitudes de un paciente en una fecha:
    public List<String> HistorialPacienteSolicitud(int id,String FechaInicio, String FechaFinal){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM solicitudlaboratorio INNER JOIN examenessolicitadosenlaboratorio ON examenessolicitadosenlaboratorio.IdSolicitudLaboratorioESL = IdSolicitudLaboratorio INNER JOIN tipodeexamenes ON tipodeexamenes.IdTipoDeExamenes = examenessolicitadosenlaboratorio.IdExamenESL INNER JOIN UsuariosMedic ON UsuariosMedic.IdUsuario = IdDelLaboratorioSL WHERE EstadoSL='FINALIZADA' AND IdDelPacienteSL ='"+id+"'AND FechaFinalizadoSL BETWEEN '"+FechaInicio+"' AND '"+FechaFinal+"'ORDER BY FechaFinalizadoSL desc;");
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
                dato = "Solicitud";
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
