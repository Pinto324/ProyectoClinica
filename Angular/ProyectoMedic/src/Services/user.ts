import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private username: any;
  private id: any;
  private tipo: any;
  constructor() { }

  setUser(username: any, id: any, tipo: any) {
    this.username = username;
    this.id = id;
    this.tipo = tipo;
  }

  getUsername() {
    return this.username;
  }
  getId() {
    return this.id;
  }
  getTipo(){
    return this.tipo;
  }
}