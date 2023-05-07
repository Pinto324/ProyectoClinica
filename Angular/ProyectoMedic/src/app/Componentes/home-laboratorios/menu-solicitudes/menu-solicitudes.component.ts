import { Component } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { UserService } from '../../../../Services/user';
@Component({
  selector: 'app-menu-solicitudes',
  templateUrl: './menu-solicitudes.component.html',
  styleUrls: ['./menu-solicitudes.component.css']
})
export class MenuSolicitudesComponent {
  Solicitud: any[] = [];
  constructor(private http: HttpClient , private UserService: UserService) {}
  ngOnInit() : void  {
    this.getSolicitudes().subscribe(especialidadesAgregadas => {
      this.Solicitud = especialidadesAgregadas;
    })
  }
  //metodo para traer la información de la tabla
  getSolicitudes() {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorSolicitudes?accion=obtenerSolicitudes&IdLab='+this.UserService.getId();
    return this.http.get<any[]>(url);
  }
}
