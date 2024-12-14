import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { OrganismeService } from '../services/organisme.service';
import { NgForm } from '@angular/forms';
import { Organisme } from './organisme';

@Component({
  selector: 'app-organisme',
  templateUrl: './organisme.component.html',
  styleUrls: ['./organisme.component.css']
})
export class OrganismeComponent implements OnInit {
  public organismes: Organisme[];
  cols: any[];
  public editOrganisme: Organisme;
  public deleteOrganisme : Organisme;
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  userName: string;
  userRoles: string;
  user: string = '';
  constructor(private organismeService: OrganismeService) { }

  ngOnInit(): void {
    this.getOrganismes();
    this.user = sessionStorage.getItem("roles");
    this.userName = sessionStorage.getItem("username");

   this.showAdminBoard = this.user.includes('ROLE_ADMIN');
    this.showUserBoard = this.user.includes('ROLE_USER');
    this.cols = [
      { field: 'id', header: 'Id' },
      { field: 'libelle', header: 'Organisme' }
        ];


  }

  public onUpdateOrganisme(organisme: Organisme): void {
    this.organismeService.updateOrganisme(organisme).subscribe(
      (response: Organisme) => {
        console.log(response);
        this.getOrganismes();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
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
  public onDeleteOrganisme(organismeId: number): void {
    this.organismeService.deleteOrganisme(organismeId).subscribe(
      (response: void) => {
        console.log(response);
        this.getOrganismes();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
   
  public onAddOrganisme(addForm: NgForm): void {
         this.organismeService.addOrganisme(addForm.value).subscribe(
       (response: Organisme) => {
        this.getOrganismes();
         console.log(response);
          addForm.reset();
          },
       (error: HttpErrorResponse) => {
         alert(error.message);
         addForm.reset();
       }
     );
   }


   public onOpenModal(organisme: Organisme, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPaysModal');
    }
    if (mode === 'edit') {
      this.editOrganisme = organisme;
      button.setAttribute('data-target', '#updateOrganismeModal');
    }
    if (mode === 'delete') {
      this.deleteOrganisme= organisme;
      button.setAttribute('data-target', '#deleteOrganismeModal');
    }
    container.appendChild(button);
    button.click();
  }
}