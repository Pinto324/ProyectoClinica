import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-menu-reportes-admin',
  templateUrl: './menu-reportes-admin.component.html',
  styleUrls: ['./menu-reportes-admin.component.css']
})
export class MenuReportesAdminComponent {
  //variables globales
  protected fechaInicial:Date | undefined;
  protected fechaFinal:Date | undefined;
  protected Accion: string = "";
  //Varables Tops
  protected Data: any[] = [];
  protected mostrarHistorial = false;
  protected mostrarFecha = false;
  protected mostrarPacientes = false;
  protected mostrarTop = false;
  protected mostrarLaboratorios = false;
  protected mostrarTotal = false;
  protected mostrarPorcentaje = false;
  constructor(private http: HttpClient) { }

  ngOnInit() {

  }
  //Metodos para llamar tops:
  Top5Medicos(){
    this.Accion ="Top5Medicos";
    this.cerrarDatos();
    this.mostrarPorcentaje = false;
    this.mostrarPacientes = true;
    this.getTops().subscribe(consultas => {
      this.Data = consultas;
      this.mostrarTop = true;
    });
  }
  Top5Labs(){
    this.Accion ="Top5Labs";
    this.cerrarDatos();
    this.mostrarPorcentaje = false;
    this.mostrarLaboratorios = true;
    this.getTops().subscribe(consultas => {
      this.Data = consultas;
      this.mostrarTop = true;
    });
  }
  PorcentajesApp(){
    this.Accion ="HistorialPorcentajes";
    this.cerrarDatos();
    this.mostrarPorcentaje = true;
    this.getTops().subscribe(consultas => {
      this.Data = consultas;
      this.mostrarTop = true;
    });
  }
  //metodo para mostrar fechas y buscar:
  MostrarFecha(){
    this.cerrarDatos();
    this.mostrarFecha = true;
  } 
  buscarConFechas(){
    if(this.comprobarFechas()){
      this.getFinal().subscribe(consultas => {
        this.Data = consultas;
        this.mostrarTotal = true;
      });   
    }
  }
  //Cerrar todo
  cerrarDatos(){
    this.mostrarFecha = false;
    this.mostrarHistorial = false;
    this.mostrarPacientes = false;
    this.mostrarLaboratorios = false;
    this.mostrarTotal = false;
    this.mostrarTop = false;
  }
  //metodos encargados de buscar el top 5 en el server:
  getTops(){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorDeReportes?accion='+this.Accion;
    return this.http.get<any[]>(url);
  }
  //Total de ingresos registrados
  getFinal(){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorDeReportes?accion=TotalIngresos&FInicio='+this.fechaInicial+'&FechaFinal='+this.fechaFinal;
    return this.http.get<any[]>(url);
  }
   //metodo para comprara las fechas:
      comprobarFechas(): boolean {
        if(this.fechaInicial===undefined||this.fechaFinal===undefined){
          alert("llene las fechas con un dato valido");
          return false
        }else{
          const fechaInicial = new Date(this.fechaInicial);
          const fechaFinal = new Date(this.fechaFinal);
          if(fechaInicial.getTime() < fechaFinal.getTime()){
            return true;
          }else{
            alert("La fecha inicial no puede estar más lejos que la fecha final");
            return false
          }
        }
      }
}
