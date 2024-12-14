import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FormateurService } from '../services/formateur.service';
import { OrganismeService } from '../services/organisme.service';
import { Session } from '../session/Isession';
import { SessionService } from '../services/session.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ParticipantService } from '../services/participant.service';
import { Participant } from '../participant/Iparticipant';

@Component({
  selector: 'app-session-details',
  templateUrl: './session-details.component.html',
  styleUrls: ['./session-details.component.css']
})
export class SessionDetailsComponent implements OnInit {
  public sessionId : number;
  cols: any[];
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  userName: string;
  userRoles: string;
  user: string = '';
  public participants: Participant[];
  clicked = false;
  constructor( private route: ActivatedRoute, private router: Router, private participantService : ParticipantService,
    private sessionService : SessionService, private organismeService: OrganismeService,
    private formateurService: FormateurService) { }

  ngOnInit(): void {
    this.sessionId = this.route.snapshot.params['sessionId'];


    
    this.user = sessionStorage.getItem("roles");
    this.userName = sessionStorage.getItem("username");
    
    this.showAdminBoard = this.user.includes('ROLE_ADMIN');
    this.showUserBoard = this.user.includes('ROLE_USER');
    this.getParticipants();
    
    this.cols = [
      { field: 'nom', header: 'Nom participant' },
      { field: 'prenom', header: 'Prenom participant' },
     
    
      ];
  }
  
  public addParticipantToSession(sessionId: number, participant: Participant): void {
    this.sessionService.addParticipantToSession(sessionId, participant).subscribe(
  (response: Participant) => {
  // this.getParticipants();
    console.log("repoonsee",response);
    /// addForm.reset();
     },
  (error: HttpErrorResponse) => {
    alert(error.message);
   // addForm.reset();
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
}
