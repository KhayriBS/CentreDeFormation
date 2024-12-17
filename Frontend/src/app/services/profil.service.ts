import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profil } from '../profil/Iprofil';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class ProfilService {

  constructor(private http: HttpClient){}

  public getProfils(): Observable<Profil[]> {
    return this.http.get<Profil[]>(`${environment.apiServerUrlProfil}`);
  }

  public addProfil(profil: Profil): Observable<Profil> {
    return this.http.post<Profil>(`${environment.apiServerUrlProfil}`, profil);
  }

  public updateProfil(profil: Profil): Observable<Profil> {
    return this.http.put<Profil>(`${environment.apiServerUrlProfil}`, profil);
  }

  public deleteProfil(profilId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiServerUrlProfil}/${profilId}`);
  }


}