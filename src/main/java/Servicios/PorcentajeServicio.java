/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import BaseDeDatos.PorcentajeDB;
import java.util.List;

/**
 *
 * @author branp
 */
public class PorcentajeServicio {
    private final PorcentajeDB DB = new PorcentajeDB();

    public PorcentajeServicio() {
    }
    
    public List<String> PorcentajeExamenes(){return DB.PorcentajeExamenes();}
    public List<String> PorcentajeEspecialidades(){return DB.PorcentajeEspecialidades();}
    
    public boolean ModificarPorcentajeEspecialidad(int Id, double Porcentaje){return DB.IngresarPorcentajeEspecialidades(Id, Porcentaje);}
    public boolean ModificarPorcentaje(int Id, double Porcentaje){return DB.IngresarPorcentajeExamenes(Id, Porcentaje);}

    public void CargaDeDatosExamen(int id){DB.CargaDeDatosExamen(id);}
    public void CargaDeDatosEspecialidad(int id){DB.CargaDeDatosEspecialidad(id);}
}
