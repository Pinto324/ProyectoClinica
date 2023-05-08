import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-menu-solicitar-consulta',
  templateUrl: './menu-solicitar-consulta.component.html',
  styleUrls: ['./menu-solicitar-consulta.component.css']
})
export class MenuSolicitarConsultaComponent {
  //Variable para la busqueda:
  protected Doctores: any[] = [];
  //Variables para mostrar cosas:
  protected mostrarModal = false;
  //variable para buscar:
  protected busqueda: string = "";
  //variable de los datos en la tabla
  borrando: boolean = false;
  protected Tabla: {Id: string, Nombre: string, Especialidad: string}[] = [];
  protected TablaGrafica: {Id: string, Nombre: string, Especialidad: string}[] = []; 
  //constructor xd
  constructor(private http: HttpClient) { }
  ngOnInit(){
    this.getInfo().subscribe(info => {
      this.Doctores = info;
      this.llenarTabla();
    })
  }
  // metodo get
  getInfo() {
    const url = 'http://localhost:8080/Proyecto2Clinica/Especialidades?accion=obtenerDoctoresYEspecialidadesPacientes&Estado=Activa';
    return this.http.get<any[]>(url);
  }
//metodo que llena la tabla
llenarTabla(){
  for (let i = 0; i < this.Doctores.length; i += 3) {
    this.Tabla.push({
      Id: this.Doctores[i],
      Nombre: this.Doctores[i + 1],
      Especialidad: this.Doctores[i + 2]
    });
    for (let j = 0; j < this.Tabla.length; j++) {
      if(!this.TablaGrafica.some(e => e.Nombre.toLowerCase() === this.Tabla[j].Nombre.toLowerCase())){
        this.TablaGrafica.push({
          Id: this.Doctores[i],
          Nombre: this.Doctores[i + 1],
          Especialidad: this.Doctores[i + 2]
        });
      }
    }
  }
}
//metodo que va enseñando la info a mostrar:
comprobadorDeInfo() {
  let temp: { Id: string; Nombre: string; Especialidad: string; }[] = [];
  for (let i = 0; i < this.Tabla.length; i++) {
    if (this.Tabla[i].Nombre.toLowerCase().startsWith(this.busqueda.toLowerCase()) || this.Tabla[i].Especialidad.toLowerCase().startsWith(this.busqueda.toLowerCase())) {
      if (!temp.some(e => e.Nombre.toLowerCase() === this.Tabla[i].Nombre.toLowerCase())) {
        temp.push(this.Tabla[i]);
      }
    }
  }
  this.TablaGrafica = temp;
}
  onInput(event:any) {
    this.busqueda = event.target.value;
    if (this.busqueda === '') {
      this.ngOnInit();
    } else {
      this.comprobadorDeInfo();
    }
  }
}
