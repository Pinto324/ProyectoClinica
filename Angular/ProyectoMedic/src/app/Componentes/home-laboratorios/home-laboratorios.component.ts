import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserService } from '../../../Services/user';
@Component({
  selector: 'app-home-laboratorios',
  templateUrl: './home-laboratorios.component.html',
  styleUrls: ['./home-laboratorios.component.css']
})
export class HomeLaboratoriosComponent {
  //Variables para carga de datos:
  protected MostrarCargaDeDatos = true;
  protected EnvioEspecialidad = true;
  protected NombreEspe: String = "";
  protected Descripcion: String = "";
  protected NoExamenes = false;
  protected examenesAgregados: any[] = [];
  MostrarSolicitud = false;
  MostrarExamen = false;
  MostrarReportes = false;
  constructor(private http: HttpClient,  private UserService: UserService ) {}

  ngOnInit() : void  {
    this.getExamenesAsignados().subscribe(info => {
      this.examenesAgregados = info;
      this.ComprobarPrimerInicio();
    })
  }
//metodo para el menu de reportes
  abrirMenuReportes(){
    this.CerrarMenus();
    this.MostrarReportes=true;
  }
  //Metodos para abrir el menu de solicitudes
  abrirFormularioSolicitud(){
    this.CerrarMenus();
    this.MostrarSolicitud=true;
  }
  //metodos para abrir el menu de examenes:
  abrirFormularioExamen(){
    this.CerrarMenus();
    this.MostrarExamen = true;
  }
  //mostrar form examen
  abrirExamen(){
    this.CerrarMenus();
    this.EnvioEspecialidad = true;
  }
  //cerrar todo alv
  CerrarMenus(){
    this.MostrarSolicitud = false;
    this.MostrarExamen = false;
    this.MostrarReportes = false;
    this.EnvioEspecialidad = false;
  }

  //metodos para enviar ingreso primera vez:
    //metodo para conseguir los examenes asignados:
    getExamenesAsignados() {
      const url = 'http://localhost:8080/Proyecto2Clinica/ControladorExamenesLaboratorios?accion=obtenerExamenesAsignados&IdLab='+this.UserService.getId();
      return this.http.get<any[]>(url);
    }
    comprobarVacio():boolean{
      if(!(this.NombreEspe==""||this.Descripcion=="")){
        return true;
      }else{
        alert("debes llenar los campos para mandar la solicitud");
        return false;
      }
    }
    ComprobarPrimerInicio(){
      if (Array.isArray(this.examenesAgregados) && this.examenesAgregados.length > 0) {
          this.NoExamenes = true;
      } else {
        this.EnvioEspecialidad = false;
        alert("para desbloquear todos los menús debés asignarte examenes antes, cierra sesión y vuelve a logear cuando hayas completado la información");
      }
    }
    //metodos para enviar nuevo exámen:
    EnviarSoliExamen(){
      if(this.comprobarVacio()){
        const nombre = this.NombreEspe;
        const Descripcion = this.Descripcion;
        const body = { nombre: nombre, Descripcion:Descripcion};
        this.http.post(`http://localhost:8080/Proyecto2Clinica/ControladorDeExamenes?accion=EnviarSolicitudNuevoExamen&nombre=${nombre}&Descripcion=${Descripcion}`, body).subscribe(
          response => {
            alert('Se envio la solicitud del nuevo tipo de examen.');
            this.NombreEspe="";
            this.Descripcion="";
          },
          error => {
            if (error.status === 400) {
              alert('Error al  confirmar la solicitud del nuevo tipo de examen.');
            } else {
              alert('Error al  confirmar la solicitud del nuevo tipo de examen');
            }
          }
        );
      }
    }
}
