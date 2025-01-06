import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FormateurService } from '../services/formateur.service';
import { OrganismeService } from '../services/organisme.service';
import { Session } from './Isession';
import { SessionService } from '../services/session.service';
import { Organisme } from '../organisme/Iorganisme';
import { ActivatedRoute, Router } from '@angular/router';
import { Formateur } from '../formateur/Iformateur';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent implements OnInit {
  public sessions: Session[];
  public organismes: Organisme[];
  public formateurs: Formateur[];
  public sessionId : number;
  public deleteSession: Session;
  public editSession: Session;
  cols: any[];
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  userName: string;
  userRoles: string;
  user: string = '';
  constructor( private route: ActivatedRoute, private router: Router, 
    private sessionService : SessionService, private organismeService: OrganismeService,
    private formateurService: FormateurService) { }
v
  ngOnInit(): void {
   
  
    this.user = sessionStorage.getItem("roles")||'';
    this.userName = sessionStorage.getItem("username")||'';
    
    this.showAdminBoard = this.user.includes('ROLE_ADMIN');
    this.showUserBoard = this.user.includes('ROLE_USER');
    this.getOrganismes();
    this.getFormateurs();
    this.geSessions();
    this.cols = [
      { field: 'lieu', header: 'Lieu de session' },
             ];
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

  public onUpdateSession(session: Session): void {
    this.sessionService.updateSession(session).subscribe(
      (response: Session) => {
        console.log(response);
        this.geSessions(); // getSessions
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
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

  sessionById(sessionId: number){
    this.router.navigate(['session',sessionId ]);
  }

  public onAddSession(addForm: NgForm): void {
    this.sessionService.addSession(addForm.value).subscribe(
  (response: Session) => {
   this.geSessions();
    console.log(response);
     addForm.reset();
     },
  (error: HttpErrorResponse) => {
    alert(error.message);
    addForm.reset();
  }
);
}

public onDeleteSession(sessionId: number): void {
  this.sessionService.deleteSession(sessionId).subscribe(
    (response: void) => {
      console.log(response);
      this.geSessions();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}



public onOpenModal(session: Session, mode: string): void {
const container = document.getElementById('main-container');
const button = document.createElement('button');
button.type = 'button';
button.style.display = 'none';
button.setAttribute('data-toggle', 'modal');
if (mode === 'add') {
 button.setAttribute('data-target', '#addPaysModal');
}
if (mode === 'details') {
  this.editSession = session;
  button.setAttribute('data-target', '#detailsSessionModal');
 }
if (mode === 'edit') {
  this.editSession = session;
  button.setAttribute('data-target', '#updateSessionModal');
 }
if (mode === 'delete') {
 this.deleteSession= session;
 button.setAttribute('data-target', '#deleteSessionModal');
}
container.appendChild(button);
button.click();
}

}
