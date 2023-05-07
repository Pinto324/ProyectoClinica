import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './Componentes/login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HomeMedicoComponent } from './Componentes/home-medico/home-medico.component';
import { MedicosRoutingModule } from './Componentes/medicos-routing.module';
import { MedicoMenuEspecialidadesComponent } from './Componentes/home-medico/medico-menu-especialidades/medico-menu-especialidades.component';
import { MedicoHorariosComponent } from './Componentes/home-medico/medico-horarios/medico-horarios.component';
import { MedicoMenuConsultasComponent } from './Componentes/home-medico/medico-menu-consultas/medico-menu-consultas.component';
import { FormConsultaComponent } from './Componentes/home-medico/medico-menu-consultas/form-consulta/form-consulta.component';
import { ModalAgregarExamenesConsultaComponent } from './Componentes/home-medico/medico-menu-consultas/form-consulta/modal-agregar-examenes-consulta/modal-agregar-examenes-consulta.component';
import { ModalInformefinalComponent } from './Componentes/home-medico/medico-menu-consultas/form-consulta/modal-informefinal/modal-informefinal.component';
import { MedicoMenuHistorialComponent } from './Componentes/home-medico/medico-menu-historial/medico-menu-historial.component';
import { MenuReportesMedicosComponent } from './Componentes/home-medico/menu-reportes-medicos/menu-reportes-medicos.component';
import { HomeLaboratoriosComponent } from './Componentes/home-laboratorios/home-laboratorios.component';
import { MenuExamenesComponent } from './Componentes/home-laboratorios/menu-examenes/menu-examenes.component'; // importa el routing module de medicos
import { MenuSolicitudesComponent } from './Componentes/home-laboratorios/menu-solicitudes/menu-solicitudes.component';
import { ModalAgregarExamenesComponent } from './Componentes/home-laboratorios/menu-solicitudes/modal-agregar-examenes/modal-agregar-examenes.component';
import { HomeAdminComponent } from './Componentes/home-admin/home-admin.component';
import { HomePacienteComponent } from './Componentes/home-paciente/home-paciente.component'; // importa el routing module de medicos
import { NgxFileDropModule } from 'ngx-file-drop';
import { MenuReportesComponent } from './menu-reportes/menu-reportes.component';
import { MenuSolicitarConsultaComponent } from './Componentes/home-paciente/menu-solicitar-consulta/menu-solicitar-consulta.component';
import { ModalNuevoConsultaComponent } from './Componentes/home-paciente/menu-solicitar-consulta/modal-nuevo-consulta/modal-nuevo-consulta.component';
const routes: Routes = [
  { path: 'login', component: LoginComponent },
  // Ruta predeterminada que redirige al usuario a la página de inicio
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  // Ruta para manejar cualquier otra ruta que no exista
  { path: '**', redirectTo: '/login' },
  //ruta medico
  {path: 'home-medico', component: HomeMedicoComponent},
  //ruta Laboratorio
  { path: 'home-laboratorios', component: HomeLaboratoriosComponent },
  { path: 'home-Admin', component: HomeAdminComponent },
  {path: 'home-Paciente', component: HomePacienteComponent}
];
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeMedicoComponent,
    MedicoMenuEspecialidadesComponent,
    MedicoHorariosComponent,
    MedicoMenuConsultasComponent,
    FormConsultaComponent,
    ModalAgregarExamenesConsultaComponent,
    ModalInformefinalComponent,
    MedicoMenuHistorialComponent,
    MenuReportesMedicosComponent,
    HomeLaboratoriosComponent,
    MenuExamenesComponent,
    MenuSolicitudesComponent,
    ModalAgregarExamenesComponent,
    HomeAdminComponent,
    HomePacienteComponent,
    MenuReportesComponent,
    MenuSolicitarConsultaComponent,
    ModalNuevoConsultaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    MedicosRoutingModule,
    NgxFileDropModule
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
