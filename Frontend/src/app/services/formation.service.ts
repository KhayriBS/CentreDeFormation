import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Formation } from '../formation/Iformation';
import { environment } from 'src/environments/environment';
import { Session } from '../session/Isession';
@Injectable({providedIn: 'root'})
export class FormationService {
    apiBaseUrl: 'http://localhost:8080'
  private apiServerUrl ='http://localhost:9090/formation'

  private apiServerUrl1 ='http://localhost:9090/session'
  constructor(private http: HttpClient){}

  public getFomration(): Observable<Formation[]> {
    return this.http.get<Formation[]>(`${this.apiServerUrl}`);
  }

  public addFormation(formation: Formation): Observable<Formation> {
    return this.http.post<Formation>(`${this.apiServerUrl}`, formation);
  }

 


  public addSessionToFormation(formationId: number, session : Session): Observable<Session> {
    return this.http.post<Session>(`${this.apiServerUrl1}/${formationId}`, session   );
  }


  public getFormationById(formationId: number): Observable<Formation[]> {
    return this.http.get<Formation[]>(`${this.apiServerUrl1}/${formationId}/session`);
  }

  public updateFormation(formation: Formation): Observable<Formation> {
    return this.http.put<Formation>(`${this.apiServerUrl}`, formation);
  }

  public deleteFormation(formationId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${formationId}`);
  }

}