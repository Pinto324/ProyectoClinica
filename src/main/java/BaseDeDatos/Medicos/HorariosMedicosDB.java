/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Medicos;

import BaseDeDatos.Conexion;
import Objetos.Medicos.EspecialidadesMedicos;
import Objetos.Medicos.Horario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author branp
 */
public class HorariosMedicosDB {
    private Conexion Con;
    private Connection Conn;

    public HorariosMedicosDB() {
    }
    
    //metodo para ver los horarios en el sistema 
    public List<Horario> HorariosPorId(int IdMedico){
        Con = new Conexion();
        List<Horario> ListaHorario = new ArrayList<>();
        ResultSet U;
        try {
            U = Con.IniciarConexion().executeQuery("SELECT * FROM horariomedicos WHERE IdDelMedicoHM = '"+IdMedico+"';");           
                while(U.next()){  
                    Horario horario;
                    LocalTime HoraEntrada = U.getTime(3).toLocalTime();
                    LocalTime HoraSalida = U.getTime(4).toLocalTime();
                    horario= new Horario(HoraEntrada, HoraSalida);
                    ListaHorario.add(horario);
                }
                U.close();
                Con.CerrarConexiones();
                return ListaHorario;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    public void CrearHorario(Horario Hn, int IdMedico){
    Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into horariomedicos (IdDelMedicoHM, HoraDeEntrada, HoraDeSalida) values (?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, IdMedico);
            ps.setTime(2, Time.valueOf(Hn.getHoraEntrada()));
            ps.setTime(3, Time.valueOf(Hn.getHoraSalida()));
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
        }
    }
}
