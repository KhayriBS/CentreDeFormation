import { Component, OnInit } from '@angular/core';
import { DomaineService } from '../services/domaine.service';
import { FormationService } from '../services/formation.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Domaine } from '../domaine/Domaine';
import { Formation } from './formation';
@Component({
  selector: 'app-formation',
  templateUrl: './formation.component.html',
  styleUrls: ['./formation.component.css']
})
export class FormationComponent implements OnInit {
  public domaines: Domaine[];
  public formations: Formation[];
  public formationId : number;
  public editFormation: Formation;
  public deleteFormation : Formation
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  userName: string;
  userRoles: string;
  user: string = '';
  cols: any[];
  clicked = false;
  constructor(private route: ActivatedRoute, private router: Router, private domaineService: DomaineService, private formationService: FormationService) { }

  ngOnInit(): void {
    this.user = sessionStorage.getItem("roles");
    this.userName = sessionStorage.getItem("username");

   this.showAdminBoard = this.user.includes('ROLE_ADMIN');
    this.showUserBoard = this.user.includes('ROLE_USER');
    this.getDomaines();
    this.getFormations();

    this.cols = [
      { field: 'titre', header: 'Titre de formation' },
      
    
      ];
  }
  formationById(formationId: number){
    this.router.navigate(['formation',formationId ]);
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
  public onUpdateFormation(formation: Formation): void {
    this.formationService.updateFormation(formation).subscribe(
      (response: Formation) => {
        console.log(response);
        this.getFormations();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getFormations(): void {
    this.formationService.getFomration().subscribe(
      (response: Formation[]) => {
        this.formations = response;
       console.log(this.formations);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteFormation(formationId: number): void {
    this.formationService.deleteFormation(formationId).subscribe(
      (response: void) => {
        console.log(response);
        this.getFormations();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  
  public onAddFormation(addForm: NgForm): void {
    this.formationService.addFormation(addForm.value).subscribe(
  (response: Formation) => {
   this.getFormations();
    console.log(response);
     addForm.reset();
     },
  (error: HttpErrorResponse) => {
    alert(error.message);
    addForm.reset();
  }
);
}




public onOpenModal(formation: Formation, mode: string): void {
const container = document.getElementById('main-container');
const button = document.createElement('button');
button.type = 'button';
button.style.display = 'none';
button.setAttribute('data-toggle', 'modal');
if (mode === 'add') {
 button.setAttribute('data-target', '#addPaysModal');
}
if (mode === 'details') {
  this.editFormation = formation;
  button.setAttribute('data-target', '#detailsFormationModal');
 }


if (mode === 'edit') {
  this.editFormation = formation;
  button.setAttribute('data-target', '#updateFormationModal');
 }
if (mode === 'delete') {
 this.deleteFormation = formation;
 button.setAttribute('data-target', '#deleteFormationModal');
}
container.appendChild(button);
button.click();
}
}
