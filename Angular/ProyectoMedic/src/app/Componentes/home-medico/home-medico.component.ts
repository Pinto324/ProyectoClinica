import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from '../../../Services/user';
@Component({
  selector: 'app-home-medico',
  templateUrl: './home-medico.component.html',
  styleUrls: ['./home-medico.component.css']
})
export class HomeMedicoComponent {
  private url = 'http://localhost:8080/Proyecto2Clinica/EspecialidadesMedico';
  protected NoHorarios=false;
  protected NoEspecialidades=false;
  protected Horario: any[] = [];
  protected Especialidad: any[] = [];
  protected EnvioEspecialidad = true;
  protected NombreEspe: String = "";
  protected Descripcion: String = "";
  mostrarFormularioEspecialidad = false;
  mostrarFormularioHorario = false;
  mostrarFormularioConsulta = false;
  mostrarFormularioHistorialMedico = false;
  mostrarMenuReportes = false;
  constructor(private http: HttpClient, private UserService: UserService) { }

  obtenerDatos(parametro: any): Observable<any> {
    const options = {
      params: {
        miParametro: parametro
      }
    };
    return this.http.get<any>(this.url, options);
  }

  ngOnInit() {
    this.getHistorialPaciente().subscribe(consultas => {
      this.Horario = consultas;
      this.getEspecialidadesAgregadas().subscribe(especialidadesAgregadas => {
        this.Especialidad = especialidadesAgregadas;
        this.ComprobarPrimerInicio()
      })
    });
  }
    //metodo get las consultas para el historial
    getHistorialPaciente(){
      const url = 'http://localhost:8080/Proyecto2Clinica/ControladorHorario?accion=obtenerHorarioId&id='+this.UserService.getId();
      return this.http.get<any[]>(url);
    }
    getEspecialidadesAgregadas() {
      const url = 'http://localhost:8080/Proyecto2Clinica/EspecialidadesMedico?accion=obtenerEspecialidadesAgregadasMedicos&IdMedico='+this.UserService.getId();
      return this.http.get<any[]>(url);
    }
//metodo para enviar nuevo examen
EnviarSoliExamen(){
  if(this.comprobarVacio()){
    const nombre = this.NombreEspe;
    const Descripcion = this.Descripcion;
    const body = { nombre: nombre, Descripcion:Descripcion};
    this.http.post(`http://localhost:8080/Proyecto2Clinica/Especialidades?accion=EnviarSolicitudNuevaEspe&nombre=${nombre}&Descripcion=${Descripcion}`, body).subscribe(
      response => {
        alert('Se envio la solicitud de la especialidad.');
        this.NombreEspe="";
        this.Descripcion="";
      },
      error => {
        if (error.status === 400) {
          alert('Error al  confirmar el nuevo especialidad.');
        } else {
          alert('Error al  confirmar el nuevo especialidad');
        }
      }
    );
  }
}




//Utilidades:
  abrirFormularioEspecialidad() { this.cerrarFormularios(); this.mostrarFormularioEspecialidad = true;}
  abrirEnviarExamen() { this.cerrarFormularios(); this.EnvioEspecialidad=true;}
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
    this.EnvioEspecialidad = false;
  }

  comprobarVacio():boolean{
    if(!(this.NombreEspe==""||this.Descripcion=="")){
      return true;
    }else{
      alert("debes llenar los campos para mandar la solicitud");
      return false;
    }
  }
  ComprobarPrimerInicio(){
    if (Array.isArray(this.Horario) && this.Horario.length > 0) {
        this.NoHorarios = true;
      if (Array.isArray(this.Especialidad) && this.Especialidad.length > 0) {
        this.NoEspecialidades = true;
      } else {
       alert("Aún no cuentas con especialidades, recuerda usar el menú de especialidades para completar tu información");
      }
    } else {
      this.EnvioEspecialidad = false;
      alert("pon horarios y especialidades para desbloquear todos los menús, cierra sesión y vuelve a logear cuando hayas completado la información");
    }
  }
  PrimerInicio():boolean{
    if(this.NoHorarios&&this.NoEspecialidades){
      return true;
    }else{
      return false;
    }
  }
}
