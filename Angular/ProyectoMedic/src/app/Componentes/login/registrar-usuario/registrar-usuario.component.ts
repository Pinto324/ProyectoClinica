import { Component, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
@Component({
  selector: 'app-registrar-usuario',
  templateUrl: './registrar-usuario.component.html',
  styleUrls: ['./registrar-usuario.component.css']
})
export class RegistrarUsuarioComponent {
  registro: any = {};

  constructor(private http: HttpClient, private router: Router) {}
  @Output() cerrar = new EventEmitter();

  enviarRegistro() {
    // Verificar que no haya campos vacíos
    if (!this.registro.nombre || !this.registro.usuario || !this.registro.contrasena ||
        !this.registro.direccion || !this.registro.telefono || !this.registro.correo ||
        !this.registro.cui || !this.registro.fecha || !this.registro.tipo) {
      alert('Por favor, completa todos los campos.');
      return;
    }
    const url = 'http://localhost:8080/Proyecto2Clinica/Usuario?accion=CrearUsuario&tipo='+this.registro.tipo+'&fecha='+this.registro.fecha+'&cui='+this.registro.cui+'&correo='+this.registro.correo+'&telefono='+this.registro.telefono+'&direccion='+this.registro.direccion+'&nombre='+this.registro.nombre+'&usuario='+this.registro.usuario+'&contrasena='+this.registro.contrasena;
    // Enviar petición POST
    this.http.post(url, this.registro).subscribe(respuesta => {
      alert('Registro exitoso');
    }, error => {
      alert('Error al registrar');
    });
    this.cerrar.emit();
  }

}
