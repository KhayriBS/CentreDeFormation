import { Component, OnInit } from '@angular/core';
import { OrganismeService } from '../services/organisme.service';
import { FormateurService } from '../services/formateur.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { Organisme } from '../organisme/Iorganisme';
import { Formateur } from './Iformateur';
@Component({
  selector: 'app-formateur',
  templateUrl: './formateur.component.html',
  styleUrls: ['./formateur.component.css']
})
export class FormateurComponent implements OnInit {
  public organismes: Organisme[];
  public formateurs: Formateur[];
  public editFormateur: Formateur;
  public deleteFormateur : Formateur;
  public detailsFormateur : Formateur
  cols: any[];
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  userName: string;
  userRoles: string;
  user: string = '';
  constructor(private organismeService : OrganismeService, private formateurService : FormateurService) { }

  ngOnInit(): void {
    this.getOrganismes();
    this.getFormateurs();
    this.user = sessionStorage.getItem("roles")||'';
    this.userName = sessionStorage.getItem("username")||'';

   this.showAdminBoard = this.user.includes('ROLE_ADMIN');
    this.showUserBoard = this.user.includes('ROLE_USER');
    this.cols = [
     
      { field: 'nom', header: 'Nom formateur' },
      { field: 'prenom', header: 'Prenom formateur' },
   
   
     /*  { field: 'type', header: 'type' },
      { field: 'profil', header: 'profil' },
      { field: 'pays', header: 'pays' },
      { field: 'session', header: 'session' }, */
      ];
  }

  public getOrganismes(): void {
    this.organismeService.getOrganismes().subscribe(
      (response: Organisme[]) => {
        this.organismes = response;
       console.log(this.organismes);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteFormateur(formateurId: number): void {
    this.formateurService.deleteFormateur(formateurId).subscribe(
      (response: void) => {
        console.log(response);
        this.getFormateurs();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onUpdateFormateur(formateur: Formateur): void {
    this.formateurService.updateFormateur(formateur).subscribe(
      (response: Formateur) => {
        console.log(response);
        this.getFormateurs();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public onAddFormateur(addForm: NgForm): void {
    this.formateurService.addFormateur(addForm.value).subscribe(
  (response: Formateur) => {
   this.getFormateurs();
    console.log(response);
     addForm.reset();
     },
  (error: HttpErrorResponse) => {
    alert(error.message);
    addForm.reset();
  }
);
}

  public getFormateurs(): void {
    this.formateurService.getFormateurs().subscribe(
      (response: Formateur[]) => {
        this.formateurs = response;
       console.log(this.formateurs);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  
  public onOpenModal(formateur: Formateur, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPaysModal');
    }
   
    if (mode === 'edit') {
      this.editFormateur = formateur;
      button.setAttribute('data-target', '#updateFormateurModal');
    }
    if (mode === 'details') {
      this.detailsFormateur = formateur;
      button.setAttribute('data-target', '#detailsFormateurModal');
    }
    if (mode === 'delete') {
      this.deleteFormateur = formateur;
      button.setAttribute('data-target', '#deleteFormateurModal');
    }
    container.appendChild(button);
    button.click();
  }

}
