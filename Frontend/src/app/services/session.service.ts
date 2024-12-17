import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Domaine } from '../domaine/Idomaine';
import { Session } from '../session/Isession';
import { Participant } from '../participant/Iparticipant';
import { environment } from 'src/environments/environment';


@Injectable({providedIn: 'root'})
export class SessionService {

  constructor(private http: HttpClient){}

  public getSessions(): Observable<Session[]> {
    return this.http.get<Session[]>(`${environment.apiSessionUrl}`);
  }

  public addSession(session: Session): Observable<Session> {
    return this.http.post<Session>(`${environment.apiSessionUrl}`, session);
  }

  public getSessionById(sessionId: number): Observable<Session[]> {
    return this.http.get<Session[]>(`${environment.apiSessionUrl}/${sessionId}`);
  }

  public deleteSession(sessionId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiSessionUrl}/${sessionId}`);
  }
  public updateSession(session: Session): Observable<Session> {
    return this.http.put<Session>(`${environment.apiSessionUrl}`, session);
  }

  public addParticipantToSession(sessionId: number, participant : Participant): Observable<Participant> {
    return this.http.post<Participant>(`${environment.apiParticipantUrl}/${sessionId}`, participant   );
  }
 
}