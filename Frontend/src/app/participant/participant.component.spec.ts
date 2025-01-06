import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantComponent } from './participant.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('ParticipantComponent', () => {
  let component: ParticipantComponent;
  let fixture: ComponentFixture<ParticipantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParticipantComponent ],

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
    fixture = TestBed.createComponent(ParticipantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
