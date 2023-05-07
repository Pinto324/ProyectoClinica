import { Component, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-modal-agregar-examenes-consulta',
  templateUrl: './modal-agregar-examenes-consulta.component.html',
  styleUrls: ['./modal-agregar-examenes-consulta.component.css']
})
export class ModalAgregarExamenesConsultaComponent {
  @Output() cerrado = new EventEmitter();
  datos: any;
  public chow = false;
  examenes: any;
  public selectedOption: any;
  ExamenesAgregados: any;
  MensajeDeConfirmacion = false;
  constructor(private http: HttpClient  ) { }
  ngOnInit() : void  {

  }
  Mostrar(datos: any){
    this.datos = datos;
    this.chow = true;
    this.subscribirse();

  }
  subscribirse(){
    this.getExamenes().subscribe(examen => {
      this.examenes = examen;
    });
    this.getExamenesAgregados().subscribe(examen => {
      this.ExamenesAgregados = examen;
    })
  }
  getExamenes() {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorTipoDeExamenes?accion=obtenerExamenes';
    return this.http.get<any[]>(url);
  }
  getExamenesAgregados(){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesConsultas?accion=obtenerExamenesAgregados&id='+this.datos.IdConsultas;
    return this.http.get<any[]>(url);
  }
  AgregarExamenSeleccionado(){
    const nombre = this.selectedOption;
    const body = {Nombre: this.selectedOption};
    this.http.post(`http://localhost:8080/Proyecto2Clinica/ControladorExamenesConsultas?accion=CrearExamenAsignado&NombreExamen=${nombre}&IdConsulta=${this.datos.IdConsultas}`, body).subscribe(
      response => {
        alert('Se agrego el examen a la consulta');
        this.subscribirse();
        // Actualizar la lista de especialidades agregadas
      },
      error => {
        if (error.status === 400) {
          alert('No se encontro el examen pedido');
        } else if(error.status === 409) {
          alert('No puedes agregar el mismo examen dos veces ');
        }
      }
    );
  }
  EliminarExamenesSolicitados(e: any){
    this.http.delete('http://localhost:8080/Proyecto2Clinica/ControladorExamenesConsultas?accion=obtenerExamenesAgregados&Nombre='+e+'&IdConsulta='+this.datos.IdConsultas).subscribe(
      (response) => {
        alert('Se elimino el examen de la consulta con exito');
      },
      (error) => {
        this.subscribirse();
        alert('Se elimino el examen de la consulta con exito');
      }
    );
  }
  Finalizar(){
    if(this.MensajeDeConfirmacion){
      this.chow = false;
      
      const idConsulta = this.datos.IdConsultas;
      const body = { idConsulta: idConsulta };
      this.http.put(`http://localhost:8080/Proyecto2Clinica/ControladorConsultas?accion=ActualizarConsulta&IdConsulta=${idConsulta}&Estado=EXAMEN_PENDIENTE`, body).subscribe(
        response => {
          alert('Se cambio la consulta a estado examen pendiente');
          this.cerrado.emit();
        },
        error => {
          if (error.status === 400) {
            alert('Error al actualizar la consulta');
          } else {
            alert('Error al actualizar');
          }
        }
      );
    }else{
      alert("Recuerda que luego no podras agregar más examenes a la consulta, si ya acabaste presiona finalizar otra vez");
      this.MensajeDeConfirmacion = true;
    }
  }
}
