import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PaysComponent } from './pays/pays.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { OrganismeComponent } from './organisme/organisme.component';
import { ProfilComponent } from './profil/profil.component';
import { DomaineComponent } from './domaine/domaine.component';
import { ParticipantComponent } from './participant/participant.component';
import { FormationComponent } from './formation/formation.component';
import { FormateurComponent } from './formateur/formateur.component';
import { SessionComponent } from './session/session.component';
import { SessionDetailsComponent } from './session-details/session-details.component';
import { FormationDetailsComponent } from './formation-details/formation-details.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TableModule} from 'primeng/table'; 
import {ToastNoAnimationModule, ToastrModule} from 'ngx-toastr';
import { MatSliderModule } from '@angular/material/slider';
import {MatIconModule} from '@angular/material/icon';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { HomeComponent } from './home/home.component';
import { AllUsersComponent } from './users/allusers.component';
import { AuthInterceptor } from './services/authintercptor.service';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    AllUsersComponent,
  
    PaysComponent,
    OrganismeComponent,
    ProfilComponent,
    DomaineComponent,
    ParticipantComponent,
    FormationComponent,
    FormateurComponent,
    SessionComponent,
    SessionDetailsComponent,
    FormationDetailsComponent,

    
  ],
  imports: [
    RouterModule,
    ReactiveFormsModule,
    MatIconModule,
    BrowserModule,
    MatSliderModule ,
    BrowserAnimationsModule,
    TableModule, 
    FormsModule,
    
    HttpClientModule,
    AppRoutingModule,
    ToastNoAnimationModule,
    ToastrModule.forRoot({
      timeOut: 1000,
      progressBar:true,
      progressAnimation: 'increasing',
      preventDuplicates:true
    })

  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
