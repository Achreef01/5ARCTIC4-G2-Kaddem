import { Equipe } from './../models/Equipe';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EquipeService {
  private baseUrl = 'http://backend_ctr:8089/kaddem/equipe'; 

  constructor(private http: HttpClient) { }

  // Récupère la liste de tous les étudiants
  getEtudiants(): Observable<Equipe[]> {
    return this.http.get<Equipe[]>(`${this.baseUrl}/retrieve-all-equipes`);
  }

  // Récupère un étudiant par son identifiant
  getEtudiant(id: number): Observable<Equipe> {
    return this.http.get<Equipe>(`${this.baseUrl}/retrieve-equipe/${id}`);
  }

  // Ajoute un nouvel étudiant
  addEtudiant(etudiant: Equipe): Observable<Equipe> {
    return this.http.post<Equipe>(`${this.baseUrl}/add-equipe`, etudiant);
  }

  // Met à jour les informations d'un étudiant
  updateEtudiant(etudiant: Equipe): Observable<Equipe> {
    return this.http.put<Equipe>(`${this.baseUrl}/update-equipe`, etudiant);
  }

  // Supprime un étudiant par son identifiant
  deleteEtudiant(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/remove-equipe/${id}`);
  }
}
