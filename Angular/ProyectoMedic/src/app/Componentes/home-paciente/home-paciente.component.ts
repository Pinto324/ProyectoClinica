import { Component } from '@angular/core';

@Component({
  selector: 'app-home-paciente',
  templateUrl: './home-paciente.component.html',
  styleUrls: ['./home-paciente.component.css']
})
export class HomePacienteComponent {
  //Variables para la recarga
  protected Recarga : number = 0;
  protected MostrarRecarga = true;
  //variables para mostrar menus
  protected Reportes = false;
  protected ConsultaNueva = false;
  protected ConsultaPendiente = false;
  protected ExamenNuevo = false;
  protected ExamenPendiente = false;
  //Metodos para abrir menus
  abrirMenuReportes(){
    this.cerrarTodo();
    this.Reportes = true;
  }

  abrirMenuConsultaNueva(){
    this.cerrarTodo();
    this.ConsultaNueva = true;
  }
  abrirMenuConsultaPendiente(){
    this.cerrarTodo();
    this.ConsultaPendiente = true;
  }
  abrirMenuExamenNuevo(){
    this.cerrarTodo();
    this.ExamenNuevo = true;
  }
  abrirMenuExamenPendiente(){
    this.cerrarTodo();
    this.ExamenPendiente = true;
  }
  abrirRecarga(){
    this.cerrarTodo();
    this.MostrarRecarga = true;
  }
  //Cerrar todo alv
  cerrarTodo(){
    this.Reportes = false;
    this.ConsultaNueva = false;
    this.ConsultaPendiente = false;
    this.ExamenNuevo = false;
    this.ExamenPendiente = false;
    this.MostrarRecarga = false;
  }
  //metodo para recargar dinero
  Recargar(){

  }
}
