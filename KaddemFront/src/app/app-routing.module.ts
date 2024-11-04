import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import {EtudiantComponent} from "../app/components/etudiant/etudiant.component";
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { UniversiteComponent } from './components/universite/universite.component';
import {ContratComponent} from "./components/contrat/contrat.component";
import { DepartementComponent } from './components/departement/departement.component';
import { EquipeComponent } from './components/equipe/equipe.component';



const routes: Routes = [
  { path: '', redirectTo: 'etudiant', pathMatch: 'full' }, // Root path redirects to 'etudiant'
  { path: 'etudiant', component: EtudiantComponent },
  { path: 'sidebar', component: SidebarComponent },
  { path: 'universite', component: UniversiteComponent },
  { path: 'contrat', component: ContratComponent },
  { path: 'departement', component: DepartementComponent },
  { path: 'equipe', component: EquipeComponent },

  



];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
