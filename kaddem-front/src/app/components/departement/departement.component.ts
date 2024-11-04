import { Departement } from './../../models/Departement';
import { Component, OnInit } from '@angular/core';
import { Etudiant } from '../../models/Etudiant';
import { DepartementService } from '../../services/departement.service';

@Component({
  selector: 'app-departement',
  templateUrl: './departement.component.html',
  styleUrls: ['./departement.component.css']
})
export class DepartementComponent implements OnInit {
  departements: Departement[] = [];
  selectedEtudiant: Departement | null = null;
  newDepartement: Departement = new Departement();
  showAddForm: boolean = false; // Add this line

  constructor(private departementService: DepartementService) { }

  ngOnInit(): void {
    this.getEtudiants();
  }

  getEtudiants(): void {
    this.departementService.getDepartements()
      .subscribe(departements => this.departements = departements);
  }

  onSelect(departement: Departement): void {
    this.selectedEtudiant = departement;
  }

  addEtudiant(): void {
    this.departementService.addDepartement(this.newDepartement)
      .subscribe(departement => {
        this.departements.push(departement);
        this.newDepartement = new Departement(); // Clear the form
      });
  }

  updateEtudiant(etudiant: Departement): void {
    this.departementService.updateDepartement(etudiant)
      .subscribe(() => {
        // Update the student in the local list if needed
      });
  }

  deleteEtudiant(etudiant: Departement): void {
    this.departementService.deleteDepartement(etudiant.idDepart)
      .subscribe(() => {
        this.departements = this.departements.filter(e => e !== etudiant);
        this.selectedEtudiant = null;
      });
  }
  toggleAddForm() { // Add this method
    this.showAddForm = !this.showAddForm;
  }
}
