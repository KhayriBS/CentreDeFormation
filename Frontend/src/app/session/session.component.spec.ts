import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessionComponent } from './session.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('SessionComponent', () => {
  let component: SessionComponent;
  let fixture: ComponentFixture<SessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SessionComponent ],

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
    fixture = TestBed.createComponent(SessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
