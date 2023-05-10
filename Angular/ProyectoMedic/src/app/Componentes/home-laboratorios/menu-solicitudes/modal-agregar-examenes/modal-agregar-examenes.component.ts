import { Component, EventEmitter, Output } from '@angular/core';
import { HttpClient} from '@angular/common/http';
@Component({
  selector: 'app-modal-agregar-examenes',
  templateUrl: './modal-agregar-examenes.component.html',
  styleUrls: ['./modal-agregar-examenes.component.css']
})
export class ModalAgregarExamenesComponent {
  datos: any;
  tabla: any;
  show = false;
  lleno = true;
  selectedFile: any;
  fileToUpload: File | undefined;
  @Output() cerrado = new EventEmitter<void>();
  constructor(private http: HttpClient) {}
//metodo de iniciacion para ver los examenes y si existe archivo
  ngOnInit() : void  {

  }
  //metodo con el que me envian el id de la solicitud
  showModalInicial(datos: any){
    this.datos = datos;
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
      this.http.put('http://localhost:8080/Proyecto2Clinica/ControladorExamenesSolicitudes?accion=subirExamenPdf&IdESL='+valor, formData).subscribe(
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
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesSolicitudes?accion=obtenerExamenesSolicitud&IdSolicitud='+this.datos;
    return this.http.get<any[]>(url);
  }
  //metodo para finalizar la consulta cuando este lleno todo:
    FinalizarConsulta(){
      this.http.put('http://localhost:8080/Proyecto2Clinica/ControladorSolicitudes?accion=FinalizarSolicitud&IdSolicitud='+this.datos, this.datos).subscribe(
        res => {
          alert('Se completo la solicitud correctamente');
          this.getExamenesDeSolicitud().subscribe(examenes => {
            this.tabla = examenes;
            this.cerrado.emit();
            this.show = false;
          })
        },
        err => {
          if(err==409){
            alert('ocurrio un error con la finalización de la solicitud, comuniquese con soporte tecnico');
          }else{ alert('ocurrio un error con el deposito, comuniquese con soporte tecnico para solucionarlo');}
         
        }
      );
    }
}
