import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ParticipantService } from '../services/participant.service';
import { Participant } from './Iparticipant';
import { OrganismeService } from '../services/organisme.service';
import { Organisme } from '../organisme/Iorganisme';
import { ProfilService } from '../services/profil.service';
import { PaysService } from '../services/pays.service';
import { Pays } from '../pays/Ipays';
import { NgForm } from '@angular/forms';
import { Profil } from '../profil/Iprofil';
@Component({
  selector: 'app-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.css']
})
export class ParticipantComponent implements OnInit {
  public participants: Participant[];
  public organismes: Organisme[];
  public profils: Profil[];
  public pays: Pays[];
  public detailsParticipant : Participant
  cols: any[];
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  userName: string;
  userRoles: string;
  user: string = '';

  public editParticipant: Participant;
  public deleteParticipant : Participant
  constructor(private participantService : ParticipantService, private organismeService : OrganismeService,
    private profilService: ProfilService,private paysService: PaysService) { }
  ngOnInit(): void {
    this.getParticipants();
    this.getOrganismes();
    this.getprofils();
    this.getPays();
    this.user = sessionStorage.getItem("roles")||'';
    this.userName = sessionStorage.getItem("username")||'';
    
    this.showAdminBoard = this.user.includes('ROLE_ADMIN');
    this.showUserBoard = this.user.includes('ROLE_USER');
    this.cols = [
      { field: 'nom', header: 'Nom participant' },
      { field: 'prenom', header: 'Prenom participant' },
     
     /*  { field: 'type', header: 'type' },
      { field: 'profil', header: 'profil' },
      { field: 'pays', header: 'pays' },
      { field: 'session', header: 'session' }, */
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
  public getParticipants(): void {
    this.participantService.getParticipants().subscribe(
      (response: Participant[]) => {
        this.participants = response;
       console.log(this.participants);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
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
  
  public onUpdateParticipant(participant: Participant): void {
    this.participantService.updateParticipant(participant).subscribe(
      (response: Participant) => {
        console.log(response);
        this.getParticipants();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onAddParticipant(addForm: NgForm): void {
         this.participantService.addParticipant(addForm.value).subscribe(
       (response: Participant) => {
        this.getParticipants();
         console.log(response);
          addForm.reset();
          },
       (error: HttpErrorResponse) => {
         alert(error.message);
         addForm.reset();
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

  public onDeleteParticipant(participantId: number): void {
    this.participantService.deleteParticipant(participantId).subscribe(
      (response: void) => {
        console.log(response);
        this.getParticipants();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


   public onOpenModal(participant: Participant, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPaysModal');
    }
    if (mode === 'details') {
      this.detailsParticipant = participant;
      button.setAttribute('data-target', '#detailsParticipantModal');
    }
    if (mode === 'edit') {
      this.editParticipant = participant;
      button.setAttribute('data-target', '#updateParticipantModal');
    }
    if (mode === 'delete') {
      this.deleteParticipant = participant;
      button.setAttribute('data-target', '#deleteParticipantModal');
    }
    container.appendChild(button);
    button.click();
  }
}