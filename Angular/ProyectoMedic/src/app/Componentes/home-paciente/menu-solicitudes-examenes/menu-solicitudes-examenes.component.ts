import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-menu-solicitudes-examenes',
  templateUrl: './menu-solicitudes-examenes.component.html',
  styleUrls: ['./menu-solicitudes-examenes.component.css']
})
export class MenuSolicitudesExamenesComponent {
  //Variable para la busqueda:
  protected Labs: any[] = [];
  //Variables para mostrar cosas:
  protected mostrarModal = false;
  //variable para buscar:
  protected busqueda: string = "";
  //variable de los datos en la tabla
  borrando: boolean = false;
  protected Tabla: {Id: string, Nombre: string, Telefono: string, Correo: string }[] = [];
  protected TablaGrafica: {Id: string, Nombre: string, Telefono: string, Correo: string }[] = []; 
  //constructor xd
  constructor(private http: HttpClient) { }
  ngOnInit(){
    this.getInfo().subscribe(info => {
      this.Labs = info;
      this.llenarTabla();
    })
  }
  // metodo get
  getInfo() {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesLaboratorios?accion=obtenerExamenesConTelefono&Estado=Activa';
    return this.http.get<any[]>(url);
  }
//metodo que llena la tabla
llenarTabla(){
  for (let i = 0; i < this.Labs.length; i += 4) {
    this.Tabla.push({
      Id: this.Labs[i],
      Nombre: this.Labs[i + 1],
      Telefono: this.Labs[i + 2],
      Correo: this.Labs[i + 3]
    });
    for (let j = 0; j < this.Tabla.length; j++) {
      if(!this.TablaGrafica.some(e => e.Nombre.toLowerCase() === this.Tabla[j].Nombre.toLowerCase())){
        this.TablaGrafica.push({
          Id: this.Labs[i],
          Nombre: this.Labs[i + 1],
          Telefono: this.Labs[i + 2],
          Correo: this.Labs[i + 3]
        });
      }
    }
  }
}
//metodo que va enseñando la info a mostrar:
comprobadorDeInfo() {
  let temp: { Id: string; Nombre: string; Telefono: string;Correo:string; }[] = [];
  for (let i = 0; i < this.Tabla.length; i++) {
    if (this.Tabla[i].Nombre.toLowerCase().startsWith(this.busqueda.toLowerCase())) {
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

