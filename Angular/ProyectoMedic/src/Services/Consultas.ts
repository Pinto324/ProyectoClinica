import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {
  private idConsulta: any;
  constructor() { }

  setId(idConsulta: any) {
    this.idConsulta = idConsulta;
  }

  getId() {
    return this.idConsulta;
  }
}