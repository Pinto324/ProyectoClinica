/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Objetos.Especialidades;
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
public class EspecialidadesDB {
     private Conexion Con;
    private Connection Conn;
    public EspecialidadesDB() {
    }
    
    public List<Especialidades> ListaDeEspecialidades(){
        Con = new Conexion();
        List<Especialidades> ListaEspecialidades = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM Especialidades where EstadoEspecialidad = true");
            while(U.next()){  
                Especialidades NEspecialidadM;
                NEspecialidadM= new Especialidades(U.getInt (1),U.getString (2),U.getString (3));
                ListaEspecialidades.add(NEspecialidadM);
            }
                U.close();
                Con.CerrarConexiones();
                return ListaEspecialidades;            
        } catch (SQLException ex) {
        }
        return null;
    }

    public int BuscarIdEspecialidadPorNombre(String Nombre){
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM Especialidades WHERE NombreEspecialidad='"+Nombre+"';");
                if(U.next()){  
                    return U.getInt(1);
                }
                U.close();
                Con.CerrarConexiones();
        } catch (SQLException ex) {
        }
        return -1;
    }
    //metodo para insertar especialidades en la carga de datos
    public void CrearEspecialidad(Especialidades user){
    Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into especialidades (IdEspecialidades ,NombreEspecialidad, DescripcionEspecialidad, EstadoEspecialidad) values (?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, user.getIdEspecialidades());
            ps.setString(2, user.getNombreEspecialidad());
            ps.setString(3, user.getDescripcionEspecialidad());
            ps.setBoolean(4, true);
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
        }
    }
       //metodo para insertar especialidades pendiente
    public boolean CrearEspecialidadPendiente(String nombre, String desc){
    Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into especialidades (NombreEspecialidad, DescripcionEspecialidad, EstadoEspecialidad) values (?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2,desc);
            ps.setBoolean(3, false);
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
            return true;
        } catch (SQLException ex) {
        }
        return false;
    }
    public List<Especialidades> SolicitudesDeEspecialidad(){
        Con = new Conexion();
        List<Especialidades> ListaEspecialidades = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM Especialidades where EstadoEspecialidad = false");
            while(U.next()){  
                Especialidades NEspecialidadM;
                NEspecialidadM= new Especialidades(U.getInt (1),U.getString (2),U.getString (3));
                ListaEspecialidades.add(NEspecialidadM);
            }
                U.close();
                Con.CerrarConexiones();
                return ListaEspecialidades;            
        } catch (SQLException ex) {
        }
        return null;
    }
            //eliminar especialidad 
    public boolean EliminarEspecialidadNuevo(int id){
        try{
            Con = new Conexion();        
            Con.IniciarConexion();
            PreparedStatement Borrador = Con.getConexion().prepareStatement("delete from especialidades WHERE IdEspecialidades='" + id + "';");
            Borrador.executeUpdate();
            Con.CerrarConexiones();
            Borrador.close();
            return true;
        }catch(SQLException ex){
            System.out.println(ex);
            return false;
        }
    }
}
