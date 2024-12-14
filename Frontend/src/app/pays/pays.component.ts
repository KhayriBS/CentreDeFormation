import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PaysService } from '../services/pays.service';
import { Pays } from './Ipays';

@Component({
  selector: 'app-pays',
  templateUrl: './pays.component.html',
  styleUrls: ['./pays.component.css']
})
export class PaysComponent implements OnInit {
  public editPays: Pays;
  public pays: Pays[];
  cols: any[];
  public deletePays  : Pays
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  userName: string;
  userRoles: string;
  user: string = '';
  constructor(private paysService: PaysService) { }

  ngOnInit(): void {
    this.getPays();
    this.user = sessionStorage.getItem("roles");
    this.userName = sessionStorage.getItem("username");

   this.showAdminBoard = this.user.includes('ROLE_ADMIN');
    this.showUserBoard = this.user.includes('ROLE_USER');
    this.cols = [
      { field: 'id', header: 'Id' },
      { field: 'libelle', header: 'Pays' }
        ];

  }


  public getPays(): void {
    this.paysService.getPays().subscribe(
      (response: Pays[]) => {
        this.pays = response;
       
        console.log(this.pays);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onUpdatePays(pays: Pays): void {
    this.paysService.updatePays(pays).subscribe(
      (response: Pays) => {
        console.log(response);
        this.getPays();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeletePays(paysId: number): void {
    this.paysService.deletePays(paysId).subscribe(
      (response: void) => {
        console.log(response);
        this.getPays();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  
  public onAddPays(addForm: NgForm): void {
    
     this.paysService.addPays(addForm.value).subscribe(
       (response: Pays) => {
        this.getPays();
         console.log(response);
          addForm.reset();
          },
       (error: HttpErrorResponse) => {
         alert(error.message);
         addForm.reset();
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

   public onOpenModal(pays: Pays, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPaysModal');
    }
     
    if (mode === 'edit') {
      this.editPays = pays;
      button.setAttribute('data-target', '#updatePaysModal');
    }
    if (mode === 'delete') {
      this.deletePays = pays;
      button.setAttribute('data-target', '#deletePaysModal');
    }
    container.appendChild(button);
    button.click();
  }
}
