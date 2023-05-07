import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../../Services/user';
@Component({
  selector: 'app-menu-reportes-medicos',
  templateUrl: './menu-reportes-medicos.component.html',
  styleUrls: ['./menu-reportes-medicos.component.css']
})
export class MenuReportesMedicosComponent {
  fechaInicial:Date | undefined;
  fechaFinal:Date | undefined;
  saldoMedico: any;
  mostrarSaldo = false;
  mostrarPacientes = false;
  mostrarEspecialidades = false;
  mostrar = false;
  Accion:String ="";
  fechas=false;
  InfoPacientes: any[] = [];
  InfoEspecialidades: any[] = [];
  constructor(private http: HttpClient , private UserService: UserService) { }
  
  ngOnInit() {
    this.getSaldo().subscribe(consultas => {
      this.saldoMedico = consultas;
    });
  }
  //metodo que maneja los botones:
    MostrarSaldo(){
      this.mostrarSaldo = true;
      this.mostrarPacientes = false;
      this.mostrarEspecialidades = false;
      this.mostrar=false;
    }
    //Metodo que activa el mostrar por paciente:
    activarPacientes(){
      this.Accion="topPacientesMedicos";
      this.mostrarPacientes=true;
      this.mostrarEspecialidades = false;
      this.MostrarTop();
    }
    //metodo que activa el mostrar por especialidad:
    activarEspecialidad(){
      this.Accion="topEspecialidadesMedicos";
      this.mostrarPacientes=false;
      this.mostrarEspecialidades = true;
      this.MostrarTop();
    }
    MostrarTop(){
      if(this.fechaInicial===undefined||this.fechaFinal===undefined){
        alert("Ingrese fechas validas en los campos");
      }else{
        const fechaInicial = new Date(this.fechaInicial);
        const fechaFinal = new Date(this.fechaFinal);
        if(fechaInicial.getTime() < fechaFinal.getTime()){
              this.getTops().subscribe(pacientes => {
                this.InfoPacientes = pacientes;
                this.mostrar = true;
                this.mostrarSaldo = false;
              });
        }else{
          alert("la fecha de inicio no puede ser después de la fecha final");
        } 
      }
    }
 //metodos encargados de buscar el saldo al servlet:
  getSaldo(){
    const url = 'http://localhost:8080/Proyecto2Clinica/Usuario?accion=obtenerSaldo&IdMedico='+this.UserService.getId();
    return this.http.get<any[]>(url);
  }
  //metodos encargados de buscar el top 5 en el server:
  getTops(){
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorDeReportes?accion='+this.Accion+'&IdMedico='+this.UserService.getId()+'&FInicio='+this.fechaInicial+'&FechaFinal='+this.fechaFinal;
    return this.http.get<any[]>(url);
  }
    //metodos encargados de buscar el top 5 especialidades en el server:
    getTopEspecialides(){
      const url = 'http://localhost:8080/Proyecto2Clinica/ControladorDeReportes?accion=topEspecialidadesMedicos&IdMedico='+this.UserService.getId();
      return this.http.get<any[]>(url);
    }
  //metodo para comprara las fechas:
  comprobarFechas(FechaInicio: Date, FechaFinal: Date): boolean {
    return FechaInicio.getTime() < FechaFinal.getTime();
  }

}
