import { EquipeService } from './../../services/equipe.service';
import { Equipe } from './../../models/Equipe';
import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-equipe',
  templateUrl: './equipe.component.html',
  styleUrls: ['./equipe.component.css']
})
export class EquipeComponent implements OnInit {
  etudiants: Equipe[] = [];
  selectedEquipe: Equipe | null = null;
  newEtudiant: Equipe = new Equipe();
  showAddForm: boolean = false; // Add this line

  constructor(private equipeService: EquipeService) { }

  ngOnInit(): void {
    this.getEtudiants();
  }

  getEtudiants(): void {
    this.equipeService.getEtudiants()
      .subscribe(etudiants => this.etudiants = etudiants);
  }

  onSelect(etudiant: Equipe): void {
    this.selectedEquipe = etudiant;
  }

  addEtudiant(): void {
    this.equipeService.addEtudiant(this.newEtudiant)
      .subscribe(etudiant => {
        this.etudiants.push(etudiant);
        this.newEtudiant = new Equipe(); // Clear the form
      });
  }

  updateEtudiant(etudiant: Equipe): void {
    this.equipeService.updateEtudiant(etudiant)
      .subscribe(() => {
        // Update the student in the local list if needed
      });
  }

  deleteEtudiant(etudiant: Equipe): void {
    this.equipeService.deleteEtudiant(etudiant.idEquipe


      
    )
      .subscribe(() => {
        this.etudiants = this.etudiants.filter(e => e !== etudiant);
        this.selectedEquipe = null;
      });
  }
  toggleAddForm() { // Add this method
    this.showAddForm = !this.showAddForm;
  }
}
