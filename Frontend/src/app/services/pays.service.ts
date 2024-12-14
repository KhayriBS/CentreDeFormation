import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pays } from '../pays/Ipays';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class PaysService {
    apiBaseUrl: 'http://localhost:8080'
  private apiServerUrl ='http://localhost:9090/pays'

  constructor(private http: HttpClient){}

  public getPays(): Observable<Pays[]> {
    return this.http.get<Pays[]>(`${this.apiServerUrl}`);
  }

  public addPays(pays: Pays): Observable<Pays> {
    return this.http.post<Pays>(`${this.apiServerUrl}`, pays);
  }
  public deletePays(paysId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${paysId}`);
  }
  public updatePays(pays: Pays): Observable<Pays> {
    return this.http.put<Pays>(`${this.apiServerUrl}`, pays);
  }

}