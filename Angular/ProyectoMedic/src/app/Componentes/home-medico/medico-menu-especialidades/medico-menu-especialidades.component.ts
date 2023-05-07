import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../../Services/user';
@Component({
  selector: 'app-medico-menu-especialidades',
  templateUrl: './medico-menu-especialidades.component.html',
  styleUrls: ['./medico-menu-especialidades.component.css']
})
export class MedicoMenuEspecialidadesComponent implements OnInit { 
  especialidades: any[] = [];
  especialidadesAgregadas: any[] = [];
  public selectedOption: any;
  public inputValue: number = 0;
  constructor(private http: HttpClient , private UserService: UserService  ) { }
  //metodo para conseguir listado de especialidades

  ngOnInit() : void  {
    this.getEspecialidades().subscribe(especialidades => {
      this.especialidades = especialidades;
    });
    this.getEspecialidadesAgregadas().subscribe(especialidadesAgregadas => {
      this.especialidadesAgregadas = especialidadesAgregadas;
    })
  }
  
  getEspecialidades() {
    const url = 'http://localhost:8080/Proyecto2Clinica/Especialidades?accion=obtenerEspecialidades';
    return this.http.get<any[]>(url);
  }
  getEspecialidadesAgregadas() {
    const url = 'http://localhost:8080/Proyecto2Clinica/EspecialidadesMedico?accion=obtenerEspecialidadesAgregadas&IdMedico='+this.UserService.getId();
    return this.http.get<any[]>(url);
  }
  //Metodo que se encarga de editar el precio de una especialidad
  editarEspecialidad(especialidad:any, e : number) {
   const idEspecialidad = especialidad.IdEspecialidades;
    const precio = e;
    const body = { Precio: precio };
    this.http.put(`http://localhost:8080/Proyecto2Clinica/EspecialidadesMedico?accion=ActualizarPrecio&IdEM=${idEspecialidad}&Precio=${precio}`, body).subscribe(
      response => {
        console.log(response);
        alert('Especialidad editada correctamente.');
        this.ngOnInit();
        // Actualizar la lista de especialidades agregadas
      },
      error => {
        if (error.status === 400) {
          alert('Error al editar la especialidad: el ID ingresado no es válido.');
        } else {
          alert('Error al editar la especialidad');
        }
      }
    );
  }
  ////////////////////////////////////////////////////////////////
  //metodo para crear solicitud de especialidades
  EnviarSolicitudEspecialidad(){
    const precio = this.inputValue;
    const nombre = this.selectedOption;
    const IdMedico = this.UserService.getId();
    const body = { Precio: precio, Nombre: this.selectedOption};
    console.log(this.selectedOption, this.inputValue);
    console.log(body);
    this.http.post(`http://localhost:8080/Proyecto2Clinica/EspecialidadesMedico?accion=CrearSolicitud&Nombre=${nombre}&Precio=${precio}&IdMedico=${IdMedico}`, body).subscribe(
      response => {
        alert('Solicitud enviada correctamente.');
        this.ngOnInit();
        // Actualizar la lista de especialidades agregadas
      },
      error => {
        if (error.status === 400) {
          alert('No se encontro la especialidad pedida');
        } else if(error.status === 409) {
          alert('No puedes pedir una solicitud de una especialidad con la que ya cuentas o tienes solicitud pendiente');
        }
      }
    );
  }
}
