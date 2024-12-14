import { SessionService } from '../services/session.service';
import { Component, OnInit } from '@angular/core';
import { DomaineService } from '../services/domaine.service';
import { FormationService } from '../services/formation.service';
import { Formation } from '../formation/Iformation';
import { Session } from '../session/Isession';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-formation-details',
  templateUrl: './formation-details.component.html',
  styleUrls: ['./formation-details.component.css']
})
export class FormationDetailsComponent implements OnInit {
  public formationId : number;
  public sessions: Session[];
  public session : Session;
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  userName: string;
  userRoles: string;
  user: string = '';
  cols: any[];

  constructor(private route: ActivatedRoute, private router: Router,
    private formationService: FormationService,  private sessionService : SessionService) { }

  ngOnInit(): void {
    this.formationId = this.route.snapshot.params['formationId'];
this.geSessions();
this.user = sessionStorage.getItem("roles");
this.userName = sessionStorage.getItem("username");

this.showAdminBoard = this.user.includes('ROLE_ADMIN');
this.showUserBoard = this.user.includes('ROLE_USER');
this.cols = [

  { field: 'lieu', header: 'Lieu session' },

    ];

  }



  public addSessionToFormation(formationId: number, session: Session): void {
    this.formationService.addSessionToFormation(formationId, session).subscribe(
  (response: Session) => {
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


  public geSessions(): void {
    this.sessionService.getSessions().subscribe(
      (response: Session[]) => {
        this.sessions = response;
       console.log("frfr",this.sessions);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
 

  
  
}
