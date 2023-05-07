import { Component } from '@angular/core';

@Component({
  selector: 'app-home-laboratorios',
  templateUrl: './home-laboratorios.component.html',
  styleUrls: ['./home-laboratorios.component.css']
})
export class HomeLaboratoriosComponent {
  MostrarSolicitud = false;
  MostrarExamen = false;
  MostrarReportes = false;

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
