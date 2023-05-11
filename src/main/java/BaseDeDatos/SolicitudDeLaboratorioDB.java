/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Objetos.Laboratorios.SolicitudDeLaboratorio;
import Servicios.Medicos.PorcentajeConsultasServicio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author branp
 */
public class SolicitudDeLaboratorioDB {
    private Conexion Con;
    private Connection Conn;

    public SolicitudDeLaboratorioDB() {
    }
    //devuelve una lista de datos de las solicitudes de un lab
    public List<String> ListaSolicitudesLab(int IdLab){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM solicitudlaboratorio INNER JOIN usuariosmedic ON usuariosmedic.IdUsuario = IdDelPacienteSL WHERE  IdDelLaboratorioSL = '"+IdLab+"' AND EstadoSL = 'PENDIENTE';");
            while(U.next()){ 
                String dato;
                dato = String.valueOf(U.getInt(1));
                Info.add(dato);
                dato = U.getString(9);
                Info.add(dato);
                dato = String.valueOf(U.getDate(5));
                Info.add(dato);
                dato = U.getString(7);
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
        //devuelve una lista de datos de las solicitudes de un lab
    public List<String> ListaDeSolicitudesPaciente(int IdPaciente){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM solicitudlaboratorio INNER JOIN usuariosmedic ON usuariosmedic.IdUsuario = IdDelLaboratorioSL WHERE  IdDelPacienteSL = '"+IdPaciente+"' AND (EstadoSL='PENDIENTE' OR EstadoSL='FINALIZADA');");
            while(U.next()){ 
                String dato;
                dato = String.valueOf(U.getInt(U.findColumn("IdSolicitudLaboratorio")));
                Info.add(dato);
                dato = U.getString(U.findColumn("NombreUsuario"));
                Info.add(dato);
                dato = String.valueOf(U.getDate(U.findColumn("FechaSolicitadoSL")));
                Info.add(dato);
                dato = U.getString(U.findColumn("Telefono"));
                Info.add(dato);
                dato = U.getString(U.findColumn("Email"));
                Info.add(dato);
                dato = U.getString(U.findColumn("EstadoSL"));
                Info.add(dato);  
                dato = String.valueOf(U.getDate(U.findColumn("FechaFinalizadoSL")));
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
    
        //querry para finalizar el estado de una solicitud:
    public boolean FinalizarSolicitud(int id){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            java.util.Date fechaActual = new java.util.Date();
            Date fechaSql = new java.sql.Date(fechaActual.getTime());
            String Ssql = "UPDATE solicitudlaboratorio SET EstadoSL=?, FechaFinalizadoSL=? WHERE IdSolicitudLaboratorio=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setString(1, "FINALIZADA");
            cambio.setDate(2, fechaSql);
            cambio.setInt(3, id);
            cambio.executeUpdate();
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }
    //metodo para crear una solicitud:
        public int CrearSolicitud(int Idlab, int IdPaciente, String Estado){
        Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date fechaActual = new Date(System.currentTimeMillis());
        int idSolicitud = -1;
        try {
            sql = "insert into solicitudlaboratorio (IdDelPacienteSL, IdDelLaboratorioSL, PorcentajeAppSL, FechaSolicitadoSL, EstadoSL) values (?,?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, IdPaciente);
            ps.setInt(2, Idlab);
            ps.setDouble(3, 0.00);
            ps.setDate(4, fechaActual);
            ps.setString(5, Estado);
            ps.executeUpdate();    
            ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idSolicitud = rs.getInt(1);
                }
            Con.CerrarConexiones();
            Conn.close();
            return idSolicitud;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -2;
    }
            //metodo para IngresarSolicitudCarga
    public void IngresarSolicitudCarga(SolicitudDeLaboratorio s){
        Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into solicitudlaboratorio (IdSolicitudLaboratorio,IdDelPacienteSL, IdDelLaboratorioSL, PorcentajeAppSL, FechaSolicitadoSL,FechaFinalizadoSL , EstadoSL) values (?,?,?,?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, s.getIdSolicitud());
            ps.setInt(2, s.getIdDelPacienteSL());
            ps.setInt(3, s.getIdDelLaboratorioSL());
            ps.setDouble(4, s.getPorcentajeAppSL());
            ps.setDate(5, s.getFechaSolicitadoSL());
            ps.setDate(6, s.getFechaFinalizadoSL());
            ps.setString(7, s.getEstadoSL());
            ps.executeUpdate();    
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
            //querry para oficializar el estado de una solicitud:
    public boolean OficializarSolicitud(int id){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            String Ssql = "UPDATE solicitudlaboratorio SET EstadoSL=? WHERE IdSolicitudLaboratorio=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setString(1, "PENDIENTE");
            cambio.setInt(2, id);
            cambio.executeUpdate();
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }
}
