/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Laboratorios;

import BaseDeDatos.Conexion;
import Objetos.Laboratorios.ExamenesLaboratorios;
import Objetos.Laboratorios.TipoDeExamen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author branp
 */
public class TipoDeExamenDB {
    private Conexion Con;
    private Connection Conn;
    public TipoDeExamenDB() {
    }
    
    public List<TipoDeExamen> ListaDeExamenes(){
        Con = new Conexion();
        List<TipoDeExamen> ListaExamenes = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM TipoDeExamenes");
            while(U.next()){  
                TipoDeExamen Examen;
                Examen= new TipoDeExamen(U.getInt (1),U.getString (2),U.getString (3));
                ListaExamenes.add(Examen);
            }
                U.close();
                Con.CerrarConexiones();
                return ListaExamenes;            
        } catch (SQLException ex) {
        }
        return null;
    }
    
            
    public int BuscarPorUserName(String User){
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM tipodeexamenes WHERE NombreExamen='"+User+"';");
            int Id;
                if(U.next()){  
                    Id= U.getInt(1);
                }else{
                    U.close();
                    Con.CerrarConexiones();
                    return -1;
                }
                U.close();
                Con.CerrarConexiones();
                return Id;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -2;
    }
    //metodo el cual crea una solicitud para que le agreguen el examen Pendiente:
    public void CrearSolicitudDeExamenLab(ExamenesLaboratorios e){
        Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into exameneslaboratorios (IdDelLabEL, IdExamenEL, PrecioExamen, EstadoEL) values (?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, e.getIdDelLab());
            ps.setInt(2, e.getIdExamen());
            ps.setDouble(3, e.getPrecio());
            ps.setString(4, "Pendiente");
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
        }
    }
    //devuelve una lista de datos de los examenes de un lab especifico
    public List<String> ListaExamenesLab(int idLab){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM exameneslaboratorios INNER JOIN tipodeexamenes ON tipodeexamenes.IdTipoDeExamenes = IdExamenEL WHERE (exameneslaboratorios.EstadoEL = 'Activa' OR exameneslaboratorios.EstadoEL = 'Pendiente') AND  exameneslaboratorios.IdDelLabEL = '"+idLab+"';");
            while(U.next()){ 
                String dato;
                dato = String.valueOf(U.getInt(1));
                Info.add(dato);
                dato = U.getString(7);
                Info.add(dato);
                dato = String.valueOf(U.getDouble(4));
                Info.add(dato);
                dato = U.getString(5);
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
    //Metodo para modificar el precio de un examen asignado al laboratorio
    public boolean ModificarPrecio(int id, double Precio){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            String Ssql = "UPDATE exameneslaboratorios SET PrecioExamen=? WHERE IdEL=?";
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
    //metodo para comprobar que no se repita el examen:
    public boolean EvitarRepetirExamen(ExamenesLaboratorios e){
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM exameneslaboratorios WHERE IdDelLabEL='"+e.getIdDelLab()+"' AND IdExamenEL='"+e.getIdExamen()+"';");
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
    
}
