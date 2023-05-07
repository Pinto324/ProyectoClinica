import { Component } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
@Component({
  selector: 'app-medico-menu-historial',
  templateUrl: './medico-menu-historial.component.html',
  styleUrls: ['./medico-menu-historial.component.css']
})
export class MedicoMenuHistorialComponent {
  mostrar = false;
  datos: any[] = [];
  examemes: any[] = [];
  selectedOption: String = "";
  pacientes: any;
  tieneExamen = false;
  constructor(private http: HttpClient) { }
//Recargo del select
  ngOnInit() : void  {
    this.getPacientes().subscribe(ListaPaciente => {
      this.pacientes = ListaPaciente;
    });
  }

  getPacientes() {
    const url = 'http://localhost:8080/Proyecto2Clinica/Usuario?accion=obtenerPacientes';
    return this.http.get<any[]>(url);
  }
  //metodo get las consultas para el historial
  getHistorialPaciente(){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorConsultas?accion=obtenerConsultasPorPaciente&IdPaciente='+this.selectedOption;
    return this.http.get<any[]>(url);
  }

  llenarDatos(){
    if(this.selectedOption===""){
      alert("Debes seleccionar un paciente para poder buscar su historial")
    }else{
      this.getHistorialPaciente().subscribe(consultas => {
        this.datos = consultas;
      });
      this.mostrar = true;
    }
  }
  getExamenes(e:number){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesConsultas?accion=obtenerExamenesAgregados&id='+e;
    return this.http.get<any[]>(url);
  }

  Descargar(e:number){
    this.getExamenes(e).subscribe(examen => {
      this.examemes = examen;
      if(this.examemes.length === 0){
        alert("la consulta no cuenta con examenes para descargar");
     }else{
      const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesConsultas?accion=DescargarExamenes&id='+e;
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
    });
  }
}
