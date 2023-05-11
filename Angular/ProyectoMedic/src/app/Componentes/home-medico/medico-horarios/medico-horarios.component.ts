import { Component } from '@angular/core';
import { UserService } from '../../../../Services/user';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-medico-horarios',
  templateUrl: './medico-horarios.component.html',
  styleUrls: ['./medico-horarios.component.css']
})
export class MedicoHorariosComponent {
  horaEntrada: String="";
  horaSalida: String="";
  horarios: String[] = [];
  numeros = Array.from({length: 24}, (_, i) => ('0' + (i)).slice(-2));
  constructor(private http: HttpClient, private UserService: UserService){}

  ngOnInit() : void  {
    this.getExamenes().subscribe(horarios => {
      this.horarios = horarios;
    })
  }

  getExamenes(){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorHorario?accion=obtenerHorarioId&id='+this.UserService.getId();
    return this.http.get<any[]>(url);
  }
  ////////////////////////////////
  //Metodo para mostrar horarios en la tabla
  mostrarHorarios(){
    this.getExamenes().subscribe(especialidadesAgregadas => {
      this.horarios = especialidadesAgregadas;
    })
  }
    //metodo para crear Horarios
    CrearHorarioNuevo(){
      if(this.horaEntrada === ""||this.horaSalida === ""){
        alert("llena ambos campos para continuar");
      }else if(this.horaEntrada === this.horaSalida){
        alert("Horario no valido");
      }
      else{
        const HoraEntrada = this.horaEntrada;
        const HoraSalida = this.horaSalida;
        const IdMedico = this.UserService.getId();
        const body = { HoraEntrada: HoraEntrada, HoraSalida: HoraSalida, IdMedico: IdMedico};
        this.http.post(`http://localhost:8080/Proyecto2Clinica/ControladorHorario?accion=CrearHorario&HEntrada=${HoraEntrada}:00&HSalida=${HoraSalida}:00&IdMedico=${IdMedico}`, body).subscribe(
          response => {
            alert('Horario creado existosamente.');
          },
          error => {
              alert('No se puede agregar el horario, se solapa con otro horario existente.');
          }
        );
        this.ngOnInit();
      }
    }
}
