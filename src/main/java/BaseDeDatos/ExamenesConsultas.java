/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Objetos.EspecialidadHijaMedicos;
import Objetos.Medicos.EspecialidadesMedicos;
import Objetos.ExamenesSolicitadosConsultas;
import Utilidades.ArchivoPDF;
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
public class ExamenesConsultas {
    private Conexion Con;
    private Connection Conn;

    public ExamenesConsultas() {
    }
    
    public void CrearExamenSolicitudConsulta(ExamenesSolicitadosConsultas e){
    Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into examenessolicitadosenlasconsultas (IdConsultaEC, IdExamenEC) values (?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, e.getIdConsulta());
            ps.setInt(2, e.getIdExamen());
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    //lista de nombre de los examenes
    public List<String> ListaDeExamenesPorID(int id){
        Con = new Conexion();
        List<String> ListaExamenesConsulta = new ArrayList<>();
        ResultSet U;
        try {
            U = Con.IniciarConexion().executeQuery("SELECT * FROM ExamenesSolicitadosEnLasConsultas INNER JOIN TipoDeExamenes ON TipoDeExamenes.IdTipoDeExamenes = IdExamenEC WHERE IdConsultaEC = '"+id+"';");           
                while(U.next()){  
                    String Examen = U.getString(7);
                    ListaExamenesConsulta.add(Examen);
                }
                U.close();
                Con.CerrarConexiones();
                return ListaExamenesConsulta;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    //enlistado de pdfs
    public List<ArchivoPDF> ListaDePdfs(int id){
        Con = new Conexion();
        List<ArchivoPDF> ListaPdf = new ArrayList<>();
        ResultSet U;
        try {
            U = Con.IniciarConexion().executeQuery("SELECT * FROM examenessolicitadosenlasconsultas WHERE IdConsultaEC = '"+id+"';");           
                while(U.next()){  
                    String NombreExamen = (U.getString(5)+".pdf");
                    byte[] Cuerpo = U.getBytes(4);
                    ArchivoPDF NuevoArchivo = new ArchivoPDF(NombreExamen, Cuerpo);
                    ListaPdf.add(NuevoArchivo);
                }
                U.close();
                Con.CerrarConexiones();
                return ListaPdf;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    //Evitar repetir examenes en la consulta
    public boolean EvitarRepetirEspecialidad(ExamenesSolicitadosConsultas e){
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM examenessolicitadosenlasconsultas WHERE IdConsultaEC='"+e.getIdConsulta()+"' AND IdExamenEC='"+e.getIdExamen()+"';");
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
    //eliminar examen de consulta
    public boolean EliminarUsuario(ExamenesSolicitadosConsultas e){
        try{
            Con = new Conexion();        
            Con.IniciarConexion();
            PreparedStatement Borrador = Con.getConexion().prepareStatement("delete from examenessolicitadosenlasconsultas WHERE IdConsultaEC='" + e.getIdConsulta() + "' AND IdExamenEC='"+e.getIdExamen() +"';");
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
