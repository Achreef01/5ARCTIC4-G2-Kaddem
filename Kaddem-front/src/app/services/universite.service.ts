import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Universite } from '../models/Universite';

@Injectable({
  providedIn: 'root',
})
export class UniversiteService {
  private baseUrl = 'http://192.168.28.144:8089/kaddem/universite'; // Update the base URL

  constructor(private http: HttpClient) {}

  // Retrieve the list of all universities
  getUniversites(): Observable<Universite[]> {
    return this.http.get<Universite[]>(`${this.baseUrl}/retrieve-all-universites`);
  }

  // Retrieve a university by its ID
  getUniversite(id: number): Observable<Universite> {
    return this.http.get<Universite>(`${this.baseUrl}/retrieve-universite/${id}`);
  }

  // Add a new university
  addUniversite(universite: Universite): Observable<Universite> {
    return this.http.post<Universite>(`${this.baseUrl}/add-universite`, universite);
  }

  // Update the information of a university
  updateUniversite(universite: Universite): Observable<Universite> {
    return this.http.put<Universite>(`${this.baseUrl}/update-universite`, universite);
  }
  deleteUniversite(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/remove-universite/${id}`);
  }

  
}
