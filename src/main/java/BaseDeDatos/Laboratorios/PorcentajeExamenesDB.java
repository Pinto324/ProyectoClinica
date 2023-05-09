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

/**
 *
 * @author branp
 */
public class PorcentajeExamenesDB {
    private Conexion Con;
    private Connection Conn;
    public PorcentajeExamenesDB() {
    }
    
    public double BuscarPorcentajeComision(int IdExamen){
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM porcentajedecobroappexamenes WHERE IdDelExamenPCE='"+IdExamen+"' AND ActivaPCE=true;");
            double porciento;
                if(U.next()){  
                    porciento = U.getDouble(3);
                }else{
                    U.close();
                    Con.CerrarConexiones();
                    return -1;
                }
                U.close();
                Con.CerrarConexiones(); 
                return porciento;
        } catch (SQLException ex) {
        }
      return -1;
    }
}
