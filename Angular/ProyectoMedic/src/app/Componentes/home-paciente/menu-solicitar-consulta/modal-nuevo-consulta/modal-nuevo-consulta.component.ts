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
  protected MostrarEspecialidad = false;
  protected horaSeleccionada :any;
  protected horasDisponibles : String[] = [];
  protected especialidad :any;
  protected especialidades : any[] = [];
  protected precioConsulta : number = 0.00;
  constructor(private http: HttpClient , private UserService: UserService  ) { }
  
  ngOnInit() {
    
  }
  //METODOS
  Mostrar(Datos:any){
    this.getEspecialidades(Datos).subscribe(info => {   
      this.mostrar = true;
      this.dato = Datos;
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
        this.getHorario().subscribe(info => {  
        this.horasDisponibles = info;
        console.log(this.horasDisponibles);
        this.fechaLista = true;
        this.fechaSirve = false;      
        });
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
      const url = 'http://localhost:8080/Proyecto2Clinica/EspecialidadesMedico?accion=obtenerEspecialidadesAgregadas&Estado=Activa&IdMedico='+info.Id;
      return this.http.get<any[]>(url);
    }
      //get de horas disponibles:
      getHorario() {
        const url = 'http://localhost:8080/Proyecto2Clinica/ControladorHorario?accion=obtenerHorarioDisponibleId&IdMedico='+this.dato.Id+'&Fecha='+this.FechaConsulta;
        return this.http.get<any[]>(url);
      }

    PonerPrecio(){
      for (let i = 0; i < this.especialidades.length; i++){
        if(parseInt(this.especialidades[i].IdEspecialidad) === parseInt(this.especialidad)){
          this.precioConsulta = this.especialidades[i].Precio;
        }
      }
    }
    //metodo para crear la consulta
  CrearConsulta(){
    const IdPaciente = this.UserService.getId();
    const IdMedico = this.dato.Id;
    const IdEspecialidad = this.especialidad;
    const FechaAgendada = this.FechaConsulta+ " " + this.horaSeleccionada + ":00";
    const Precio = this.precioConsulta;
    const body = { IdPaciente: IdPaciente, IdMedico: IdMedico, IdEspecialidad: IdEspecialidad, FechaAgendada:FechaAgendada, Precio:Precio};
    this.http.post(`http://localhost:8080/Proyecto2Clinica/ControladorConsultas?accion=CrearConsulta&IdPaciente=${IdPaciente}&IdMedico=${IdMedico}&IdEspecialidad=${IdEspecialidad}&FechaAgendada=${FechaAgendada}&Precio=${Precio}`, body).subscribe(
      response => {
        alert('Consulta agendada correctamente');
        alert('Se le a debitado el dinero de la consulta');
        this.mostrar = false;
      },
      error => {
        if (error.status === 400) {
          alert("ocurrio un fallo al crear la consulta");
        } else if(error.status === 409) {
          alert('No cuentas con el saldo suficiente para agendar la consulta');
        }
      }
    );
  }
}
