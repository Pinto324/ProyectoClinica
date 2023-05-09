import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../../Services/user';

@Component({
  selector: 'app-menu-consultas-pendientes',
  templateUrl: './menu-consultas-pendientes.component.html',
  styleUrls: ['./menu-consultas-pendientes.component.css']
})
export class MenuConsultasPendientesComponent {
  Consulta: any[] = []; 
  formularioVisible = false;
  Info: any = {};

  constructor(private http: HttpClient , private UserService: UserService) { }
  
  ngOnInit() {
    this.getConsultas().subscribe(consultas => {
      this.Info = consultas;
      this.llenarTabla();
    });
  }
  getConsultas() {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorConsultas?accion=ConsultasRevisionExamenPaciente&IdPaciente='+this.UserService.getId();
    return this.http.get<any[]>(url);
  }

  llenarTabla(){
    for (let i = 0; i < this.Info.length; i += 8) {
      this.Consulta.push({
        IdConsulta: this.Info[i],
        NombreDoc: this.Info[i + 1],
        Especialidad: this.Info[i + 2],
        precio: this.Info[i + 3],
        Fecha: this.Info[i + 4],
        Estado: this.Info[i + 5],
        Correo: this.Info[i + 6],
        Telefono: this.Info[i + 7]
      });
    }
  }
}
