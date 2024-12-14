import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomaineService } from '../services/domaine.service';
import { NgForm } from '@angular/forms';
import { Domaine } from './Idomaine';

@Component({
  selector: 'app-domaine',
  templateUrl: './domaine.component.html',
  styleUrls: ['./domaine.component.css']
})
export class DomaineComponent implements OnInit {
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  userName: string;
  userRoles: string;
  user: string = '';
  public domaines: Domaine[];
  public deleteDomaine: Domaine;
  public editDomaine: Domaine;

  cols: any[];
  constructor(private domaineService: DomaineService) { }

  ngOnInit(): void {
    this.user = sessionStorage.getItem("roles");
    this.userName = sessionStorage.getItem("username");

   this.showAdminBoard = this.user.includes('ROLE_ADMIN');
    this.showUserBoard = this.user.includes('ROLE_USER');
    this.getDomaines();
    this.cols = [
      { field: 'id', header: 'Id' },
      { field: 'libelle', header: 'Domaine' },
    
        ];
  }

  public onUpdateDomaine(domaine: Domaine): void {
    this.domaineService.updateDomaine(domaine).subscribe(
      (response: Domaine) => {
        console.log(response);
        this.getDomaines();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public getDomaines(): void {
    this.domaineService.getDomaine().subscribe(
      (response: Domaine[]) => {
        this.domaines = response;
       console.log(this.domaines);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
   
  public onAddDomaine(addForm: NgForm): void {
         this.domaineService.addDomaine(addForm.value).subscribe(
       (response: Domaine) => {
        this.getDomaines();
         console.log(response);
          addForm.reset();
          },
       (error: HttpErrorResponse) => {
         alert(error.message);
         addForm.reset();
       }
     );
   }
   public onDeleteDomaine(domaineId: number): void {
    this.domaineService.deleteDomaine(domaineId).subscribe(
      (response: void) => {
        console.log(response);
        this.getDomaines();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

   public onOpenModal(domaine: Domaine, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPaysModal');
    }
    if (mode === 'delete') {
      
      this.deleteDomaine = domaine;
      button.setAttribute('data-target', '#deleteDomaineModal');
    }
    if (mode === 'edit') {
      
      this.editDomaine = domaine;
      button.setAttribute('data-target', '#updateDomaineModal');
    }
    if (mode === 'delete') {
      this.deleteDomaine = domaine;
      button.setAttribute('data-target', '#deleteDomaineModal');
    }
    container.appendChild(button);
    button.click();
  }
}