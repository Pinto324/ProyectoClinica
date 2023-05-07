import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginComponent } from './Componentes/login/login.component';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ProyectoMedic';
  constructor(private router: Router) { }
  ngOnInit(): void {
    // Redirige automáticamente al componente home al cargar el componente por defecto
    this.router.navigate(['./Componentes/login/login.component']);
  }
}
