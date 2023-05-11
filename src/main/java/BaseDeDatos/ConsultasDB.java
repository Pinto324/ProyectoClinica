/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Objetos.Consultas;
import Objetos.ConsultasHija;
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
public class ConsultasDB {
    private Conexion Con;
    private ResultSet Rs;
    private Connection Conn;
    private List<Consultas> consultas;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public ConsultasDB() {
    }
    
    //metodo para crear un consulta
    public int CrearConsulta(int idIdPaciente, int idMedico, int idEspe, String fecha, double precio){
        Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PorcentajeConsultasServicio SC = new PorcentajeConsultasServicio();
        double porcentaje = SC.BuscarPorcentajeComision(idEspe);
        java.sql.Date fechaActual = new Date(System.currentTimeMillis());
        java.util.Date fechaHora;
        Timestamp timestamp;
        int idConsulta = -1;
        try {
            fechaHora = formatter.parse(fecha);
            timestamp = new Timestamp(fechaHora.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasDB.class.getName()).log(Level.SEVERE, null, ex);
            return -3;
        }
        try {
            sql = "insert into consultas (IdDelPacienteConsultas, IdDelMedicoConsultas, IdDeEspecialidadDelMedico, PorcentajeAPP, FechaCreacion, FechaAgendada,PrecioConsulta,InformeFinal,EstadoConsulta) values (?,?,?,?,?,?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idIdPaciente);
            ps.setInt(2, idMedico);
            ps.setInt(3, idEspe);
            ps.setDouble(4, porcentaje);
            ps.setDate(5, fechaActual);
            ps.setTimestamp(6, timestamp);
            ps.setDouble(7, precio);
            ps.setString(8, null);
            ps.setString(9, "AGENDADA");
            ps.executeUpdate();    
            ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idConsulta = rs.getInt(1);
                }
            Con.CerrarConexiones();
            Conn.close();
            return idConsulta;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -2;
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
                    ConsultasHija cons = new ConsultasHija(U.getString (12), U.getString (15), U.getInt (1), U.getInt (2), U.getInt (3), U.getInt (4), U.getDouble(5), (java.util.Date) formato.parse(String.valueOf(U.getDate(6))), (Date) fechaHoraUtil, U.getDouble(8), U.getString (9),U.getString (10));
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
    //Metodo para filtrar las horas ya ocupadas en alguna consulta
        public List<String> FiltrarHorasParaConsulta(List<String> horas,String Fecha,int id){
        Con = new Conexion();
        try {
            List<String> HorasDipsonibles = new ArrayList<>();
            ResultSet U = null;
            for(int i = 0; i < horas.size(); i++){
                String fechaHora = Fecha + " " + horas.get(i) + ":00";
                U = Con.IniciarConexion().executeQuery("SELECT * FROM Consultas WHERE FechaAgendada='"+fechaHora+"' AND IdDelMedicoConsultas ='"+id+"';");
                if(U.next()){
               }else{
                    HorasDipsonibles.add(horas.get(i));
                }
            }  
                U.close();
                Con.CerrarConexiones();
                return HorasDipsonibles;            
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
                    ConsultasHija cons = new ConsultasHija(U.getString (12), U.getString (15), U.getInt (1), U.getInt (2), U.getInt (3), U.getInt (4), U.getDouble(5), (java.util.Date) formato.parse(String.valueOf(U.getDate(6))), (Date) fechaHoraUtil, U.getDouble(8), U.getString (9),U.getString (10));
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
    //metodo para mostrar al paciente las consultas pendientes de revisión
    public List<String> ConsultasRevisionExamenPaciente(int cod) throws ParseException{
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM Consultas INNER JOIN Especialidades ON Especialidades.IdEspecialidades = IdDeEspecialidadDelMedico INNER JOIN UsuariosMedic ON UsuariosMedic.IdUsuario = IdDelMedicoConsultas WHERE EstadoConsulta='EXAMEN_PENDIENTE' AND IdDelPacienteConsultas ='"+cod+"';");
            List<String> consultaLista;
            consultaLista = new ArrayList<>();
                while(U.next()){  
                    String Dato = String.valueOf(U.getInt(U.findColumn("IdConsultas")));
                    consultaLista.add(Dato);
                    Dato = (U.getString(U.findColumn("NombreUsuario")));
                    consultaLista.add(Dato);
                    Dato = (U.getString(U.findColumn("NombreEspecialidad")));
                    consultaLista.add(Dato);
                    Dato = String.valueOf(U.getDouble(U.findColumn("PrecioConsulta")));
                    consultaLista.add(Dato);
                    Dato = String.valueOf(U.getTimestamp(U.findColumn("FechaAgendada")));
                    consultaLista.add(Dato);
                    Dato = (U.getString(U.findColumn("EstadoConsulta")));
                    consultaLista.add(Dato);
                    Dato = (U.getString(U.findColumn("Email")));
                    consultaLista.add(Dato);
                    Dato = (U.getString(U.findColumn("Telefono")));
                    consultaLista.add(Dato);
                }
                U.close();
                Con.CerrarConexiones();
                return consultaLista;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    //metodo para ingresar las consultas de la carga de datos:
    public void IngresaConsulta(Consultas c, Timestamp fecha){
        Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into consultas (IdConsultas,IdDelPacienteConsultas, IdDelMedicoConsultas, IdDeEspecialidadDelMedico, PorcentajeAPP, FechaCreacion, FechaAgendada,PrecioConsulta,InformeFinal,EstadoConsulta) values (?,?,?,?,?,?,?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, c.getIdConsultas());
            ps.setInt(2, c.getIdPaciente());
            ps.setInt(3, c.getIdMedico());
            ps.setInt(4, c.getIdEspecialidadMedico());
            ps.setDouble(5, c.getPorcentaje());
            java.sql.Date fechaSql = new java.sql.Date(c.getFechaCreacion().getTime());
            ps.setDate(6, fechaSql);
            ps.setTimestamp(7, fecha);
            ps.setDouble(8, c.getPrecio());
            ps.setString(9, c.getInformeFinal());
            ps.setString(10, c.getEstado());
            ps.executeUpdate();    
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
