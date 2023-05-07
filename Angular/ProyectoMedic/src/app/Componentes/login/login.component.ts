import { Component } from '@angular/core';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../../../Services/user';
import { HomeLaboratoriosComponent } from '../home-laboratorios/home-laboratorios.component';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  username: string = "";
  password: string = "";
  errorMessage: string ="";
  constructor(private http: HttpClient, private UserService: UserService  ,private router: Router) { }

  login(username: string, password: string) {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorLogin';
    const body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);
    const options = {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    };
    this.http.post(url, body.toString(), options).subscribe(
      (response: any) => {
        if (response.valid) {
          this.UserService.setUser(response.username, response.id, response.tipo);
          switch(response.tipo){
            case "Medico":
                this.router.navigate(['/home-medico']);
              break;
            case "Admin":
                this.router.navigate(['/home-admin']);
                break;
            case "Laboratorio":
              this.router.navigate(['/home-laboratorios']);
              break;
            case "Paciente":
              this.router.navigate(['/home-Paciente']);
              break;
            default:
              break;
          }

          // Redirigir al usuario a la página de inicio
          
        } else {
          alert('Nombre de usuario o contraseña incorrectos.');
        }
      },
      (error) => {
        // Si ocurre un error al enviar la solicitud, mostrar un mensaje de error
        console.error(error);
        this.errorMessage = 'Ocurrió un error al iniciar sesión. Por favor, inténtalo de nuevo más tarde.';
      }
    );
  }

  goToRegister(){

  }
}
