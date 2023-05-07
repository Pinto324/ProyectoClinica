/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Laboratorios;

import BaseDeDatos.Laboratorios.TipoDeExamenDB;
import Objetos.Laboratorios.ExamenesLaboratorios;
import Objetos.Laboratorios.TipoDeExamen;
import java.util.List;

/**
 *
 * @author branp
 */
public class ExamenesServicio {
    private TipoDeExamenDB DB = new TipoDeExamenDB();

    public ExamenesServicio() {
    }
    public List<TipoDeExamen> ListaExamenes(){return DB.ListaDeExamenes();}
    public int BuscarIdPorNombre(String Nombre){return DB.BuscarPorUserName(Nombre);}
    public List<String> ListaDeExamenesPorId(int IdLab){return DB.ListaExamenesLab(IdLab);}
    public boolean ModificarPrecioExamen(int Id, double precio){return DB.ModificarPrecio(Id, precio);}
    public void CrearSolicitudDeExamen(ExamenesLaboratorios e){DB.CrearSolicitudDeExamenLab(e);}
    //RepetirExamen xd
    public boolean EvitarRepetirEspecialidad(ExamenesLaboratorios e){return DB.EvitarRepetirExamen(e);}
    
}
