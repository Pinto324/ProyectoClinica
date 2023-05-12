/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BaseDeDatos.Reportes.GananciaGeneradaMedicosDB;
import Objetos.Consultas;
import Objetos.Especialidades;
import Objetos.ExamenesSolicitadosConsultas;
import Objetos.Laboratorios.ExamenesLaboratorios;
import Objetos.Laboratorios.SolicitudDeLaboratorio;
import Objetos.Laboratorios.TipoDeExamen;
import Objetos.Medicos.EspecialidadesMedicos;
import Objetos.Medicos.Horario;
import Objetos.Usuario;
import Servicios.ConsultasServicio;
import Servicios.EspecialidadesServicio;
import Servicios.ExamenesConsultasServicio;
import Servicios.Laboratorios.ExamenesDeUnaSolicitudServicio;
import Servicios.Laboratorios.ExamenesServicio;
import Servicios.Laboratorios.SolicitudesServicio;
import Servicios.Medicos.HorariosServicio;
import Servicios.PorcentajeServicio;
import Servicios.Reportes.GananciaGeneradaLabsServicio;
import Servicios.UsuarioServicio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
/**
 *
 * @author branp
 */
@WebServlet(name = "ControladorCargaDeDatos", urlPatterns = {"/ControladorCargaDeDatos"})
public class ControladorCargaDeDatos extends HttpServlet {
    private static final SimpleDateFormat FormatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    
    
    
    
