import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaysComponent } from './pays.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('PaysComponent', () => {
  let component: PaysComponent;
  let fixture: ComponentFixture<PaysComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaysComponent ],
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
    fixture = TestBed.createComponent(PaysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
