import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-home-admin',
  templateUrl: './home-admin.component.html',
  styleUrls: ['./home-admin.component.css']
})
export class HomeAdminComponent {
    //Variables para carga de datos:
    protected MostrarCargaDeDatos = true;
    protected ArchivoDeCarga: any;
    //constructor
    constructor(private http: HttpClient) {}
    //metodos de utilidades:

  //metodo para la carga de datos:
  onUpload(): void {
    if(!(this.ArchivoDeCarga==undefined)) {
    const formData = new FormData();
    formData.append('file', this.ArchivoDeCarga);
    this.http.post('http://localhost:8080/Proyecto2Clinica/ControladorCargaDeDatos', formData).subscribe(
      (response) => alert('Archivo cargado exitosamente'),
      (error) => alert('Error al cargar el archivo')
    );
  }else{ alert("seleccione un archivo")}
  }
  onFileSelected(event:any) {
    const file: File = event.target.files[0];
    this.ArchivoDeCarga = file;
  }


}
