import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserService } from '../../../../Services/user';


@Component({
  selector: 'app-menu-examenes-pendientes',
  templateUrl: './menu-examenes-pendientes.component.html',
  styleUrls: ['./menu-examenes-pendientes.component.css']
})
export class MenuExamenesPendientesComponent {
  Solicitud: any[] = []; 
  formularioVisible = false;
  Info: any = {};

  constructor(private http: HttpClient , private UserService: UserService) { }
  
  ngOnInit() {
    this.getSolicitud().subscribe(Solicitud => {
      this.Info = Solicitud;
      this.llenarTabla();
    });
  }
  getSolicitud() {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorSolicitudes?accion=obtenerSolicitudesIdPaciente&IdPaciente='+this.UserService.getId();
    return this.http.get<any[]>(url);
  }

  llenarTabla(){
    for (let i = 0; i < this.Info.length; i += 7) {
      if(this.Info[i + 6]==='null'){
      this.Solicitud.push({
        IdSolicitud: this.Info[i],
        NombreLab: this.Info[i + 1],
        Fecha: this.Info[i + 2],
        Telefono: this.Info[i + 3],
        Correo: this.Info[i + 4],
        Estado: this.Info[i + 5],
        FechaFinalizado: "",
      });
    }else{
      this.Solicitud.push({
        IdSolicitud: this.Info[i],
        NombreLab: this.Info[i + 1],
        Fecha: this.Info[i + 2],
        Telefono: this.Info[i + 3],
        Correo: this.Info[i + 4],
        Estado: this.Info[i + 5],
        FechaFinalizado: this.Info[i + 6],
      });
    }
    }
  }
  //botóng para descargar los examenes de una solicitud finalizada:
  DescargarExamenes(Id: String){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesSolicitudes?accion=DescargarExamenes&IdSolicitud='+Id;
    const headers = new HttpHeaders().set('Accept', 'application/zip');
    this.http.get(url, { headers, responseType: 'blob' }).subscribe((response) => {
      // Crear un objeto URL con el archivo ZIP
      const url = window.URL.createObjectURL(response);
      // Crear un elemento <a> para descargar el archivo ZIP
      const a = document.createElement('a');
      a.href = url;
      a.download = 'Examenes.zip';
      document.body.appendChild(a);
      a.click();   
      // Limpiar el objeto URL y el elemento <a>
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
    });
  }

}
