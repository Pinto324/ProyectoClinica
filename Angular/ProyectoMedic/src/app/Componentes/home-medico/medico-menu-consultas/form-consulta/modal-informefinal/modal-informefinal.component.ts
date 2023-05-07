import { Component, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ComponentCommunicationService } from '../../form-consulta/Comunicador';


@Component({
  selector: 'app-modal-informefinal',
  templateUrl: './modal-informefinal.component.html',
  styleUrls: ['./modal-informefinal.component.css']
})

export class ModalInformefinalComponent {
  @Output() cerrado = new EventEmitter<void>();
  mostrar = false;
  datos : any;
  Informe: String = "";
  MensajeSeguro = false;
  
      constructor(private http: HttpClient, private componentCommunicationService: ComponentCommunicationService) { }

    //Metodo que finaliza la consulta:
    FinalizarConsulta(){
      if(this.Informe === ""){
        alert("Debe de llenar el informe para poder mandarlo");
      }else{
        if(this.MensajeSeguro){
          alert("Asegurese de que el informe final está correcto, no podrá cambiarlo más tarde, vuelva a presionar el botón para mandarlo");
        }else{
          this.putFinalizar();
        }
      }
    }
    //metodo put que cambia el estado a finalizado con el informe:
    async putFinalizar() {
      const IdConsulta = this.datos.IdConsultas;
      const Informe = this.Informe;
      const body = { Informe: Informe };
      console.log(IdConsulta);
    
      try {
        const response = await this.http.put<any>(`http://localhost:8080/Proyecto2Clinica/ControladorConsultas?accion=FinalizarConsulta&IdConsulta=${IdConsulta}&Informe=${Informe}`, body).toPromise();
        alert('Consulta finalizada correctamente.');
        this.AgregarDineroMedico();
        this.componentCommunicationService.closeSecondComponent();
        this.cerrado.emit();
        this.CerrarModal();
      } catch (error) {
        if (error === 400) {
          alert('Error al finalizar la consulta, el ID ingresado no es válido.');
        } else {
          alert('Error al finalizar la consulta');
        }
      }
    }
     //metodo para agregar el dinero
     AgregarDineroMedico() {
      const Porcentaje = this.datos.Porcentaje;
       const precio = this.datos.Precio;
       const IdMedico = this.datos.IdMedico;
       const IdConsulta = this.datos.IdConsultas;
       const body = { precio: precio };
       this.http.put(`http://localhost:8080/Proyecto2Clinica/Usuario?accion=DebitarConsulta&Porcentaje=${Porcentaje}&precio=${precio}&IdMedico=${IdMedico}&IdConsulta=${IdConsulta}`, body).subscribe(
         response => {
           alert('Se agrego el dinero de la consulta a su cuenta');
         },
         error => {
           if (error.status === 400) {
             alert('Error al agregar el dinero, el ID ingresado no es válido.');
           } else {
             alert('Error al agregar el dinero pongase en contacto con soporte tecnico');
           }
         }
       );
     }
     CerrarModal(){
      this.mostrar = false;
     }

     EncenderModal(datos: any){
      this.datos = datos;
      this.mostrar = true;
     }
}
