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
public class ExamenesDeSolicitudDB {
     private Conexion Con;
    private Connection Conn;

    public ExamenesDeSolicitudDB() {
    }
    
        //devuelve una lista de datos de los examenes de un lab para hacer una solicitud
    public List<String> ListaExamenesSolicitudes(int idLab){
        Con = new Conexion();
        List<String> Info = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM exameneslaboratorios INNER JOIN tipodeexamenes ON tipodeexamenes.IdTipoDeExamenes = IdExamenEL WHERE exameneslaboratorios.EstadoEL = 'Activa' AND  exameneslaboratorios.IdDelLabEL = '"+idLab+"';");
            while(U.next()){ 
                String dato;
                dato = String.valueOf(U.getInt(U.findColumn("IdEL")));
                Info.add(dato);
                dato = U.getString(U.findColumn("NombreExamen"));
                Info.add(dato);
                dato = String.valueOf(U.getDouble(U.findColumn("PrecioExamen")));
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
            //metodo para insertar examenes en la carga de datos
    public void AsignarExamenLaboratorio(ExamenesLaboratorios user){
    Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into exameneslaboratorios (IdDelLabEL,IdExamenEL, PrecioExamen, EstadoEL) values (?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, user.getIdDelLab());
            ps.setInt(2, user.getIdExamen());
            ps.setDouble(3, user.getPrecio());
            ps.setString(4, user.getEstado());
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
        }
    }
}
