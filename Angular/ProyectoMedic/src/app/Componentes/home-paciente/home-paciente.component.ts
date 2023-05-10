import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../Services/user';
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
  constructor(private http: HttpClient , private UserService: UserService) { }
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
    if(this.Recarga==0){
      alert('ponga un valor valido para recargar');
    }else{
    const IdPaciente = this.UserService.getId();
     const Monto = this.Recarga;
     const body = { Precio: Monto };
     this.http.put(`http://localhost:8080/Proyecto2Clinica/Usuario?accion=RecargaPaciente&IdPaciente=${IdPaciente}&Monto=${Monto}`, body).subscribe(
       response => {
        this.Recarga = 0;
         alert('Recarga hecha correctamente');
       },
       error => {
         if (error.status === 400) {
           alert('Error al recargar pongase en contacto con el soporte');
         } else {
           alert('Error');
         }
       }
     );
    }
  }
}