        //POST
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");        
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }
    String jsonString = sb.toString();
        JSONObject objetoJson = new JSONObject(jsonString.substring(jsonString.indexOf("{"), jsonString.length()));
  //MANEJO DE USUARIOS:
  //ADMINS:
   try{
    JSONObject admin = objetoJson.getJSONObject("admin");
    int id = admin.getInt("id");
    String nombre = admin.getString("nombre");
    String username = admin.getString("username");
    String password = admin.getString("password");
    String email = admin.getString("email");
    String fechaNac = admin.getString("fecha_nacimiento");
    double saldo = admin.getDouble("saldo");
    java.sql.Date sqlDate; 
      try {
          // hacer algo con los datos del pedido
         sqlDate = new java.sql.Date(FormatoFecha.parse(fechaNac).getTime());
          } catch (ParseException ex) {
          sqlDate = null;
      }     
    //Subirlo
    UsuarioServicio ManejadorUsuario = new UsuarioServicio();
    Usuario NU = new Usuario(id,nombre,username,password,"","","",email,sqlDate,"Admin",saldo);
    ManejadorUsuario.CrearUsuario(NU);
  }catch(JSONException e) {System.out.println("No hay admins");}
    //////especialidades
  try{
    JSONArray  especialidads = objetoJson.getJSONArray("especialidades");
    for (int i = 0; i < especialidads.length(); i++) {
    JSONObject U = especialidads.getJSONObject(i);
    int id = U.getInt("id");
    String nombre = U.getString("nombre");
    String descripcion = U.getString("descripcion");
    //Subirlo
    Especialidades NU = new Especialidades(id,nombre,descripcion);
    EspecialidadesServicio manejadorEspecialidad = new EspecialidadesServicio();
    manejadorEspecialidad.CrearEspecialidadCarga(NU);
    PorcentajeServicio Servicio = new PorcentajeServicio();
    Servicio.CargaDeDatosEspecialidad(id);
    }
  }catch(JSONException e){System.out.println("No hay Especialidades"); }
      //Tipo de Examenes
  try{
  JSONArray tipos_examenes = objetoJson.getJSONArray("tipos_examenes");
    for (int i = 0; i < tipos_examenes.length(); i++) {
    JSONObject examenes = tipos_examenes.getJSONObject(i);
    int id = examenes.getInt("id");
    String nombre = examenes.getString("nombre");
    String descripcion = examenes.getString("descripcion");
    //Subirlo
    TipoDeExamen NU = new TipoDeExamen(id,nombre,descripcion);
    ExamenesServicio ManejadorExamenes = new ExamenesServicio();
    ManejadorExamenes.CrearExamenCarga(NU);
  }
  }catch(JSONException e)
  {System.out.println("No hay Tipos de Examenes");}
   //Pacientes
  try{
  JSONArray pacientes = objetoJson.getJSONArray("pacientes");
    for (int i = 0; i < pacientes.length(); i++) {
    JSONObject pac = pacientes.getJSONObject(i);
    int id = pac.getInt("id");
    String nombre = pac.getString("nombre");
    String username = pac.getString("username");
    String password = pac.getString("password");
    String direccion = pac.getString("direccion");
    String cui = pac.getString("cui");
    String telefono = pac.getString("telefono");
    String email = pac.getString("email");
    String fechaNac = pac.getString("fecha_nacimiento");
    double saldo = pac.getDouble("saldo");
        java.sql.Date sqlDate; 
      try {
          // hacer algo con los datos del pedido
         sqlDate = new java.sql.Date(FormatoFecha.parse(fechaNac).getTime());
          } catch (ParseException ex) {
          sqlDate = null;
      }   
    //Subirlo
    UsuarioServicio ManejadorUsuario = new UsuarioServicio();
    Usuario NU = new Usuario(id,nombre,username,password,direccion,cui,telefono,email,sqlDate,"Paciente",saldo);
    ManejadorUsuario.CrearUsuario(NU);
  }
      }catch(JSONException e)
  {
      System.out.println("No hay Pacientes");
  }
   //Medicos
  try{
    JSONArray medicos = objetoJson.getJSONArray("medicos");
      for (int i = 0; i < medicos.length(); i++) {
      JSONObject medic = medicos.getJSONObject(i);
      int id = medic.getInt("id");
      String nombre = medic.getString("nombre");
      String username = medic.getString("username");
      String password = medic.getString("password");
      String direccion = medic.getString("direccion");
      String cui = medic.getString("cui");
      String telefono = medic.getString("telefono");
      String email = medic.getString("email");
      String fechaNac = medic.getString("fecha_nacimiento");
      double saldo = medic.getDouble("saldo");
      java.sql.Date sqlDate; 
      try {sqlDate = new java.sql.Date(FormatoFecha.parse(fechaNac).getTime());} catch (ParseException ex) {sqlDate = null;}  
          //Subirlo
        UsuarioServicio ManejadorUsuario = new UsuarioServicio();
        Usuario NU = new Usuario(id,nombre,username,password,direccion,cui,telefono,email,sqlDate,"Medico",saldo);
        ManejadorUsuario.CrearUsuario(NU);
      JSONArray horarios = medic.getJSONArray("horarios");
      for (int j = 0; j < horarios.length(); j++) {
          String HoraJunta = horarios.getString(j);
String[] horas = HoraJunta.split("-");
if (horas.length != 2) {
    // manejar error de formato inválido aquí
}
LocalTime horaInicio = LocalTime.parse(horas[0].trim(), DateTimeFormatter.ofPattern("H:mm"));
LocalTime horaFin = LocalTime.parse(horas[1].trim(), DateTimeFormatter.ofPattern("H:mm"));
Horario H = new Horario(horaInicio, horaFin);
HorariosServicio Servicio = new HorariosServicio();
Servicio.CrearHorario(H, id);
      }
      JSONArray especialidades = medic.getJSONArray("especialidades");
      for (int j = 0; j < especialidades.length(); j++) {
           JSONObject Espe = especialidades.getJSONObject(j);
           int idespecialidad = Espe.getInt("id");
           double precioespecialidad = Espe.getDouble("precio");
           EspecialidadesMedicos em = new EspecialidadesMedicos(id,idespecialidad,precioespecialidad);
           EspecialidadesServicio serv = new EspecialidadesServicio();
           serv.CrearEspecialidadMedicoActiva(em);
      }
    }
        }catch(JSONException e)
    {
        System.out.println("No hay Medicos");
    }
     //Laboratorios
    try{
      JSONArray Laboratorios = objetoJson.getJSONArray("laboratorios");
        for (int i = 0; i < Laboratorios.length(); i++) {
        JSONObject lab = Laboratorios.getJSONObject(i);
        int id = lab.getInt("id");
        String nombre = lab.getString("nombre");
        String username = lab.getString("username");
        String password = lab.getString("password");
        String direccion = lab.getString("direccion");
        String cui = lab.getString("cui");
        String telefono = lab.getString("telefono");
        String email = lab.getString("email");
        String fecha_fundacion = lab.getString("fecha_fundacion");
        double saldo = lab.getDouble("saldo");
            java.sql.Date sqlDate; 
      try {sqlDate = new java.sql.Date(FormatoFecha.parse(fecha_fundacion).getTime());} catch (ParseException ex) {sqlDate = null;}  
          //Subirlo
        UsuarioServicio ManejadorUsuario = new UsuarioServicio();
        Usuario NU = new Usuario(id,nombre,username,password,direccion,cui,telefono,email,sqlDate,"Laboratorio",saldo);
        ManejadorUsuario.CrearUsuario(NU);
        JSONArray examenes = lab.getJSONArray("examenes");
        for (int j = 0; j < examenes.length(); j++) {
            JSONObject examen = examenes.getJSONObject(j);
             int idexamen = examen.getInt("id");
             double precioexamen = examen.getDouble("precio");
             ExamenesLaboratorios Elab = new ExamenesLaboratorios(id,idexamen,precioexamen,"Activa");
             ExamenesServicio serv = new ExamenesServicio();
             serv.AsignarExamenLaboratorioCarga(Elab);
        }
      }
          }catch(JSONException e)
      {
          System.out.println("No hay Laboratorios");
      }
      //Consultas
    try{
      JSONArray consultas = objetoJson.getJSONArray("consultas");
        for (int i = 0; i < consultas.length(); i++) {
        JSONObject con = consultas.getJSONObject(i);
        int id = con.getInt("id");
        int paciente = con.getInt("paciente");
        int medico = con.getInt("medico");
        int especialidad = con.getInt("especialidad");
        double porcentaje_aplicacion = con.getDouble("porcentaje_aplicacion");
        JSONArray examenes = con.getJSONArray("examenes_solicitados");
        for (int j = 0; j < examenes.length(); j++) {
            JSONObject examen = examenes.getJSONObject(j);
             int idexamen = examen.getInt("id");
             ExamenesSolicitadosConsultas solEx = new ExamenesSolicitadosConsultas(id,idexamen,null);
             ExamenesConsultasServicio serv = new ExamenesConsultasServicio();
             serv.CrearExamenSolicitado(solEx);
        }
        String fecha_creacion = con.getString("fecha_creacion");
        String fecha_agendada = con.getString("fecha_agendada");
        double precio = con.getDouble("precio");
        String informe_finalizacion = con.getString("informe_finalizacion");
        String estado = con.getString("estado");
       java.sql.Date sqlDateCreacion; 
       SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm");
       Timestamp timestamp = null;
      try {
          sqlDateCreacion = new java.sql.Date((FormatoFecha.parse(fecha_creacion).getTime()));  
          Date fecha = formatoFecha.parse(fecha_agendada);
        timestamp = new Timestamp(fecha.getTime());
        } catch (ParseException ex) { System.out.println(ex);sqlDateCreacion = null;}        
        Consultas NU = new Consultas(id,paciente,medico,especialidad,porcentaje_aplicacion,sqlDateCreacion,timestamp,precio,informe_finalizacion,estado);
        ConsultasServicio cs = new ConsultasServicio();
        cs.IngresarConsultaCarga(NU, timestamp);
        GananciaGeneradaMedicosDB Reporte = new GananciaGeneradaMedicosDB();
        double GananciaAPP = precio*porcentaje_aplicacion;
        double gananciamedico = precio-GananciaAPP;
        Reporte.SubirDatosCarga(id, GananciaAPP, gananciamedico, sqlDateCreacion);
      }
          }catch(JSONException e)
      {
          System.out.println("No hay consultas");
      }
  
       //Solicitudes de Examenes
    try{
      JSONArray solicitudes = objetoJson.getJSONArray("solicitudes");
        for (int i = 0; i < solicitudes.length(); i++) {
        JSONObject sol = solicitudes.getJSONObject(i);
        int id = sol.getInt("id");
        int paciente = sol.getInt("paciente");
        int laboratorio = sol.getInt("laboratorio");
        double porcentaje_aplicacion = sol.getDouble("porcentaje_aplicacion");
        String fecha_solicitado = sol.getString("fecha_solicitado");
        String fecha_finalizado = sol.getString("fecha_finalizado");
        java.sql.Date sqlDateCreacion; 
        java.sql.Date sqlDateFinalizado; 
        try {
          sqlDateCreacion = new java.sql.Date((FormatoFecha.parse(fecha_solicitado).getTime()));
          sqlDateFinalizado = new java.sql.Date((FormatoFecha.parse(fecha_finalizado).getTime()));
           } catch (ParseException ex) {sqlDateCreacion = null;sqlDateFinalizado=null;}       
        JSONArray examenes = sol.getJSONArray("examenes");
        for (int j = 0; j < examenes.length(); j++) {
            JSONObject examen = examenes.getJSONObject(j);
             int idexamen = examen.getInt("id");
             double precioexamen = examen.getDouble("precio");
             ExamenesDeUnaSolicitudServicio servicio = new ExamenesDeUnaSolicitudServicio();
             servicio.CrearExamenSolicitud(id, idexamen);
             GananciaGeneradaLabsServicio servicioReporte = new GananciaGeneradaLabsServicio();
             double gananciaApp = precioexamen*porcentaje_aplicacion;
             double gananciaLab = precioexamen-gananciaApp;
             servicioReporte.SubirDatoCargaDeDatos(id, gananciaLab, gananciaApp, idexamen, sqlDateCreacion);
        }
        String estado_solicitud = sol.getString("estado_solicitud");
        SolicitudDeLaboratorio NU = new SolicitudDeLaboratorio(id,paciente,laboratorio,porcentaje_aplicacion,sqlDateCreacion,sqlDateFinalizado,estado_solicitud);
        SolicitudesServicio servicio = new SolicitudesServicio();
        servicio.IngresarSolicitudCarga(NU);
      }
          }catch(JSONException e)
      {
          System.out.println("No hay Solicitudes");
      }
    }
    
        @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }
}

