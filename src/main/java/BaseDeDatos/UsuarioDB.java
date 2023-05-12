/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Objetos.Especialidades;
import Objetos.Usuario;
import Servicios.Reportes.GananciaGeneradaLabsServicio;
import Servicios.Reportes.GananciaGeneradaMedicosServicio;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author branp
 */
public class UsuarioDB {
    private Conexion Con;
    private ResultSet Rs;
    private Connection Conn;
    private List<Usuario> usuarios;
    public UsuarioDB() {
    }
    
    public Usuario BuscarPorCodigo(int cod){
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM UsuariosMedic WHERE IdUsuario='"+cod+"';");
            Usuario Usern;
                if(U.next()){  
                    Usern= new Usuario(U.getInt (1),U.getString (2),U.getString (3),U.getString (4),
                    U.getString (5),U.getString (6),U.getString (7),U.getString (8),U.getDate(9),U.getString(10),
                    U.getDouble(11)
                    );
                }else{
                    U.close();
                    Con.CerrarConexiones();
                    return null;
                }
                U.close();
                Con.CerrarConexiones();
                return Usern;            
        } catch (SQLException ex) {
        }
        return null;
    }
        
    public Usuario BuscarPorUserName(String User){
        Con = new Conexion(); 
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM UsuariosMedic WHERE Username='"+User+"';");
            Usuario Usern;
                if(U.next()){  
                    Usern= new Usuario(U.getInt (1),U.getString (2),U.getString (3),U.getString (4),
                    U.getString (5),U.getString (6),U.getString (7),U.getString (8),U.getDate(9),U.getString(10),
                    U.getDouble(11)
                    );
                }else{
                    U.close();
                    Con.CerrarConexiones();
                    return null;
                }
                U.close();
                Con.CerrarConexiones();
                return Usern;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public void CrearUsuario(Usuario user){
    Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
            sql = "insert into UsuariosMedic (IdUsuario,NombreUsuario, Username, Password, Direccion, CUI, Telefono,Email,FechaNacimiento,Tipo,Saldo) values (?,?,?,?,?,?,?,?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setInt(1, user.getCodigo());
            ps.setString(2, user.getNombre());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getDireccion());
            ps.setString(6, user.getCUI());
            ps.setString(7, user.getTelefono());
            ps.setString(8, user.getEmail());
            ps.setDate(9, user.getFechaNacimiento());
            ps.setString(10, user.getTipo());
            ps.setDouble(11, user.getSaldo());           
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
        } catch (SQLException ex) {
            System.out.println("ya hay un usuario con ese id en el sistema");
        }
    }
    public boolean ModificarDatos(Usuario Nuevo){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            String Ssql = "UPDATE Usuarios SET NombreUsuario=?, Username=?, Password=?, Direccion=?, CUI=?,Telefono=?,FechaNacimiento=?,Email=?, Tipo=?, Saldo=? WHERE Codigo=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setString(1, Nuevo.getNombre());
            cambio.setString(2, Nuevo.getUserName());
            cambio.setString(3, Nuevo.getPassword());
            cambio.setString(4, Nuevo.getDireccion());
            cambio.setString(5, Nuevo.getCUI());
            cambio.setString(6, Nuevo.getTelefono());
            cambio.setDate(7, Nuevo.getFechaNacimiento());
            cambio.setString(8, Nuevo.getEmail());
            cambio.setString(9, Nuevo.getTipo());
            cambio.setDouble(10, Nuevo.getSaldo());
            cambio.setInt(11, Nuevo.getCodigo());
            cambio.executeUpdate();
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }
    
    public List<Usuario> ListaPacientes(){
    Con = new Conexion();
    List<Usuario> ListaPacientes = new ArrayList<>();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM usuariosmedic WHERE Tipo='Paciente';");
            while(U.next()){  
                Usuario paciente;
                paciente= new Usuario(U.getInt (1),U.getString (2),U.getString (3),U.getString (4),U.getString (5),U.getString (6),U.getString (7), U.getString (8),U.getDate (9),U.getString (10),U.getDouble (11));
                ListaPacientes.add(paciente);
            }
                U.close();
                Con.CerrarConexiones();
                return ListaPacientes;            
        } catch (SQLException ex) {
        }
        return null;
    }
    //metodo que paga las consultas 
    public boolean Pagar(int IdPagado, double pago, double porcentaje,int IdConsulta){
        double cobroApp = (pago*porcentaje);
        double CobroFinal = (pago-cobroApp);
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            //Envia el pago al medico
            String Ssql = "UPDATE usuariosmedic SET Saldo=Saldo + ? WHERE IdUsuario=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDouble(1, CobroFinal);
            cambio.setInt(2, IdPagado);
            cambio.executeUpdate();
            //Envia el pago Al Admin
            Ssql = "UPDATE usuariosmedic SET Saldo=Saldo + ? WHERE Tipo=?";
            cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDouble(1, cobroApp);
            cambio.setString(2, "Admin");
            cambio.executeUpdate();
            //Para el reporte de administración y medico:
            GananciaGeneradaMedicosServicio Servicio = new GananciaGeneradaMedicosServicio();
            Servicio.IngresarGanancia(IdConsulta, cobroApp, CobroFinal);
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }       
    //metodo que paga las consultas 
    public boolean RecargaPaciente(int IdPagado, double monto){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            //Envia el pago al medico
            String Ssql = "UPDATE usuariosmedic SET Saldo=Saldo + ? WHERE IdUsuario=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDouble(1, monto);
            cambio.setInt(2, IdPagado);
            cambio.executeUpdate();
            //Reportes:
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(fechaHoraActual);
            Ssql = "Insert into recargaspacientes (Monto, HoraFecha, IdPaciente) values (?,?,?);";
            cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDouble(1, monto);
            cambio.setTimestamp(2, timestamp);
            cambio.setInt(3, IdPagado);
            cambio.executeUpdate();
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }
        //metodo que paga las consultas 
    public boolean PagarALaboratorio(int IdPagado, double pago, double porcentaje,int IdSolicitud,int IdExamen){
        double cobroApp = (pago*porcentaje);
        double CobroFinal = (pago-cobroApp);
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            //Envia el pago al medico
            String Ssql = "UPDATE usuariosmedic SET Saldo=Saldo + ? WHERE IdUsuario=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDouble(1, CobroFinal);
            cambio.setInt(2, IdPagado);
            cambio.executeUpdate();
            //Envia el pago Al Admin
            Ssql = "UPDATE usuariosmedic SET Saldo=Saldo + ? WHERE Tipo=?";
            cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDouble(1, cobroApp);
            cambio.setString(2, "Admin");
            cambio.executeUpdate();
            //Para el reporte de administración y Lab:
            GananciaGeneradaLabsServicio Servicio = new GananciaGeneradaLabsServicio();
            Servicio.IngresarGanancia(IdSolicitud, cobroApp, CobroFinal,IdExamen);
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }
    //metodo encargado de pagar al admin cada examen de solicitud
    public boolean PagarSolicitud(double pagoLab, double pagoAdmin, int IdPagado){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            //Envia el pago al medico
            String Ssql = "UPDATE usuariosmedic SET Saldo=Saldo + ? WHERE IdUsuario=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDouble(1, pagoLab);
            cambio.setInt(2, IdPagado);
            cambio.executeUpdate();
            //Envia el pago Al Admin
            Ssql = "UPDATE usuariosmedic SET Saldo=Saldo + ? WHERE Tipo=?";
            cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDouble(1, pagoAdmin);
            cambio.setString(2, "Admin");
            cambio.executeUpdate();
            Con.CerrarConexiones();
            return true; 
        }catch(SQLException e){
            return false;
        }
    }
    //Restar saldo al paciente
    public void RestarConsulta(int Paciente, double pago){
        try{
            Con = new Conexion();
            Con.IniciarConexion();
            //Envia el pago al medico
            String Ssql = "UPDATE usuariosmedic SET Saldo=Saldo - ? WHERE IdUsuario=?";
            PreparedStatement cambio = Con.getConexion().prepareStatement(Ssql);
            cambio.setDouble(1, pago);
            cambio.setInt(2, Paciente);
            cambio.executeUpdate();
        }catch(SQLException e){
        }
    }
    //metodo para obtener el saldo de un usuario:
    public double ObtenerSaldoPorId(int Id){
        Con = new Conexion();
        try {
            ResultSet U = Con.IniciarConexion().executeQuery("SELECT * FROM UsuariosMedic WHERE IdUsuario='"+Id+"';");
            double Saldo;
                if(U.next()){  
                    Saldo = U.getDouble(11);
                }else{
                    U.close();
                    Con.CerrarConexiones();
                    return -1;
                }
                U.close();
                Con.CerrarConexiones();
                return Saldo;            
        } catch (SQLException ex) {
        }
        return -2;
    }
    //metodo para crear un usuario nuevo:
    public boolean CrearUsuarioNuevo(Usuario user){
    Con = new Conexion();
        Con.IniciarConexion();
        PreparedStatement ps;
        String sql;
        try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] valorHashCalculado = digest.digest(user.getPassword().getBytes());
        String valorHashCalculadoHex = bytesToHex(valorHashCalculado);
            sql = "insert into UsuariosMedic (NombreUsuario, Username, Password, Direccion, CUI, Telefono,Email,FechaNacimiento,Tipo,Saldo) values (?,?,?,?,?,?,?,?,?,?);";
            Conn = Con.getConexion();
            ps = Conn.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getUserName());
            ps.setString(3, valorHashCalculadoHex);
            ps.setString(4, user.getDireccion());
            ps.setString(5, user.getCUI());
            ps.setString(6, user.getTelefono());
            ps.setString(7, user.getEmail());
            ps.setDate(8, user.getFechaNacimiento());
            ps.setString(9, user.getTipo());
            ps.setDouble(10, user.getSaldo());           
            ps.executeUpdate();          
            Con.CerrarConexiones();
            Conn.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("ya hay un usuario con ese id en el sistema");
        }catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }
        return false;
    }
    //metodo para confirmar contraseña encriptada
        public boolean ComprobarContraseña(String Usuario, String contra){
        Con = new Conexion();
        try{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        Rs = Con.IniciarConexion().executeQuery("select * from UsuariosMedic WHERE Username='"+Usuario+"';");
        if (Rs.next()) {
            String valorHashAlmacenado = Rs.getString("Password");
            byte[] valorHashCalculado = digest.digest(contra.getBytes());
            String valorHashCalculadoHex = bytesToHex(valorHashCalculado);
            return valorHashAlmacenado.equals(valorHashCalculadoHex);
        } else {
            return false;
        }
        }catch(SQLException e){
        }catch(NoSuchAlgorithmException ex){
        
        }
        return false;
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}
