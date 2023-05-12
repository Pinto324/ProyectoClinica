import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-menu-porcentajes-admin',
  templateUrl: './menu-porcentajes-admin.component.html',
  styleUrls: ['./menu-porcentajes-admin.component.css']
})
export class MenuPorcentajesAdminComponent {
  protected mostrarExamen = false;
  protected mostrarEspecialidad = false;
  protected mostrarTodo = false;
  protected info: {Id: string, IdExamen: string ,Nombre: string, Porcentaje: string, NuevoPorcentaje: number}[] = [];
  protected tabla: any[] = [];
  constructor(private http: HttpClient) { }
  //metodos de examenes:
  //get de examenes
  getExamenes() {
    const url = 'http://localhost:8080/Proyecto2Clinica/ControladorPorcentajes?accion=PorcentajeExamenes';
    return this.http.get<any[]>(url);
  }
  CargarExamenes(){
    this.info = [];
    this.getExamenes().subscribe(examenes => {
      this.tabla = examenes;
      this.CerrarTodo();
      for (let i = 0; i < this.tabla.length; i += 4) {
        this.info.push({
          Id: this.tabla[i],
          IdExamen: this.tabla[i + 1],
          Nombre: this.tabla[i + 2],
          Porcentaje: this.tabla[i + 3],
          NuevoPorcentaje: 0
        });
      }
      this.mostrarTodo = true;
      this.mostrarExamen = true;
    });
  }

  //metodo para editar el porcentaje de especialidad:
    editarExamen(especialidad:any) {
      if(this.ComprobarNumero(parseInt(especialidad.NuevoPorcentaje))){
        const idEspecialidad = especialidad.IdExamen;
        const precio = (parseInt(especialidad.NuevoPorcentaje)/100);
        const body = { Precio: precio };
        this.http.post(`http://localhost:8080/Proyecto2Clinica/ControladorPorcentajes?accion=NuevoPorcentajeExamen&IdEM=${idEspecialidad}&Porcentaje=${precio}`, body).subscribe(
          response => {
            alert('Porcentaje editado correctamente.');
            this.CargarExamenes();
            // Actualizar la lista de especialidades agregadas
          },
          error => {
            if (error.status === 400) {
              alert('Error al editar la especialidad: el ID ingresado no es válido.');
            } else {
              alert('Error al editar la especialidad');
            }
          }
        );
      }
     }


     ////metodos especialidades:
     getEspecialidades() {
      const url = 'http://localhost:8080/Proyecto2Clinica/ControladorPorcentajes?accion=PorcentajeEspecialidades';
      return this.http.get<any[]>(url);
    }
    CargarEspecialidades(){
      this.info = [];
      this.getEspecialidades().subscribe(examenes => {
        this.tabla = examenes;
        this.CerrarTodo();
        for (let i = 0; i < this.tabla.length; i += 4) {
          this.info.push({
            Id: this.tabla[i],
            IdExamen: this.tabla[i + 1],
            Nombre: this.tabla[i + 2],
            Porcentaje: this.tabla[i + 3],
            NuevoPorcentaje: 0
          });
        }
        this.mostrarTodo = true;
        this.mostrarEspecialidad = true;
      });
    }
  
    //metodo para editar el porcentaje de especialidad:
      editarEspecialidad(especialidad:any) {
        if(this.ComprobarNumero(parseInt(especialidad.NuevoPorcentaje))){
          const idEspecialidad = especialidad.IdExamen;
          const precio = (parseInt(especialidad.NuevoPorcentaje)/100);
          const body = { Precio: precio };
          this.http.post(`http://localhost:8080/Proyecto2Clinica/ControladorPorcentajes?accion=NuevoPorcentajeEspecialidad&IdEM=${idEspecialidad}&Porcentaje=${precio}`, body).subscribe(
            response => {
              alert('Porcentaje editado correctamente.');
              this.CargarEspecialidades();
              // Actualizar la lista de especialidades agregadas
            },
            error => {
              if (error.status === 400) {
                alert('Error al editar la especialidad: el ID ingresado no es válido.');
              } else {
                alert('Error al editar la especialidad');
              }
            }
          );
        }
      }


     //utilidades
     ComprobarNumero(nuevo: number):boolean{
      if(nuevo>0 && nuevo<=100){
        return true;
      }else{
        alert("el numero de porcentaje no puede ser mayor a 100 y menor a 0");
        return false;
      }
     }
     CerrarTodo(){
      this.mostrarEspecialidad = false;
      this.mostrarExamen = false;
      this.mostrarTodo = false;
     }
    }
