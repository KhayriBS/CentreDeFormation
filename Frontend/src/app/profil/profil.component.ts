import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ProfilService } from '../services/profil.service';
import { Profil } from './profiil';
@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

  cols: any[];
  public profils: Profil[];
  public editProfil: Profil;
  public deleteProfil : Profil;
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  userName: string;
  userRoles: string;
  user: string = '';
  constructor(private profilService: ProfilService) { }

  ngOnInit(): void {
    this.user = sessionStorage.getItem("roles");
    this.userName = sessionStorage.getItem("username");

   this.showAdminBoard = this.user.includes('ROLE_ADMIN');
    this.showUserBoard = this.user.includes('ROLE_USER');
    this.getprofils();
    this.cols = [
      { field: 'id', header: 'Id' },
      { field: 'libelle', header: 'Profil' }
        ];
  }


  public getprofils(): void {
    this.profilService.getProfils().subscribe(
      (response: Profil[]) => {
        this.profils = response;
       
        console.log(this.profils);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
 
  
  public onAddProfil(addForm: NgForm): void {
    
     this.profilService.addProfil(addForm.value).subscribe(
       (response: Profil) => {
        this.getprofils();
         console.log(response);
          addForm.reset();
          },
       (error: HttpErrorResponse) => {
         alert(error.message);
         addForm.reset();
       }
     );
   }


   public onDeletProfil(profilId: number): void {
    this.profilService.deleteProfil(profilId).subscribe(
      (response: void) => {
        console.log(response);
        this.getprofils();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onUpdateProfil(profil: Profil): void {
    this.profilService.updateProfil(profil).subscribe(
      (response: Profil) => {
        console.log(response);
        this.getprofils();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  /*


   public onUpdatePool(pool: Pool): void {
    this.poolService.updatePool(pool).subscribe(
      (response: Pool) => {
        console.log(response);
        this.getPools();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
*/

   public onOpenModal(Profil: Profil, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPaysModal');
    }
    if (mode === 'edit') {
      this.editProfil = Profil;
      button.setAttribute('data-target', '#updateProfilModal');
    }
    if (mode === 'delete') {
      this.deleteProfil = Profil;
      button.setAttribute('data-target', '#deleteProfilModal');
    }
    container.appendChild(button);
    button.click();
  }
}