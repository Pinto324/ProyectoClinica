/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Objetos.Consultas;
import Objetos.ConsultasHija;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author branp
 */
public class ConsultasDB {
    private Conexion Con;
    private ResultSet Rs;
    private Connection Conn;
    private List<Consultas> consultas;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public ConsultasDB() {
    }
    public List<ConsultasHija> BuscarConsultasPendientesPorIdMedicoFecha(int cod, String fecha) throws ParseException{
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM Consultas INNER JOIN Especialidades ON Especialidades.IdEspecialidades = IdDeEspecialidadDelMedico INNER JOIN UsuariosMedic ON UsuariosMedic.IdUsuario = IdDelPacienteConsultas WHERE EstadoConsulta='AGENDADA' AND IdDelMedicoConsultas ='"+cod+"' AND DATE(FechaAgendada)='"+fecha+"';");
            List<ConsultasHija> consultaLista;
            consultaLista = new ArrayList<>();
                while(U.next()){  
                    Timestamp fechaHora = U.getTimestamp(7);
                    java.sql.Date fechaHoraUtil = U.getDate(6);
                    java.time.LocalDateTime localDateTime = fechaHora.toLocalDateTime();
                    java.time.ZonedDateTime zonedDateTime = localDateTime.atZone(java.time.ZoneId.systemDefault());
                    java.util.Date date = java.util.Date.from(zonedDateTime.toInstant());         
                    ConsultasHija cons = new ConsultasHija(U.getString (12), U.getString (15), U.getInt (1), U.getInt (2), U.getInt (3), U.getInt (4), U.getDouble(5), fechaHoraUtil , date, U.getDouble(8), U.getString (9),U.getString (10));
                    consultaLista.add(cons);
                }
                U.close();
                Con.CerrarConexiones();
                return consultaLista;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public List<ConsultasHija> BuscarConsultasRevisionPorIdMedico(int cod) throws ParseException{
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM Consultas INNER JOIN Especialidades ON Especialidades.IdEspecialidades = IdDeEspecialidadDelMedico INNER JOIN UsuariosMedic ON UsuariosMedic.IdUsuario = IdDelPacienteConsultas WHERE EstadoConsulta='PENDIENTE_REVISION' AND IdDelMedicoConsultas ='"+cod+"';");
            List<ConsultasHija> consultaLista;
            consultaLista = new ArrayList<>();
                while(U.next()){  
                    Timestamp fechaHora = U.getTimestamp(7);
                    java.sql.Date fechaHoraUtil = new java.sql.Date(fechaHora.getTime());
                    ConsultasHija cons = new ConsultasHija(U.getString (12), U.getString (15), U.getInt (1), U.getInt (2), U.getInt (3), U.getInt (4), U.getDouble(5), (Date) formato.parse(String.valueOf(U.getDate(6))), (Date) fechaHoraUtil, U.getDouble(8), U.getString (9),U.getString (10));
                    consultaLista.add(cons);
                }
                U.close();
                Con.CerrarConexiones();
                return consultaLista;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    //querry para actualizar el estado de una consulta:
        public boolean ModificarPrecio(int id, String estado){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            String Ssql = "UPDATE consultas SET EstadoConsulta=? WHERE IdConsultas=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setString(1, estado);
            cambio.setInt(2, id);
            cambio.executeUpdate();
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }
    //querry para finalñizar el estado de una consulta:
    public boolean FinalizarConsulta(int id, String informe){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            String Ssql = "UPDATE consultas SET EstadoConsulta=?, InformeFinal=? WHERE IdConsultas=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setString(1, "FINALIZADA");
            cambio.setString(2, informe);
            cambio.setInt(3, id);
            cambio.executeUpdate();
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }
    //querry para buscar las consultas de un paciente por id
    public List<ConsultasHija> BuscarConsultasIdPaciente(int cod) throws ParseException{
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM Consultas INNER JOIN Especialidades ON Especialidades.IdEspecialidades = IdDeEspecialidadDelMedico INNER JOIN UsuariosMedic ON UsuariosMedic.IdUsuario = IdDelPacienteConsultas WHERE IdDelPacienteConsultas ='"+cod+"'ORDER BY FechaCreacion ASC;");
            List<ConsultasHija> consultaLista;
            consultaLista = new ArrayList<>();
                while(U.next()){  
                    Timestamp fechaHora = U.getTimestamp(7);
                    java.sql.Date fechaHoraUtil = new java.sql.Date(fechaHora.getTime());
                    ConsultasHija cons = new ConsultasHija(U.getString (12), U.getString (15), U.getInt (1), U.getInt (2), U.getInt (3), U.getInt (4), U.getDouble(5), (Date) formato.parse(String.valueOf(U.getDate(6))), (Date) fechaHoraUtil, U.getDouble(8), U.getString (9),U.getString (10));
                    consultaLista.add(cons);
                }
                U.close();
                Con.CerrarConexiones();
                return consultaLista;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
