import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormationComponent } from './formation.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('FormationComponent', () => {
  let component: FormationComponent;
  let fixture: ComponentFixture<FormationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormationComponent ],
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
    fixture = TestBed.createComponent(FormationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
