import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../../Services/user';
import { ConsultaService } from '../../../../Services/Consultas';
@Component({
  selector: 'app-medico-menu-consultas',
  templateUrl: './medico-menu-consultas.component.html',
  styleUrls: ['./medico-menu-consultas.component.css']
})
export class MedicoMenuConsultasComponent {
  Consulta: any[] = []; 
  fechaSeleccionada: any;
  formularioVisible = false;
  datosSeleccionados: any;
  constructor(private http: HttpClient , private UserService: UserService, private ConsultasService: ConsultaService  ) { }
   
  //Metodo De inicio de tabla
  ngOnInit() : void  {
    this.ActualizarTablaHoy();
  }
  //Metodo get de la información en la tabla consultas pendientes hoy
  getConsultasHoy() {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorConsultas?accion=obtenerConsultasPENDIENTEShoy&IdMedico='+this.UserService.getId();
    return this.http.get<any[]>(url);
  }
  //metodo get para info según una fecha
  getConsultasPendientesFecha() {
      const fecha = this.fechaSeleccionada;
      const url = 'http://localhost:8080/Proyecto2Clinica/ControladorConsultas?accion=obtenerConsultasPENDIENTESFecha&IdMedico='+this.UserService.getId()+'&Fecha='+fecha;
      return this.http.get<any[]>(url);
  }
  //metodo para info de pendiente de revision
  getConsultasRevision() {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorConsultas?accion=obtenerConsultasREVISION&IdMedico='+this.UserService.getId();
    return this.http.get<any[]>(url);
  }
  mostrarFormulario(datos: any) {
    this.ConsultasService.setId(datos.IdConsultas); 
    this.formularioVisible = true;
  }
  ActualizarTablaHoy(){
    this.getConsultasHoy().subscribe(consultas => {
      this.Consulta = consultas;
    });
  }
  ActualizarTablaRevision(){
    this.getConsultasRevision ().subscribe(consultas => {
      this.Consulta = consultas;
    });
  }

  ActualizarTablaFecha(){
    if (this.fechaSeleccionada === undefined){
      alert("No ingresaste ninguna fecha!");
      this.ActualizarTablaHoy();
    }else{
      this.getConsultasPendientesFecha().subscribe(consultas => {
        this.Consulta = consultas;
      });
    }
    }
}
