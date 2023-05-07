import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeMedicoComponent } from './home-medico/home-medico.component';
import { HomeLaboratoriosComponent } from './home-laboratorios/home-laboratorios.component';
import { HomeAdminComponent } from './home-admin/home-admin.component'; // importa el routing module de medicos
import { HomePacienteComponent } from './home-paciente/home-paciente.component'; // importa el routing module de medicos

const routes: Routes = [
  { 
    path: 'home-medico', 
    component: HomeMedicoComponent,
    children: [
      { path: '', redirectTo: 'pacientes', pathMatch: 'full' },
      { path: '**', redirectTo: 'pacientes' }
    ]
  },{ 
    path: 'home-laboratorios', 
    component: HomeLaboratoriosComponent
  },
  { 
    path: 'home-admin', 
    component: HomeAdminComponent
  },
  { 
    path: 'home-Paciente', 
    component: HomePacienteComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MedicosRoutingModule { }