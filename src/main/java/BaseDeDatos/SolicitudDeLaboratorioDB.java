/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

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
    
        //querry para finalizar el estado de una solicitud:
    public boolean FinalizarSolicitud(int id){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            String Ssql = "UPDATE solicitudlaboratorio SET EstadoSL=? WHERE IdSolicitudLaboratorio=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setString(1, "FINALIZADA");
            cambio.setInt(2, id);
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
