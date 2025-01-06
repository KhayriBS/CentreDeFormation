import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganismeComponent } from './organisme.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { ActivatedRoute } from '@angular/router';

describe('OrganismeComponent', () => {
  let component: OrganismeComponent;
  let fixture: ComponentFixture<OrganismeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrganismeComponent ],
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
    fixture = TestBed.createComponent(OrganismeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
