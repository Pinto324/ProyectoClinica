/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import BaseDeDatos.ConsultasDB;
import Objetos.ConsultasHija;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author branp
 */
public class ConsultasServicio {
    private ConsultasDB DB = new ConsultasDB();

    public ConsultasServicio() {
    }
    public List<ConsultasHija> ListaDeConsultasPendientesIdMedicoFecha(int id, String Fecha) throws ParseException{return DB.BuscarConsultasPendientesPorIdMedicoFecha(id, Fecha);}
    public List<ConsultasHija> ListaDeConsultasRevisionIdMedico(int id) throws ParseException{return DB.BuscarConsultasRevisionPorIdMedico(id);}
    public boolean ActualizarEstadoConsulta(int id, String estado){return DB.ModificarPrecio(id, estado);}
    public boolean FinalizarConsulta(int id, String Informe){return DB.FinalizarConsulta(id, Informe);}
    public List<ConsultasHija> ListaDeConsultasIdPaciente(int id) throws ParseException{return DB.BuscarConsultasIdPaciente(id);}
}
