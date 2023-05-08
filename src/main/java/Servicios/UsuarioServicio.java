/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import BaseDeDatos.UsuarioDB;
import Objetos.Usuario;
import java.util.List;

/**
 *
 * @author branp
 */
public class UsuarioServicio {
    private UsuarioDB DB = new UsuarioDB();

    public UsuarioServicio() {
    }
    public Usuario BuscarUsuarioPorUsername(String UsuarioBuscar){ return DB.BuscarPorUserName(UsuarioBuscar);}
    public Usuario BuscarUsuarioPorId(int UsuarioBuscar){ return DB.BuscarPorCodigo(UsuarioBuscar);}
    public void CrearUsuario(Usuario UsuarioBuscar){DB.CrearUsuario(UsuarioBuscar);}
    public void ActualizarUsuario(Usuario u){DB.ModificarDatos(u);}
    public boolean ComprobarSiExisteUsuario(int cod){return (DB.BuscarPorCodigo(cod)==null);}
    public boolean PagarAlDoyAdmin(int id, double precio, double porcentaje, int IdConsulta){return DB.Pagar(id,precio,porcentaje,IdConsulta);}
    public List<Usuario> obtenerPacientes(){return DB.ListaPacientes();}
    public double ObtenerSaldo(int Id){return DB.ObtenerSaldoPorId(Id);}
    public boolean PagarSolicitud(double pagoLab, double pagoAdmin, int IdPagado){return DB.PagarSolicitud(pagoLab, pagoAdmin, IdPagado);}
    public void RestarSaldoPaciente(int IdPaciente, double Saldo){DB.RestarConsulta(IdPaciente,Saldo);}
}
