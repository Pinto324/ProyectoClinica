import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-menu-especialidades-admin',
  templateUrl: './menu-especialidades-admin.component.html',
  styleUrls: ['./menu-especialidades-admin.component.css']
})
export class MenuEspecialidadesAdminComponent {
  protected DataTabla: any[]=[];
  protected dataExamen: any;
  protected mostrarSolicitudes = false;
  protected mostrarIngresos = false;
  protected boton = false;

  constructor(private http: HttpClient) { }
  
    TablaAsignaciones(){
      this.getExamenes().subscribe(info => {
        this.DataTabla = info;
        this.mostrarIngresos = false;
        this.mostrarSolicitudes = true;
        this.boton = true;
      })
    }
    TablaDeExamanes(){
      this.getExamenesPendientes().subscribe(info => {
        this.dataExamen = info;
        this.mostrarSolicitudes = false;
        this.mostrarIngresos = true;
        this.boton = true;
      })
    }
    //metodo para conseguir las solicitude de examenes:
    getExamenes() {
      const url = 'http://localhost:8080/Proyecto2Clinica/EspecialidadesMedico?accion=obtenerEspecialidadesPendientes';
      return this.http.get<any[]>(url);
    }
     //metodo para conseguir las getExamenesPendientes:
    getExamenesPendientes() {
        const url = 'http://localhost:8080/Proyecto2Clinica/Especialidades?accion=SolicitudesDeEspecialidad';
        return this.http.get<any[]>(url);
      }
    //cambiar tipo de examen:
    CambiarEstado(id :any, Estado:any){
      const Id = id;
      const estado = Estado;
      const body = { estado: estado };
      this.http.put(`http://localhost:8080/Proyecto2Clinica/EspecialidadesMedico?accion=CambiarEstado&IdEL=${Id}&Estado=${estado}`, body).subscribe(
        response => {
          alert('Se cambió el estado de la solicitud correctamente.');
          this.TablaAsignaciones();
        },
        error => {
          if (error.status === 400) {
            alert('Error al cambiar el estado de la solicitud: el ID ingresado no es válido.');
          } else {
            alert('Error al editar la exámen');
          }
        }
      );
    }
        //aceptar nuevo tipo de examen:
        aceptarExamen(id :any){
          const Id = id;
          const body = { Id: id };
          this.http.put(`http://localhost:8080/Proyecto2Clinica/Especialidades?accion=ActualizarEstadoAdmin&IdEL=${Id}`, body).subscribe(
            response => {
              alert('Se confirmo el nuevo exámen correctamente.');
              // Actualizar la lista de especialidades agregadas
              this.TablaDeExamanes();
            },
            error => {
              if (error.status === 400) {
                alert('Error al  confirmar el nuevo exámen.');
                this.TablaDeExamanes();
              } else {
                alert('Error al  confirmar el nuevo exámen');
                this.TablaDeExamanes();
              }
            }
          );
        }
          //eliminar nuevo tipo de examen:
          eliminarExamen(id :any){
            const Id = id;
            this.http.delete(`http://localhost:8080/Proyecto2Clinica/Especialidades?accion=ActualizarEstadoAdmin&IdEL=${Id}`).subscribe(
              response => {
                alert('Se eliminó la solicitud correctamente.');
                // Actualizar la lista de especialidades agregadas
                this.TablaDeExamanes();
              },
              error => {
                if (error.status === 400) {
                  alert('Se eliminó la solicitud correctamente');
                  this.TablaDeExamanes();
                } else {
                  alert('Se eliminó la solicitud correctamente');
                  this.TablaDeExamanes();
                }
              }
            );
          }
}
