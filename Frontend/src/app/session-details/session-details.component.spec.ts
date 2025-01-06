import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessionDetailsComponent } from './session-details.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('SessionDetailsComponent', () => {
  let component: SessionDetailsComponent;
  let fixture: ComponentFixture<SessionDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SessionDetailsComponent ],
        imports:[HttpClientModule,FormsModule,ReactiveFormsModule,RouterTestingModule],
         providers: [
           {
             provide: ActivatedRoute,
             useValue: {
               params: ({ id: '123' }), // Mock route parameters
               queryParams: ({ query: 'test' }), // Mock query parameters
             },
           },
         ],
       }).compileComponents();
     });

  beforeEach(() => {
    fixture = TestBed.createComponent(SessionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
