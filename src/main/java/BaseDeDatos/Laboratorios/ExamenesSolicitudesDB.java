/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Laboratorios;

import BaseDeDatos.Conexion;
import Objetos.Usuario;
import Servicios.Reportes.GananciaGeneradaLabsServicio;
import Servicios.UsuarioServicio;
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
public class ExamenesSolicitudesDB {
    private Conexion Con;
    private Connection Conn;

    public ExamenesSolicitudesDB() {
    }
    
     //lista de nombre de los examenes en una solicitud
    public List<String> ListaDeExamenesPorIDSolicitud(int IdSolicitud){
        Con = new Conexion();
        List<String> ListaExamenesConsulta = new ArrayList<>();
        ResultSet U;
        try {
            U = Con.IniciarConexion().executeQuery("SELECT * FROM examenessolicitadosenlaboratorio INNER JOIN TipoDeExamenes ON TipoDeExamenes.IdTipoDeExamenes = IdExamenESL WHERE IdSolicitudLaboratorioESL = '"+IdSolicitud+"';");           
                while(U.next()){  
                    String Examen = U.getString(7);
                    ListaExamenesConsulta.add(Examen);
                    if(U.getString(5)==null){
                        Examen = "No";
                    }else{
                        Examen = U.getString(5);
                    }
                    ListaExamenesConsulta.add(Examen);
                    Examen = String.valueOf(U.getInt(1));
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
    //metodo para meter el archivo en la solicitud de examen:
    public boolean SubirArchivoPDF(int Id, byte[] cuerpo,String Nombre){
        Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "UPDATE examenessolicitadosenlaboratorio SET ArchivoExamenLaboratorioESL=?, NombreDeArchivoESL=? WHERE IdESL=?;";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setBytes(1, cuerpo);
            ps.setString(2, Nombre);
            ps.setInt(3, Id);
            ps.executeUpdate();         
            Con.CerrarConexiones();
            Conn.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    //metodo encargado de pagar todos los examenes de una Solicitud al lab y al admin
    public boolean PagarExamenesDeSolicitud(int IdSolicitud){
        Con = new Conexion();
        ResultSet U;
        UsuarioServicio pago = new UsuarioServicio();
        try {
            U = Con.IniciarConexion().executeQuery("SELECT * FROM examenessolicitadosenlaboratorio INNER JOIN solicitudlaboratorio ON solicitudlaboratorio.IdSolicitudLaboratorio = IdSolicitudLaboratorioESL INNER JOIN exameneslaboratorios ON exameneslaboratorios.IdExamenEL = IdExamenESL INNER JOIN porcentajedecobroappexamenes ON porcentajedecobroappexamenes.IdDelExamenPCE = IdExamenESL WHERE IdSolicitudLaboratorioESL = '"+IdSolicitud+"' AND ActivaPCE='1';");           
                while(U.next()){  
                    double PagoAdmin = (U.getDouble(U.findColumn("PrecioExamen")))*(U.getDouble(U.findColumn("PorcentajePCE")));
                    double PagoLab = (U.getDouble(U.findColumn("PrecioExamen")))-PagoAdmin;
                    int IdLab = (U.getInt(U.findColumn("IdDelLabEL")));
                    int IdExamen = (U.getInt(U.findColumn("IdExamenESL")));
                    int IdConsulta = (U.getInt(U.findColumn("IdSolicitudLaboratorioESL")));
                    pago.PagarSolicitud(PagoLab, PagoAdmin, IdLab);
                    //se realizo el pago ahora tengo que mandar a gananacia lab
                    GananciaGeneradaLabsServicio Reporte = new GananciaGeneradaLabsServicio();
                    Reporte.IngresarGanancia(IdConsulta, PagoLab, PagoAdmin, IdExamen);
                }
                U.close();
                Con.CerrarConexiones();
                return true;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    
}
