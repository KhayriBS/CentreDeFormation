import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Formateur } from '../formateur/Iformateur';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class FormateurService {

  constructor(private http: HttpClient){}

  public getFormateurs(): Observable<Formateur[]> {
    return this.http.get<Formateur[]>(`${environment.apiServerUrlFormateur}`);
  }

  public addFormateur(formateur: Formateur): Observable<Formateur> {
    return this.http.post<Formateur>(`${environment.apiServerUrlFormateur}`, formateur);
  }

  public updateFormateur(formateur: Formateur): Observable<Formateur> {
    return this.http.put<Formateur>(`${environment.apiServerUrlFormateur}`, formateur);
  }
  public deleteFormateur(formateurId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiServerUrlFormateur}/${formateurId}`);
  } 
}