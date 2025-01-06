import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormationDetailsComponent } from './formation-details.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('FormationDetailsComponent', () => {
  let component: FormationDetailsComponent;
  let fixture: ComponentFixture<FormationDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormationDetailsComponent ],
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
    fixture = TestBed.createComponent(FormationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
