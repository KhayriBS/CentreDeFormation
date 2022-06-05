import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Organisme } from '../organisme/organisme';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class OrganismeService {
    apiBaseUrl: 'http://localhost:8080'
  private apiServerUrl ='http://localhost:9090/organisme'

  constructor(private http: HttpClient){}

  public getOrganismes(): Observable<Organisme[]> {
    return this.http.get<Organisme[]>(`${this.apiServerUrl}`);
  }

  public addOrganisme(organisme: Organisme): Observable<Organisme> {
    return this.http.post<Organisme>(`${this.apiServerUrl}`, organisme);
  }

  public updateOrganisme(organisme: Organisme): Observable<Organisme> {
    return this.http.put<Organisme>(`${this.apiServerUrl}`, organisme);
  }
  
  public deleteOrganisme(organismeId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${organismeId}`);
  }
}