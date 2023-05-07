import { Component, EventEmitter, Output  } from '@angular/core';
import { ConsultaService } from '../../../../../Services/Consultas';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ComponentCommunicationService } from './Comunicador';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-form-consulta',
  templateUrl: './form-consulta.component.html',
  styleUrls: ['./form-consulta.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class FormConsultaComponent {
  private subscription: Subscription;
  @Output() dialogoCerrado = new EventEmitter<void>();
  pendiente = false;
  revision = false;
  public show = false;
  datos: any;
  constructor(private http: HttpClient , private ConsultasService: ConsultaService ,private componentCommunicationService: ComponentCommunicationService ) {
    this.subscription = this.componentCommunicationService.closeSecondComponent$.subscribe(() => {
      this.show = false;
    });
  }
  cerrarDialogo() {
    this.dialogoCerrado.emit();
  }
  //metodo para cerrar el componente
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
  //Metodo De inicio de tabla
  ngOnInit() : void  {

  }
  //metodo para descargar los examenes:
  Descargar(){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesConsultas?accion=DescargarExamenes&id='+this.datos.IdConsultas;
    const headers = new HttpHeaders().set('Accept', 'application/zip');
    this.http.get(url, { headers, responseType: 'blob' }).subscribe((response) => {
      // Crear un objeto URL con el archivo ZIP
      const url = window.URL.createObjectURL(response);
      
      // Crear un elemento <a> para descargar el archivo ZIP
      const a = document.createElement('a');
      a.href = url;
      a.download = 'Examenes.zip';
      document.body.appendChild(a);
      a.click();
      
      // Limpiar el objeto URL y el elemento <a>
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
    });
  }
  showModalInicial(datos: any){
    this.datos = datos;
    console.log(datos);
    this.show = true;
    this.ManejarBotones();
  }
  ManejarBotones(){
    switch (this.datos.Estado) {
      case "PENDIENTE":
        this.pendiente = true;
        break;
      case "PENDIENTE_REVISION":
        this.revision = true;
        break;
      default:
    }
  }
  hideModal(){
    this.show = false;
  }

}
