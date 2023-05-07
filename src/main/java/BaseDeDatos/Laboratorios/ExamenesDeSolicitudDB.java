/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Laboratorios;

import BaseDeDatos.Conexion;
import java.sql.Connection;
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
}
