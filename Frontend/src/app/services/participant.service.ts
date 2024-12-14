import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Participant} from '../participant/Iparticipant';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class ParticipantService {
    apiBaseUrl: 'http://localhost:8080'
  private apiServerUrl ='http://localhost:9090/participant'

  constructor(private http: HttpClient){}

  public getParticipants(): Observable<Participant[]> {
    return this.http.get<Participant[]>(`${this.apiServerUrl}`);
  }

  public addParticipant(participant: Participant): Observable<Participant> {
    return this.http.post<Participant>(`${this.apiServerUrl}`, participant);
  } 



  public updateParticipant(participant: Participant): Observable<Participant> {
    return this.http.put<Participant>(`${this.apiServerUrl}`, participant);
  }

  public deleteParticipant(participantId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${participantId}`);
  }

}


