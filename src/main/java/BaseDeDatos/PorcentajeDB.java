/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Servicios.Reportes.GananciaGeneradaMedicosServicio;
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
public class PorcentajeDB {
    private Conexion Con;
    private Connection Conn;

    public PorcentajeDB() {
    }
   //metodo para traer los porcentajes de examenes
    public List<String> PorcentajeExamenes(){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM porcentajedecobroappexamenes INNER JOIN tipodeexamenes ON tipodeexamenes.IdTipoDeExamenes = IdDelExamenPCE WHERE ActivaPCE=true;");
            while(U.next()){  
                String dato = String.valueOf(U.getInt(U.findColumn("IdPorcentajeDeCobroAPPExamenes")));
                Info.add(dato);
                dato = String.valueOf(U.getInt(U.findColumn("IdDelExamenPCE")));;
                Info.add(dato);
                dato = U.getString(U.findColumn("NombreExamen"));
                Info.add(dato);
                dato = String.valueOf(U.getDouble(U.findColumn("PorcentajePCE"))*100);
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
       //metodo para traer los porcentajes de especialidades
    public List<String> PorcentajeEspecialidades(){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM porcentajedecobroappconsultas INNER JOIN especialidades ON especialidades.IdEspecialidades = IdDeEspecialidadPorcentaje WHERE Activa=true;");
            while(U.next()){  
                String dato = String.valueOf(U.getInt(U.findColumn("IdPorcentajeAPPConsultas")));
                Info.add(dato);
                dato = String.valueOf(U.getInt(U.findColumn("IdDeEspecialidadPorcentaje")));;
                Info.add(dato);
                dato = U.getString(U.findColumn("NombreEspecialidad"));
                Info.add(dato);
                dato = String.valueOf(U.getDouble(U.findColumn("Porcentaje"))*100);
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
    //metodo para finalizar el antiguo porcentaje y crear un nuevo owo
    public boolean IngresarPorcentajeExamenes(int Id, double porcentaje){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            //Envia el pago al medico
            String Ssql = "UPDATE porcentajedecobroappexamenes SET FechaFinalPCE=?,ActivaPCE=? WHERE IdDelExamenPCE=? AND ActivaPCE=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDate(1, sqlDate);
            cambio.setBoolean(2, false);
            cambio.setInt(3, Id);
            cambio.setBoolean(4, true);
            cambio.executeUpdate();
            //crear el nuevo porcentaje en la app
            Ssql = "insert into porcentajedecobroappexamenes (IdDelExamenPCE,PorcentajePCE, FechaDeInicioPCE, ActivaPCE) values (?,?,?,?);";
            cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setInt(1, Id);
            cambio.setDouble(2, porcentaje);
            cambio.setDate(3, sqlDate);
            cambio.setBoolean(4, true);
            cambio.executeUpdate();
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }  
        //metodo para finalizar el antiguo porcentaje y crear un nuevo owo especialidades
    public boolean IngresarPorcentajeEspecialidades(int Id, double porcentaje){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            //Envia el pago al medico
            String Ssql = "UPDATE porcentajedecobroappconsultas SET FechaFinal=?,Activa=? WHERE IdDeEspecialidadPorcentaje=? AND Activa=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDate(1, sqlDate);
            cambio.setBoolean(2, false);
            cambio.setInt(3, Id);
            cambio.setBoolean(4, true);
            cambio.executeUpdate();
            //Envia el pago Al Admin
            Ssql = "insert into porcentajedecobroappconsultas (IdDeEspecialidadPorcentaje,Porcentaje, FechaDeInicio, Activa) values (?,?,?,?);";
            cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setInt(1, Id);
            cambio.setDouble(2, porcentaje);
            cambio.setDate(3, sqlDate);
            cambio.setBoolean(4, true);
            cambio.executeUpdate();
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }  
            //metodo para finalizar el antiguo porcentaje y crear un nuevo owo especialidades
    public void CargaDeDatosExamen(int Id){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            //Envia el pago al medico
          String Ssql = "insert into porcentajedecobroappexamenes (IdDelExamenPCE,PorcentajePCE, FechaDeInicioPCE, ActivaPCE) values (?,?,?,?);";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setInt(1, Id);
            cambio.setDouble(2, 0.04);
            cambio.setDate(3, sqlDate);
            cambio.setBoolean(4, true);
            cambio.executeUpdate();
            Con.CerrarConexiones();
        }catch(SQLException e){
        }
    }  
                //metodo para finalizar el antiguo porcentaje y crear un nuevo owo especialidades
    public void CargaDeDatosEspecialidad(int Id){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            //Envia el pago al medico
          String Ssql = "insert into porcentajedecobroappconsultas (IdDeEspecialidadPorcentaje,Porcentaje, FechaDeInicio, Activa) values (?,?,?,?);";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setInt(1, Id);
            cambio.setDouble(2, 0.04);
            cambio.setDate(3, sqlDate);
            cambio.setBoolean(4, true);
            cambio.executeUpdate();
            Con.CerrarConexiones();
        }catch(SQLException e){
        }
    } 
}
