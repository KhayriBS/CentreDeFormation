import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pays } from '../pays/Ipays';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class PaysService {
  

  constructor(private http: HttpClient){}

  public getPays(): Observable<Pays[]> {
    return this.http.get<Pays[]>(`${environment.apiServerUrlPays}`);
  }

  public addPays(pays: Pays): Observable<Pays> {
    return this.http.post<Pays>(`${environment.apiServerUrlPays}`, pays);
  }
  public deletePays(paysId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiServerUrlPays}/${paysId}`);
  }
  public updatePays(pays: Pays): Observable<Pays> {
    return this.http.put<Pays>(`${environment.apiServerUrlPays}`, pays);
  }

}