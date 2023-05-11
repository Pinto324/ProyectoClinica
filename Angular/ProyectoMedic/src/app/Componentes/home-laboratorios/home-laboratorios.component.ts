import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-home-laboratorios',
  templateUrl: './home-laboratorios.component.html',
  styleUrls: ['./home-laboratorios.component.css']
})
export class HomeLaboratoriosComponent {
  //Variables para carga de datos:
  protected MostrarCargaDeDatos = true;

  MostrarSolicitud = false;
  MostrarExamen = false;
  MostrarReportes = false;
  constructor(private http: HttpClient) {}

//metodo para el menu de reportes
  abrirMenuReportes(){
    this.CerrarMenus();
    this.MostrarReportes=true;
  }
  //Metodos para abrir el menu de solicitudes
  abrirFormularioSolicitud(){
    this.CerrarMenus();
    this.MostrarSolicitud=true;
  }
  //metodos para abrir el menu de examenes:
  abrirFormularioExamen(){
    this.CerrarMenus();
    this.MostrarExamen = true;
  }
  //cerrar todo alv
  CerrarMenus(){
    this.MostrarSolicitud = false;
    this.MostrarExamen = false;
    this.MostrarReportes = false;
  }
}
