import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Formateur } from '../formateur/Iformateur';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class FormateurService {
    apiBaseUrl: 'http://localhost:8080'
  private apiServerUrl ='http://localhost:9090/formateur'

  constructor(private http: HttpClient){}

  public getFormateurs(): Observable<Formateur[]> {
    return this.http.get<Formateur[]>(`${this.apiServerUrl}`);
  }

  public addFormateur(formateur: Formateur): Observable<Formateur> {
    return this.http.post<Formateur>(`${this.apiServerUrl}`, formateur);
  }

  public updateFormateur(formateur: Formateur): Observable<Formateur> {
    return this.http.put<Formateur>(`${this.apiServerUrl}`, formateur);
  }
  public deleteFormateur(formateurId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${formateurId}`);
  } 
}