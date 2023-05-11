import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../../Services/user';
@Component({
  selector: 'app-menu-reporte-paciente',
  templateUrl: './menu-reporte-paciente.component.html',
  styleUrls: ['./menu-reporte-paciente.component.css']
})
export class MenuReportePacienteComponent {
  protected fechaInicial: any;
  protected fechaFinal: any;
  //variables reporte historial
  protected mostrarHistorial = false;
  protected InfoHistorial: any[] = [];
  protected Historial: any = {};
  //Variables reporte Consultas
  protected mostrarConsultas = false;
  protected TablaEspecialidad = false;
  protected BotonEspecialidad = false;
  protected InfoConsultas: any[] = [];
  protected Consultas: any = {};
  protected selectedOptionEspecialidad: any;
  protected ListaEspecialidades: any[] = [];
  //variables reporte Solicitud
  protected mostrarExamenes = false;
  protected TablaExamenes = false;
  protected BotonExamene = false;
  protected InfoSolicitudes: any[] = [];
  protected Solicitudes: any = {};
  protected selectedOptionExamen: any;
  protected ListaExamenes: any[] = [];
  protected ListaExamenesFiltrada: any[] = [];
  //Variables reporte Recargas
  protected mostrarRecargas = false;
  protected historialRecargas: any[] = [];
  protected recargastxt: any = {};
  constructor(private http: HttpClient , private UserService: UserService) { }
  ngOnInit() {
    this.getEspecialidades().subscribe(ListaEspecialidades => {
      this.ListaEspecialidades = ListaEspecialidades;
    });
    this.getExamenes().subscribe(ListaEspecialidades => {
      this.ListaExamenes = ListaEspecialidades;
    });
  }
  //metodo para cerrar reportes
  CerrarReportes(){
    this.mostrarHistorial = false;
    this.mostrarRecargas = false;
    this.mostrarConsultas = false;
    this.mostrarExamenes = false;
  }
  //get de el arreglo para el historial de paciente:
  getHistorial(){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorDeReportes?accion=HistorialDePaciente&FInicio='+this.fechaInicial+'&FechaFinal='+this.fechaFinal+'&IdPaciente='+this.UserService.getId();
    return this.http.get<any[]>(url);
  }
    //get de el arreglo para el historial de recargas:
    getHistorialRecargas(){
      const url = 'http://localhost:8080/Proyecto2Clinica/ControladorDeReportes?accion=HistorialDeRecargas&FInicio=&IdPaciente='+this.UserService.getId();
      return this.http.get<any[]>(url);
    }
  ////////////////////////////////////////////////////////////////
      //metodos para consultas:
      //get especialidades:
      getEspecialidades() {
        const url = 'http://localhost:8080/Proyecto2Clinica/Especialidades?accion=obtenerEspecialidades';
        return this.http.get<any[]>(url);
      }
    //get de el arreglo para las consultas por especialidad ds:
    getHistorialConsultas(){
      const url = 'http://localhost:8080/Proyecto2Clinica/ControladorDeReportes?accion=ConsultasPorEspecialidad&FInicio='+this.fechaInicial+'&FechaFinal='+this.fechaFinal+'&Espe='+this.selectedOptionEspecialidad+'&IdPaciente='+this.UserService.getId();
      return this.http.get<any[]>(url);
    }
    //llenar datos consultas:
    llenarConsultas(){
      if(this.comprobarFechas()){
        this.InfoConsultas = [];
        this.getHistorialConsultas().subscribe(Historial => {
          this.Consultas = Historial;
          console.log(Historial);
          for (let i = 0; i < this.Consultas.length; i += 6) {
            this.InfoConsultas.push({
              Nombre: this.Consultas[i],
              NombreTrabajo: this.Consultas[i + 1],
              Fecha: this.Consultas[i + 2],
              Telefono: this.Consultas[i + 3],
              Correo: this.Consultas[i + 4],
              Final: this.Consultas[i + 5],
            });
          }
          this.TablaEspecialidad = true;
        });
      }
    }
    //activar botong
    boton(){
      this.BotonExamene = true;
    }
    //activar todo
    MostrarConsultas(){    
      this.getEspecialidades().subscribe(ListaEspecialidades => {
        this.ListaEspecialidades = ListaEspecialidades;
        this.ReiniciarFechas();
        this.CerrarReportes();
        this.BotonEspecialidad = true;
        this.mostrarConsultas = true;
      });
    }
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
//metodos para Solicitudes:
      //get Examenes:
      getExamenes() {
        const url = 'http://localhost:8080/Proyecto2Clinica/ControladorDeExamenes?accion=obtenerExamenes';
        return this.http.get<any[]>(url);
      }
 //get de el arreglo para las Solicitudes por Examenes ds:
    getHistorialSolicitudes(){
      const url = 'http://localhost:8080/Proyecto2Clinica/ControladorDeReportes?accion=SolicitudesPorExamenes&FInicio='+this.fechaInicial+'&FechaFinal='+this.fechaFinal+'&examen='+this.selectedOptionExamen+'&IdPaciente='+this.UserService.getId();
      return this.http.get<any[]>(url);
    }
    //llenar datos consultas:
    llenarExamenes(){
      if(this.comprobarFechas()){
        this.InfoSolicitudes = [];
        this.getHistorialSolicitudes().subscribe(Historial => {
          this.Solicitudes = Historial;
          for (let i = 0; i < this.Solicitudes.length; i += 6) {
            this.InfoSolicitudes.push({
              Nombre: this.Solicitudes[i],
              NombreTrabajo: this.Solicitudes[i + 1],
              Fecha: this.Solicitudes[i + 2],
              Telefono: this.Solicitudes[i + 3],
              Correo: this.Solicitudes[i + 4],
              Final: this.Solicitudes[i + 5],
            });
          }
          this.InfoSolicitudes = this.InfoSolicitudes.filter((item, index, self) => {
            return index === self.findIndex((t) => (
              t.Nombre === item.Nombre &&
              t.NombreTrabajo === item.NombreTrabajo &&
              t.Fecha === item.Fecha &&
              t.Telefono === item.Telefono &&
              t.Correo === item.Correo &&
              t.Final === item.Final
            ));
          });
          this.TablaExamenes = true;
        });
      }
    }
    //activar botong
      botonExamen(){
        this.BotonEspecialidad = true;
      }
      //activar todo
      MostrarExamenes(){    
        this.getExamenes().subscribe(ListaEspecialidades => {
          this.ListaExamenes = ListaEspecialidades;
          this.ReiniciarFechas();
          this.CerrarReportes();
          this.BotonExamene = true;
          this.mostrarExamenes = true;
        });
      }  


///////////////////////////////////////////////////////////////////////






