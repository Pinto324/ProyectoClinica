/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Objetos.EspecialidadHijaMedicos;
import Objetos.Especialidades;
import Objetos.Usuario;
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
    private ResultSet Rs;
    private Connection Conn;
    public EspecialidadesDB() {
    }
    
    public List<Especialidades> ListaDeEspecialidades(){
        Con = new Conexion();
        List<Especialidades> ListaEspecialidades = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM Especialidades");
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

}
