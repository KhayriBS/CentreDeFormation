import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profil } from '../profil/profiil';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class ProfilService {
    apiBaseUrl: 'http://localhost:8080'
  private apiServerUrl ='http://localhost:9090/profil'

  constructor(private http: HttpClient){}

  public getProfils(): Observable<Profil[]> {
    return this.http.get<Profil[]>(`${this.apiServerUrl}`);
  }

  public addProfil(profil: Profil): Observable<Profil> {
    return this.http.post<Profil>(`${this.apiServerUrl}`, profil);
  }

  public updateProfil(profil: Profil): Observable<Profil> {
    return this.http.put<Profil>(`${this.apiServerUrl}`, profil);
  }

  public deleteProfil(profilId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${profilId}`);
  }


}