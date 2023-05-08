import { Component, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-modal-paciente-examenes-consultas',
  templateUrl: './modal-paciente-examenes-consultas.component.html',
  styleUrls: ['./modal-paciente-examenes-consultas.component.css']
})
export class ModalPacienteExamenesConsultasComponent {
  datos: any;
  tabla: any;
  show = false;
  lleno = true;
  selectedFile: any;
  fileToUpload: File | undefined;
@Output() cerrado = new EventEmitter();
constructor(private http: HttpClient  ) { }
//metodo de iniciacion para ver los examenes y si existe archivo
ngOnInit() : void  {

}
  //metodo con el que me envian el id de la Consulta
  showModalInicial(datos: any){
    this.datos = datos;
    console.log(datos);
    this.show = true;
    this.getExamenesDeSolicitud().subscribe(examenes => {
      this.tabla = examenes;
    })
  }
  //metodo para desactivar el botón si no está lleno:
  desactivarBoton(){
    this.lleno = false;
  }
  activarBoton(){
    this.lleno = true;
  }
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  uploadFile(valor:any) { 
    if (this.selectedFile.type === 'application/pdf') { 
      const formData = new FormData();
      formData.append('file', this.selectedFile);
      this.http.put('http://localhost:8080/Proyecto2Clinica/ControladorExamenesDeConsultas?accion=subirExamenPdf&IdESL='+valor, formData).subscribe(
        res => {
          alert('Examen subido correctamente');
          this.getExamenesDeSolicitud().subscribe(examenes => {
            this.tabla = examenes;
          })
        },
        err => {
          alert('Error al subir Examen:'+err);
        }
      );
    }else {
      alert('Por favor seleccione un archivo PDF');
    }
  }
  
//borrar

  //metodo que busca los examenes:
  getExamenesDeSolicitud(){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesDeConsultas?accion=obtenerExamenesConsulta&IdConsulta='+this.datos.IdConsulta;
    return this.http.get<any[]>(url);
  }
  //metodo para finalizar la consulta cuando este lleno todo:
    MandarConsulta(){
      this.http.put('http://localhost:8080/Proyecto2Clinica/ControladorConsultas?accion=ActualizarConsulta&IdConsulta='+this.datos.IdConsulta+'&Estado=PENDIENTE_REVISION', this.datos).subscribe(
        res => {
          alert('Se mandaron los examenes al medico correctamente');
          this.getExamenesDeSolicitud().subscribe(examenes => {
            this.tabla = examenes;
            this.cerrado.emit();
            this.show = false;
          })
        },
        err => {
          if(err==409){
            alert('ocurrio un error con mandar los examenes de la consulta, comuniquese con soporte tecnico');
          }else{ alert('ocurrio un error comuniquese con soporte tecnico para solucionarlo');}
         
        }
      );
    }
}

