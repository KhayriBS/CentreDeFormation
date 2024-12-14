import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Domaine } from '../domaine/Idomaine';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class DomaineService {
    apiBaseUrl: 'http://localhost:8080'
  private apiServerUrl ='http://localhost:9090/domaine'

  constructor(private http: HttpClient){}

  public getDomaine(): Observable<Domaine[]> {
    return this.http.get<Domaine[]>(`${this.apiServerUrl}`);
  }

  public addDomaine(domaine: Domaine): Observable<Domaine> {
    return this.http.post<Domaine>(`${this.apiServerUrl}`, domaine);
  }

  public deleteDomaine(domaineId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${domaineId}`);
  }

  public updateDomaine(domaine: Domaine): Observable<Domaine> {
    return this.http.put<Domaine>(`${this.apiServerUrl}`, domaine);
  }





}