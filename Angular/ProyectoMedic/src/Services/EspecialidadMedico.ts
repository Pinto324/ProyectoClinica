import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private IdEM: any;
  private IdDelMedico: any;
  private NombreEspecialidad: any;
  private PrecioEspecialidad: any;
  constructor() { }

  setEspecialidad(IdEM: any, IdDelMedico: any, NombreEspecialidad: any, PrecioEspecialidad: any) {
    this.IdEM = IdEM;
    this.IdDelMedico = IdDelMedico;
    this.NombreEspecialidad = NombreEspecialidad;
    this.PrecioEspecialidad = PrecioEspecialidad;
  }

  getIdEM() {
    return this.IdEM;
  }
  getIdDelMedico() {
    return this.IdDelMedico;
  }
  getNombreEspecialidad(){
    return this.NombreEspecialidad;
  }
  getPrecioEspecialidad(){
    return this.PrecioEspecialidad;
  }

}