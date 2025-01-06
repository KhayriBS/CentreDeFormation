import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'clientFormation';

  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  userName: string = '';
  userRoles: string = '';
  user: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    // Récupération des données depuis sessionStorage
    this.user = sessionStorage.getItem("roles") || '';
    this.userName = sessionStorage.getItem("username") || '';

    // Détermination des autorisations utilisateur
    this.showAdminBoard = this.user.includes('ROLE_ADMIN');
    this.showUserBoard = this.user.includes('ROLE_USER');
  }

  toggleMenu(event: Event): void {
    event.preventDefault();
    document.querySelector('#wrapper')?.classList.toggle('toggled');
  }

  getUserName(): string {
    return sessionStorage.getItem("username") || '';
  }

  getUserRole(): string {
    return sessionStorage.getItem("roles") || '';
  }

  onLogOut(): void {
    this.authService.logout();
  }

  loggedIn(): boolean {
    return this.authService.isLoggedIn();
  }
}
