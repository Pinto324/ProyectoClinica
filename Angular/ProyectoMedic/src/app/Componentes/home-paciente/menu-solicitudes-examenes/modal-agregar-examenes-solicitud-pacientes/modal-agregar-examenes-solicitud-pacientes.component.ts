import { Component, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../../../Services/user';
@Component({
  selector: 'app-modal-agregar-examenes-solicitud-pacientes',
  templateUrl: './modal-agregar-examenes-solicitud-pacientes.component.html',
  styleUrls: ['./modal-agregar-examenes-solicitud-pacientes.component.css']
})
export class ModalAgregarExamenesSolicitudPacientesComponent {
  @Output() cerrado = new EventEmitter();
  datos: any;
  protected IdSolicitud: string = "";
  protected proteccionExamen = false;
  protected mensaje = true;
  public chow = false;
  examenes: any;
  public selectedOption: any;
  protected arreglo : any[] = [];
  ExamenesAgregados: any;
  MensajeDeConfirmacion = false;
  constructor(private http: HttpClient, private UserService:UserService  ) { }
  ngOnInit() : void  {

  }
  Mostrar(datos: any){
    this.datos = datos;
    this.chow = true;
    this.subscribirse();
    this.CrearSolicitudIncompleta();
  }
  subscribirse(){
    this.getExamenes().subscribe(examen => {
      this.examenes = examen;
    });
    this.getExamenesAgregados().subscribe(examen => {
      this.ExamenesAgregados = examen;
      console.log(this.ExamenesAgregados);
    });
  }
  getExamenes() {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesLaboratorios?accion=obtenerExamenesDelLabParaSolicitud&IdLab='+this.datos.Id;
    return this.http.get<any[]>(url);
  }
  getExamenesAgregados(){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesSolicitudes?accion=obtenerExamenesSolicitud&IdSolicitud='+this.IdSolicitud;
    return this.http.get<any[]>(url);
  }

  //Crear la solicitud incompleta:
  CrearSolicitudIncompleta(){
    const Idlab = this.datos.Id;
    const idUsuario = this.UserService.getId();
    const body = {Idlab : this.datos.Id};
    const url = `http://localhost:8080/Proyecto2Clinica/ControladorSolicitudes?accion=CrearSolicitud&Estado=INCOMPLETO&Idlab=${Idlab}&idUsuario=${idUsuario}`;
    this.http.post(url, this.datos, {responseType: 'text'}).subscribe((respuesta: string) => {
      this.IdSolicitud = respuesta;
    });
  }
  AgregarExamenSeleccionado(){
    if(this.mensaje){
      alert('Recuerde que una vez agregue el examen se le debitará el dinero, así que este seguro de los examenes que agrega');
      this.mensaje=false;
    }else{
      this.arreglo = this.selectedOption.split(",");
      const IdPaciente = this.UserService.getId();
      const IdExamen = this.arreglo[0];
      const IdSolicitud = this.IdSolicitud;
      const Precio = this.arreglo[2];
      const body = {Nombre: this.arreglo};
      this.http.post(`http://localhost:8080/Proyecto2Clinica/ControladorExamenesSolicitudes?accion=subirSolicitudExamen&IdPaciente=${IdPaciente}&IdExamen=${IdExamen}&IdSolicitud=${IdSolicitud}&Precio=${Precio}`, body).subscribe(
        response => {
          alert('Se agrego el examen a la solicitud con exito');
          this.subscribirse();
          this.proteccionExamen = true;
          // Actualizar la lista de especialidades agregadas
        },
        error => {
          if (error.status === 400) {
            alert('No puedes repetir el mismo examen en una solicitud');
          } else if(error.status === 409) {
            alert('No cuentas con el dinero suficiente para pedir este examen');
          }
        }
      );
    }

  }
  Finalizar(){
    if(this.MensajeDeConfirmacion){
      const idSoli = this.IdSolicitud;
      const body = { idSoli: idSoli };
      this.http.put(`http://localhost:8080/Proyecto2Clinica/ControladorSolicitudes?accion=OficializarSolicitud&IdSolicitud=${idSoli}`, body).subscribe(
        response => {
          alert('Se envio la solicitud con exito');
          this.chow = false;
          this.cerrado.emit();
        },
        error => {
          if (error.status === 400) {
            alert('Error al actualizar la solicitud');
          } else {
            alert('Error al actualizar');
          }
        }
      );
    }else{
      alert("Recuerda que luego no podras agregar más examenes a la solicitud, si ya acabaste presiona finalizar otra vez");
      this.MensajeDeConfirmacion = true;
    }
  }
}

