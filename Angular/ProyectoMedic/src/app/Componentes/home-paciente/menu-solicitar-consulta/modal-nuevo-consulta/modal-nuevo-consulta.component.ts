import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../../../Services/user';
@Component({
  selector: 'app-modal-nuevo-consulta',
  templateUrl: './modal-nuevo-consulta.component.html',
  styleUrls: ['./modal-nuevo-consulta.component.css']
})
export class ModalNuevoConsultaComponent {
  protected FechaConsulta: Date | undefined;
  protected dato: any;
  protected mostrar = false;
  protected fechaLista = false;
  protected fechaSirve = true;
  protected horaSeleccionada :any;
  protected horasDisponibles : any;
  protected especialidad :any;
  protected especialidades : any[] = [];
  protected precioConsulta : number = 0.00;
  constructor(private http: HttpClient , private UserService: UserService  ) { }
  
  ngOnInit() {

  }
  //METODOS
  Mostrar(info:any){
    this.getEspecialidades(info).subscribe(info => {
      this.mostrar = true;
      this.dato = info;
      this.especialidades = info;
    })
  }

  enviarFecha() {
    if (this.FechaConsulta) {
      const fechaSeleccionada = new Date(this.FechaConsulta);
      const fechaActual = new Date();
      if (fechaSeleccionada < fechaActual) {
        alert('No puedes seleccionar una fecha anterior a la de hoy');
      }else{
        this.fechaLista = true;
        this.fechaSirve = false;
      }
    } else {
      alert('Seleccione una fecha válida');
    }
  }
  //metodo para la flecha de regreso:
  regresar(){
    this.fechaLista = false;
    this.fechaSirve = true;
  }
  //get de las especialidades:
    getEspecialidades(info:any) {
      const url = 'http://localhost:8080/Proyecto2Clinica/EspecialidadesMedico?accion=obtenerEspecialidadesAgregadas&IdMedico='+info.Id;
      return this.http.get<any[]>(url);
    }

    PonerPrecio(){
      for (let i = 0; i < this.dato.length; i++){
        if(parseInt(this.dato[i].IdEspecialidad) === parseInt(this.especialidad)){
          this.precioConsulta = this.dato[i].Precio;
        }
      }
    }
}
