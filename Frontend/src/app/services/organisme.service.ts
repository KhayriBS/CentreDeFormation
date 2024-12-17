import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Organisme } from '../organisme/Iorganisme';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class OrganismeService {

  constructor(private http: HttpClient){}

  public getOrganismes(): Observable<Organisme[]> {
    return this.http.get<Organisme[]>(`${environment.apiServerUrlOrganisme}`);
  }

  public addOrganisme(organisme: Organisme): Observable<Organisme> {
    return this.http.post<Organisme>(`${environment.apiServerUrlOrganisme}`, organisme);
  }

  public updateOrganisme(organisme: Organisme): Observable<Organisme> {
    return this.http.put<Organisme>(`${environment.apiServerUrlOrganisme}`, organisme);
  }
  
  public deleteOrganisme(organismeId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiServerUrlOrganisme}/${organismeId}`);
  }
}