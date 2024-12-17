import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Formation } from '../formation/Iformation';
import { environment } from 'src/environments/environment';
import { Session } from '../session/Isession';
@Injectable({providedIn: 'root'})
export class FormationService {

  constructor(private http: HttpClient){}

  public getFomration(): Observable<Formation[]> {
    return this.http.get<Formation[]>(`${environment.apiServerUrlFormation}`);
  }

  public addFormation(formation: Formation): Observable<Formation> {
    return this.http.post<Formation>(`${environment.apiServerUrlFormation}`, formation);
  }

 


  public addSessionToFormation(formationId: number, session : Session): Observable<Session> {
    return this.http.post<Session>(`${environment.apiSessionUrl}/${formationId}`, session   );
  }


  public getFormationById(formationId: number): Observable<Formation[]> {
    return this.http.get<Formation[]>(`${environment.apiSessionUrl}/${formationId}/session`);
  }

  public updateFormation(formation: Formation): Observable<Formation> {
    return this.http.put<Formation>(`${environment.apiServerUrlFormation}`, formation);
  }

  public deleteFormation(formationId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiServerUrlFormation}/${formationId}`);
  }

}