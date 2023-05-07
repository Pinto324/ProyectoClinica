import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../../Services/user';
@Component({
  selector: 'app-menu-examenes',
  templateUrl: './menu-examenes.component.html',
  styleUrls: ['./menu-examenes.component.css']
})
export class MenuExamenesComponent {
  Examenes: any[] = [];
  examenesAgregados: any[] = [];
  selectedOption: any;
  inputValue: number = 0;
  constructor(private http: HttpClient , private UserService: UserService  ) { }
  //cargar examenes al select y tabla:
  ngOnInit() : void  {
    this.getExamenes().subscribe(examenes => {
      this.Examenes = examenes;
    })
    this.getExamenesAsignados().subscribe(info => {
      this.examenesAgregados = info;
      console.log(this.examenesAgregados);
    })
  }
  //metodo para conseguir los examenes:
  getExamenes() {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorDeExamenes?accion=obtenerExamenes';
    return this.http.get<any[]>(url);
  }
  //metodo para conseguir los examenes asignados:
  getExamenesAsignados() {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesLaboratorios?accion=obtenerExamenesAsignados&IdLab='+this.UserService.getId();
    return this.http.get<any[]>(url);
  }
  editarEspecialidad(id: number, precio: string) {
    // Aquí puedes hacer lo que necesites con el id y el precio
    const Id = id;
    const Precio = precio;
    const body = { Precio: precio };
    this.http.put(`http://localhost:8080/Proyecto2Clinica/ControladorExamenesLaboratorios?accion=ActualizarPrecio&IdExamen=${Id}&Precio=${Precio}`, body).subscribe(
      response => {
        alert('examen editado correctamente.');
        this.ngOnInit();
        // Actualizar la lista de especialidades agregadas
      },
      error => {
        if (error.status === 400) {
          alert('Error al editar el exámen: el ID ingresado no es válido.');
        } else {
          alert('Error al editar la exámen');
        }
      }
    );
  }

  //metodo para enviar solicitud de un nuevo examen:
  EnviarSolicitudExamen(){
    const precio = this.inputValue;
    const nombre = this.selectedOption;
    const IdMedico = this.UserService.getId();
    const body = { Precio: precio, Nombre: this.selectedOption};
    this.http.post(`http://localhost:8080/Proyecto2Clinica/ControladorExamenesLaboratorios?accion=CrearSolicitud&IdExamen=${nombre}&Precio=${precio}&IdMedico=${IdMedico}`, body).subscribe(
      response => {
        alert('Solicitud enviada correctamente.');
        this.ngOnInit();
        // Actualizar la lista de especialidades agregadas
      },
      error => {
        if (error.status === 400) {
          alert('No se encontro el Examen pedido');
        } else if(error.status === 409) {
          alert('No puedes pedir una solicitud de un Examen con la que ya cuentas o tienes solicitud pendiente');
        }
      }
    );
  }
}
