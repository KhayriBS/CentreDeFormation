import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Participant} from '../participant/Iparticipant';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class ParticipantService {

  constructor(private http: HttpClient){}

  public getParticipants(): Observable<Participant[]> {
    return this.http.get<Participant[]>(`${environment.apiServerUrlParticipant}`);
  }

  public addParticipant(participant: Participant): Observable<Participant> {
    return this.http.post<Participant>(`${environment.apiServerUrlParticipant}`, participant);
  } 



  public updateParticipant(participant: Participant): Observable<Participant> {
    return this.http.put<Participant>(`${environment.apiServerUrlParticipant}`, participant);
  }

  public deleteParticipant(participantId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiServerUrlParticipant}/${participantId}`);
  }

}


