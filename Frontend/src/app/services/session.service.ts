import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Domaine } from '../domaine/Idomaine';
import { Session } from '../session/Isession';
import { Participant } from '../participant/Iparticipant';
import { environment } from 'src/environments/environment';


@Injectable({providedIn: 'root'})
export class SessionService {
    apiBaseUrl: 'http://localhost:8080'
  private apiServerUrl ='http://localhost:9090/session'
  private apiServerUrl1 ='http://localhost:9090/participant'

  constructor(private http: HttpClient){}

  public getSessions(): Observable<Session[]> {
    return this.http.get<Session[]>(`${this.apiServerUrl}`);
  }

  public addSession(session: Session): Observable<Session> {
    return this.http.post<Session>(`${this.apiServerUrl}`, session);
  }

  public getSessionById(sessionId: number): Observable<Session[]> {
    return this.http.get<Session[]>(`${this.apiServerUrl}/${sessionId}`);
  }

  public deleteSession(sessionId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${sessionId}`);
  }
  public updateSession(session: Session): Observable<Session> {
    return this.http.put<Session>(`${this.apiServerUrl}`, session);
  }

  public addParticipantToSession(sessionId: number, participant : Participant): Observable<Participant> {
    return this.http.post<Participant>(`${this.apiServerUrl1}/${sessionId}`, participant   );
  }
 
}