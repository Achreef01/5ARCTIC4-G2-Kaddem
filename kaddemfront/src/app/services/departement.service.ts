import { Departement } from './../models/Departement';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Etudiant } from '../models/Etudiant'; 

@Injectable({
  providedIn: 'root'
})
export class DepartementService {
  private baseUrl = 'http://192.168.8.151:8089/kaddem/departement'; 

  constructor(private http: HttpClient) { }

  // Récupère la liste de tous les étudiants
  getDepartements(): Observable<Departement[]> {
    return this.http.get<Departement[]>(`${this.baseUrl}/retrieve-all-departements`);
  }

  // Récupère un étudiant par son identifiant
  getDepartement(id: number): Observable<Etudiant> {
    return this.http.get<Etudiant>(`${this.baseUrl}/retrieve-departement/${id}`);
  }

  // Ajoute un nouvel étudiant
  addDepartement(departement: Departement): Observable<Departement> {
    return this.http.post<Departement>(`${this.baseUrl}/add-departement`, departement);
  }

  // Met à jour les informations d'un étudiant
  updateDepartement(departement: Departement): Observable<Departement> {
    return this.http.put<Departement>(`${this.baseUrl}/update-departement`, departement);
  }

  // Supprime un étudiant par son identifiant
  deleteDepartement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/remove-departement/${id}`);
  }
}
