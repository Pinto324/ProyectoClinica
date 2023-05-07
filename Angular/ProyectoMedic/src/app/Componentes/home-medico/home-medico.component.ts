import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-home-medico',
  templateUrl: './home-medico.component.html',
  styleUrls: ['./home-medico.component.css']
})
export class HomeMedicoComponent {
  private url = 'http://localhost:8080/Proyecto2Clinica/EspecialidadesMedico';

  constructor(private http: HttpClient) { }

  obtenerDatos(parametro: any): Observable<any> {
    const options = {
      params: {
        miParametro: parametro
      }
    };
    return this.http.get<any>(this.url, options);
  }
  mostrarFormularioEspecialidad = false;
  mostrarFormularioHorario = false;
  mostrarFormularioConsulta = false;
  mostrarFormularioHistorialMedico = false;
  mostrarMenuReportes = false;

  abrirFormularioEspecialidad() { this.cerrarFormularios(); this.mostrarFormularioEspecialidad = true;}
  abrirFormularioHorario() {this.cerrarFormularios(); this.mostrarFormularioHorario = true;}
  abrirFormularioConsulta() {this.cerrarFormularios(); this.mostrarFormularioConsulta = true;}
  abrirFormularioHistorialMedico() {this.cerrarFormularios(); this.mostrarFormularioHistorialMedico = true;}
  abrirMenuReportes() {this.cerrarFormularios(); this.mostrarMenuReportes = true;}
  cerrarFormularios(){
    this.mostrarFormularioEspecialidad = false;
    this.mostrarFormularioHorario = false;
    this.mostrarFormularioConsulta = false;
    this.mostrarFormularioHistorialMedico = false;
    this.mostrarMenuReportes = false;
  }
}
