import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeLaboratoriosComponent } from './Componentes/home-laboratorios/home-laboratorios.component';
import { HomeAdminComponent } from './Componentes/home-admin/home-admin.component'; // importa el routing module de medicos

const routes: Routes = [
  { path: 'admin', component: HomeAdminComponent },
  { path: 'laboratorio', component: HomeLaboratoriosComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
