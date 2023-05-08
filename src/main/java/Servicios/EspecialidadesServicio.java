/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import BaseDeDatos.EspecialidadesDB;
import BaseDeDatos.EspecialidadesMedicosDB;
import Objetos.EspecialidadHijaMedicos;
import Objetos.Especialidades;
import Objetos.Medicos.EspecialidadesMedicos;
import java.util.List;

/**
 *
 * @author branp
 */
public class EspecialidadesServicio {
    private final EspecialidadesDB DB = new EspecialidadesDB();
    private final EspecialidadesMedicosDB DBEM = new EspecialidadesMedicosDB();
    public EspecialidadesServicio() {
    }
     public List<Especialidades> ListaEspecialidades(){ return DB.ListaDeEspecialidades();}
     public List<EspecialidadHijaMedicos> ListaEspecialidadesPorId(int id){return DBEM.ListaDeEspecialidadesPorID(id);}
     public List<EspecialidadHijaMedicos> ListaEspecialidadesPorIdyEstado(int id, String Estado){return DBEM.ListaDeEspecialidadesPorIDyEstado(id, Estado);}
     public boolean ActualizarPrecioPorId(int id, double precio){return DBEM.ModificarPrecio(id, precio);}
     public int BuscarIdEspecialidadPorNombre(String Nombre){return DB.BuscarIdEspecialidadPorNombre(Nombre);}
     public boolean EvitarRepetirEspecialidad(EspecialidadesMedicos e){return DBEM.EvitarRepetirEspecialidad(e);}
    public void CrearEspecialidadMedico(EspecialidadesMedicos e){DBEM.CrearUsuario(e);}
    public List<String> ListaEspecialidadesYDoctores(String Estado){return DBEM.ListaEspecialidadesYDoctores(Estado);}
}