  //metodo reporte de Historial
  MostrarHistorial(){
    if(this.comprobarFechas()){
      this.CerrarReportes();
      this.ReiniciarFechas();
      this.mostrarHistorial = true;
      this.llenarElHistorial();
    }
  }
  //metodo para mostrar el reporte de recargas
  MostrarHistorialRecargas(){
    this.CerrarReportes();
      this.mostrarRecargas = true;
      this.HistorialDeRecargas();
      this.ReiniciarFechas();
  }
  //metodo para llenar la info del historial
  llenarElHistorial(){
    this.getHistorial().subscribe(Historial => {
      this.Historial = Historial;
      for (let i = 0; i < this.Historial.length; i += 7) {
        this.InfoHistorial.push({
          Nombre: this.Historial[i],
          NombreTrabajo: this.Historial[i + 1],
          Fecha: this.Historial[i + 2],
          Telefono: this.Historial[i + 3],
          Correo: this.Historial[i + 4],
          Final: this.Historial[i + 5],
          Estado: this.Historial[i + 6],
        });
      }
    });
  }
  //metodo para traer el historial de recargas del paciente:
  HistorialDeRecargas(){
    this.getHistorialRecargas().subscribe(Historial => {
      this.recargastxt = Historial;
      for (let i = 0; i < this.recargastxt.length; i += 2) {
        this.historialRecargas.push({
          Monto: this.recargastxt[i],
          Fecha: this.recargastxt[i + 1].substring(0,16),
        });
      }
    });
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
    //metodo reiniciar fechas
    ReiniciarFechas(){
      this.fechaInicial = new Date();
      this.fechaFinal = new Date();
    }
}
