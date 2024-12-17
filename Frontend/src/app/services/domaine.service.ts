import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Domaine } from '../domaine/Idomaine';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class DomaineService {
 

  constructor(private http: HttpClient){}

  public getDomaine(): Observable<Domaine[]> {
    return this.http.get<Domaine[]>(`${environment.apiServerUrlDomaine}`);
  }

  public addDomaine(domaine: Domaine): Observable<Domaine> {
    return this.http.post<Domaine>(`${environment.apiServerUrlDomaine}`, domaine);
  }

  public deleteDomaine(domaineId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiServerUrlDomaine}/${domaineId}`);
  }

  public updateDomaine(domaine: Domaine): Observable<Domaine> {
    return this.http.put<Domaine>(`${environment.apiServerUrlDomaine}`, domaine);
  }





}