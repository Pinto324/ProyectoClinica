/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Objetos.EspecialidadHijaMedicos;
import Objetos.Medicos.EspecialidadesMedicos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author branp
 */
public class EspecialidadesMedicosDB {
     private Conexion Con;
    private ResultSet Rs;
    private Connection Conn;

    public EspecialidadesMedicosDB() {
    }
    
    public void CrearUsuario(EspecialidadesMedicos e){
    Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into EspecialidadesMedicos (IdDelMedicoEM, IdEspecialidadEM, PrecioEspecialidad, EstadoEM) values (?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, e.getIdMedicoEM());
            ps.setInt(2, e.getIdEspecialidadEM());
            ps.setDouble(3, e.getPrecio());
            ps.setString(4, "Pendiente");
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
        }
    }
    //metodo para asignar una especialidad a un medico de una vez activa
    public void AsignarEspecialdadActiva(EspecialidadesMedicos e){
    Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into EspecialidadesMedicos (IdDelMedicoEM, IdEspecialidadEM, PrecioEspecialidad, EstadoEM) values (?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, e.getIdMedicoEM());
            ps.setInt(2, e.getIdEspecialidadEM());
            ps.setDouble(3, e.getPrecio());
            ps.setString(4, "Activa");
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
        }
    }
        public boolean EvitarRepetirEspecialidad(EspecialidadesMedicos e){
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM EspecialidadesMedicos WHERE IdEspecialidadEM='"+e.getIdEspecialidadEM()+"' AND IdDelMedicoEM='"+e.getIdMedicoEM() +"' AND (EstadoEM='Activa' OR EstadoEM='Pendiente');");
                if(U.next()){ 
                    return true;
                }
                U.close();
                Con.CerrarConexiones();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }        
    public List<EspecialidadHijaMedicos> ListaDeEspecialidadesPorID(int id){
        Con = new Conexion();
        List<EspecialidadHijaMedicos> ListaEspecialidades = new ArrayList<>();
        ResultSet U;
        try {
            U = Con.IniciarConexion().executeQuery("SELECT * FROM EspecialidadesMedicos INNER JOIN Especialidades ON Especialidades.IdEspecialidades = IdEspecialidadEM WHERE (EspecialidadesMedicos.EstadoEM = 'Activa' OR EspecialidadesMedicos.EstadoEM = 'Pendiente') AND  EspecialidadesMedicos.IdDelMedicoEM = '"+id+"';");           
                while(U.next()){  
                    EspecialidadHijaMedicos NEspecialidadM;
                    NEspecialidadM= new EspecialidadHijaMedicos(U.getInt (3),U.getInt(2),U.getDouble(4),U.getString(5),U.getInt(1),U.getString(7),U.getString(8));
                    ListaEspecialidades.add(NEspecialidadM);
                }
                U.close();
                Con.CerrarConexiones();
                return ListaEspecialidades;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    //metodo para ver las especialidades solo activas:
        public List<EspecialidadHijaMedicos> ListaDeEspecialidadesPorIDyEstado(int id, String Estado){
        Con = new Conexion();
        List<EspecialidadHijaMedicos> ListaEspecialidades = new ArrayList<>();
        ResultSet U;
        try {
            U = Con.IniciarConexion().executeQuery("SELECT * FROM EspecialidadesMedicos INNER JOIN Especialidades ON Especialidades.IdEspecialidades = IdEspecialidadEM WHERE EspecialidadesMedicos.EstadoEM = '"+Estado+"'  AND  EspecialidadesMedicos.IdDelMedicoEM = '"+id+"';");           
                while(U.next()){  
                    EspecialidadHijaMedicos NEspecialidadM;
                    NEspecialidadM= new EspecialidadHijaMedicos(U.getInt (3),U.getInt(2),U.getDouble(4),U.getString(5),U.getInt(1),U.getString(7),U.getString(8));
                    ListaEspecialidades.add(NEspecialidadM);
                }
                U.close();
                Con.CerrarConexiones();
                return ListaEspecialidades;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public boolean ModificarPrecio(int id, double Precio){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            String Ssql = "UPDATE EspecialidadesMedicos SET PrecioEspecialidad=? WHERE IdEM=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDouble(1, Precio);
            cambio.setInt(2, id);
            cambio.executeUpdate();
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }
    public boolean CambiarEstado(int id, String estado){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            String Ssql = "UPDATE EspecialidadesMedicos SET EstadoEM=? WHERE IdEM=?";
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
    public List<String> ListaEspecialidadesYDoctores(String Estado){
        Con = new Conexion();
        List<String> ListaString = new ArrayList<>();
        ResultSet U;
        try {
            U = Con.IniciarConexion().executeQuery("SELECT * FROM especialidadesmedicos INNER JOIN especialidades ON especialidades.IdEspecialidades = IdEspecialidadEM INNER JOIN usuariosmedic ON usuariosmedic.IdUsuario = IdDelMedicoEM WHERE especialidadesmedicos.EstadoEM ='"+Estado+"' ;");           
                while(U.next()){  
                    String dato;
                    dato = String.valueOf(U.getInt(U.findColumn("IdDelMedicoEM")));
                    ListaString.add(dato);
                    dato = U.getString(U.findColumn("NombreUsuario"));
                    ListaString.add(dato);
                    dato = U.getString(U.findColumn("NombreEspecialidad"));
                    ListaString.add(dato);
                }
                U.close();
                Con.CerrarConexiones();
                return ListaString;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    //metodo que se encarga de devolver especialidades pendientes admin
        public List<String> EspecialidadesPendientes(){
        Con = new Conexion();
        List<String> ListaString = new ArrayList<>();
        ResultSet U;
        try {
            U = Con.IniciarConexion().executeQuery("SELECT * FROM especialidadesmedicos INNER JOIN especialidades ON especialidades.IdEspecialidades = IdEspecialidadEM INNER JOIN usuariosmedic ON usuariosmedic.IdUsuario = IdDelMedicoEM WHERE especialidadesmedicos.EstadoEM ='Pendiente' ;");           
                while(U.next()){  
                    String dato;
                    dato = String.valueOf(U.getInt(U.findColumn("IdEM")));
                    ListaString.add(dato);
                    dato = U.getString(U.findColumn("NombreUsuario"));
                    ListaString.add(dato);
                    dato = U.getString(U.findColumn("NombreEspecialidad"));
                    ListaString.add(dato);
                    dato = U.getString(U.findColumn("PrecioEspecialidad"));
                    ListaString.add(dato);
                }
                U.close();
                Con.CerrarConexiones();
                return ListaString;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    public boolean ActualizarEstadoAdmin(int id){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            String Ssql = "UPDATE especialidades SET EstadoEspecialidad=? WHERE IdEspecialidades=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setBoolean(1, true);
            cambio.setInt(2, id);
            cambio.executeUpdate();
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            //agregarle porcentaje en la app
            Ssql = "insert into porcentajedecobroappconsultas (IdDeEspecialidadPorcentaje,Porcentaje, FechaDeInicio, Activa) values (?,?,?,?);";
            cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setInt(1, id);
            cambio.setDouble(2, 0.04);
            cambio.setDate(3, sqlDate);
            cambio.setBoolean(4, true);
            cambio.executeUpdate();  
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }
}
